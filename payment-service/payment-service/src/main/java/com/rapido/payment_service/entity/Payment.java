package com.rapido.payment_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long rideId;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    // GETTERS

    public Long getId() {
        return id;
    }

    public Long getRideId() {
        return rideId;
    }

    public Double getAmount() {
        return amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    // SETTERS

    public void setId(Long id) {
        this.id = id;
    }

    public void setRideId(Long rideId) {
        this.rideId = rideId;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setPaymentMethod(
            PaymentMethod paymentMethod
    ) {

        this.paymentMethod =
                paymentMethod;
    }

    public void setPaymentStatus(
            PaymentStatus paymentStatus
    ) {

        this.paymentStatus =
                paymentStatus;
    }
}