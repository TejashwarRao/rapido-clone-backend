package com.rapido.payment_service.service;

import com.rapido.payment_service.dto.PaymentRequest;
import com.rapido.payment_service.entity.PaymentStatus;
import com.rapido.payment_service.entity.Transaction;
import com.rapido.payment_service.entity.Wallet;
import com.rapido.payment_service.repository.TransactionRepository;
import com.rapido.payment_service.repository.WalletRepository;
import com.rapido.payment_service.util.PaymentGatewaySimulator;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentService {

    private static final Logger logger =
            LoggerFactory.getLogger(PaymentService.class);

    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    private Tracer tracer;

    public PaymentService(
            WalletRepository walletRepository,
            TransactionRepository transactionRepository) {

        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }

    public Wallet createWallet(Long userId) {

        Span span = tracer.spanBuilder("wallet-create")
                .startSpan();

        try {

            Wallet wallet = new Wallet();
            wallet.setUserId(userId);
            wallet.setBalance(0.0);
            wallet.setActive(true);
            wallet.setCreatedAt(LocalDateTime.now());
            wallet.setUpdatedAt(LocalDateTime.now());

            Wallet savedWallet = walletRepository.save(wallet);

            logger.info("Wallet created for user ID: {}", userId);

            return savedWallet;

        } finally {

            span.end();
        }
    }

    @Transactional
    public Wallet topUpWallet(Long userId, Double amount) {

        Span span = tracer.spanBuilder("wallet-topup")
                .startSpan();

        try {

            if (amount == null || amount < 10) {
                throw new RuntimeException("Minimum top-up amount is 10");
            }

            Wallet wallet = walletRepository.findByUserId(userId)
                    .orElseGet(() -> createWallet(userId));

            wallet.setBalance(wallet.getBalance() + amount);
            wallet.setUpdatedAt(LocalDateTime.now());

            Wallet savedWallet = walletRepository.save(wallet);

            logger.info(
                    "Wallet recharged for user ID: {}, amount: {}",
                    userId,
                    amount
            );

            return savedWallet;

        } finally {

            span.end();
        }
    }

    public Double getBalance(Long userId) {

        Span span = tracer.spanBuilder("balance-check")
                .startSpan();

        try {

            Wallet wallet = walletRepository.findByUserId(userId)
                    .orElseThrow(() ->
                            new RuntimeException("Wallet not found"));

            return wallet.getBalance();

        } finally {

            span.end();
        }
    }

    @Transactional
    public Transaction processRidePayment(
            PaymentRequest request) {

        Span span = tracer.spanBuilder("payment-processing")
                .startSpan();

        try {

            if (transactionRepository.existsByRideIdAndStatus(
                    request.getRideId(),
                    PaymentStatus.SUCCESS)) {

                logger.warn(
                        "Duplicate payment attempt for ride ID: {}",
                        request.getRideId()
                );

                throw new RuntimeException(
                        "Payment already completed for this ride"
                );
            }

            boolean gatewaySuccess =
                    PaymentGatewaySimulator.processPayment();

            if (!gatewaySuccess) {

                Transaction failedTransaction =
                        new Transaction();

                failedTransaction.setRideId(
                        request.getRideId());

                failedTransaction.setPayerId(
                        request.getPayerId());

                failedTransaction.setDriverId(
                        request.getDriverId());

                failedTransaction.setAmount(
                        request.getAmount());

                failedTransaction.setStatus(
                        PaymentStatus.FAILED);

                failedTransaction.setTransactionReference(
                        UUID.randomUUID().toString());

                failedTransaction.setCreatedAt(
                        LocalDateTime.now());

                transactionRepository.save(
                        failedTransaction);

                logger.error(
                        "Payment failed for ride ID: {}",
                        request.getRideId()
                );

                throw new RuntimeException(
                        "Payment gateway failed"
                );
            }

            Wallet riderWallet =
                    walletRepository.findByUserId(
                                    request.getPayerId())
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Rider wallet not found"));

            Wallet driverWallet =
                    walletRepository.findByUserId(
                                    request.getDriverId())
                            .orElseGet(() ->
                                    createWallet(
                                            request.getDriverId()));

            if (riderWallet.getBalance()
                    < request.getAmount()) {

                throw new RuntimeException(
                        "Insufficient wallet balance");
            }

            riderWallet.setBalance(
                    riderWallet.getBalance()
                            - request.getAmount());

            driverWallet.setBalance(
                    driverWallet.getBalance()
                            + request.getAmount());

            walletRepository.save(riderWallet);
            walletRepository.save(driverWallet);

            Transaction transaction =
                    new Transaction();

            transaction.setRideId(
                    request.getRideId());

            transaction.setPayerId(
                    request.getPayerId());

            transaction.setDriverId(
                    request.getDriverId());

            transaction.setAmount(
                    request.getAmount());

            transaction.setStatus(
                    PaymentStatus.SUCCESS);

            transaction.setTransactionReference(
                    UUID.randomUUID().toString());

            transaction.setCreatedAt(
                    LocalDateTime.now());

            Transaction savedTransaction =
                    transactionRepository.save(
                            transaction);

            logger.info(
                    "Payment successful for ride ID: {}",
                    request.getRideId()
            );

            return savedTransaction;

        } finally {

            span.end();
        }
    }

    @Transactional
    public String refundRide(Long rideId) {

        Span span = tracer.spanBuilder("refund-processing")
                .startSpan();

        try {

            Transaction transaction =
                    transactionRepository
                            .findByRideIdAndStatus(
                                    rideId,
                                    PaymentStatus.SUCCESS)
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Successful payment not found"));

            Wallet riderWallet =
                    walletRepository.findByUserId(
                                    transaction.getPayerId())
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Rider wallet not found"));

            Wallet driverWallet =
                    walletRepository.findByUserId(
                                    transaction.getDriverId())
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Driver wallet not found"));

            riderWallet.setBalance(
                    riderWallet.getBalance()
                            + transaction.getAmount());

            driverWallet.setBalance(
                    driverWallet.getBalance()
                            - transaction.getAmount());

            walletRepository.save(riderWallet);
            walletRepository.save(driverWallet);

            transaction.setStatus(
                    PaymentStatus.REFUNDED);

            transactionRepository.save(transaction);

            logger.info(
                    "Refund processed for ride ID: {}",
                    rideId
            );

            return "Refund processed successfully";

        } finally {

            span.end();
        }
    }

    public List<Transaction> getTransactionHistory(
            Long userId) {

        Span span = tracer.spanBuilder(
                        "transaction-history")
                .startSpan();

        try {

            return transactionRepository
                    .findByPayerId(userId);

        } finally {

            span.end();
        }
    }
}