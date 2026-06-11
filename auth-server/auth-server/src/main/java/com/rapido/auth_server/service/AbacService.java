package com.rapido.auth_server.service;

import com.rapido.auth_server.entity.Payment;
import com.rapido.auth_server.entity.Ride;
import com.rapido.auth_server.repository.PaymentRepository;
import com.rapido.auth_server.repository.RideRepository;

import org.springframework.stereotype.Service;

@Service
public class AbacService {

    private final RideRepository rideRepository;
    private final PaymentRepository paymentRepository;

    public AbacService(
            RideRepository rideRepository,
            PaymentRepository paymentRepository) {

        this.rideRepository = rideRepository;
        this.paymentRepository = paymentRepository;
    }

    /**
     * Driver can access only assigned ride.
     * Super Admin can access all rides.
     */
    public boolean canAccessRide(
            Long rideId,
            String username) {

        if ("superadmin".equalsIgnoreCase(username)) {
            return true;
        }

        Ride ride =
                rideRepository.findById(rideId)
                        .orElse(null);

        if (ride == null) {
            return false;
        }

        return username.equalsIgnoreCase(
                ride.getDriverUsername());
    }

    /**
     * User can access only own payment.
     * Super Admin can access all payments.
     */
    public boolean canAccessPayment(
            Long paymentId,
            String username) {

        if ("superadmin".equalsIgnoreCase(username)) {
            return true;
        }

        Payment payment =
                paymentRepository.findById(paymentId)
                        .orElse(null);

        if (payment == null) {
            return false;
        }

        return username.equalsIgnoreCase(
                payment.getOwnerUsername());
    }
}