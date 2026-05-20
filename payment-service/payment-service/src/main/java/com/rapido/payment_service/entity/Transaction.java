package com.rapido.payment_service.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long rideId;

    private Long payerId;

    private Long driverId;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private String transactionReference;

    private LocalDateTime createdAt;

    // GETTERS

    public Long getId() {
        return id;
    }

    public Long getRideId() {
        return rideId;
    }

    public Long getPayerId() {
        return payerId;
    }

    public Long getDriverId() {
        return driverId;
    }

    public Double getAmount() {
        return amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public String getTransactionReference() {
        return transactionReference;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // SETTERS

    public void setId(Long id) {
        this.id = id;
    }

    public void setRideId(Long rideId) {
        this.rideId = rideId;
    }

    public void setPayerId(Long payerId) {
        this.payerId = payerId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}