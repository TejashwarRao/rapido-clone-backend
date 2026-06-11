package com.rapido.auth_server.repository;

import com.rapido.auth_server.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository
        extends JpaRepository<Payment, Long> {
}