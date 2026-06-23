package com.rapido.analytics_service.warehouse.repository;

import com.rapido.analytics_service.warehouse.entity.FactPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactPaymentRepository extends JpaRepository<FactPayment, Long> {
}