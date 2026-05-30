package com.rapido.admin_service.repository;

import com.rapido.admin_service.entity.FraudAlert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FraudAlertRepository extends JpaRepository<FraudAlert, Long> {

    List<FraudAlert> findBySeverity(String severity);

    List<FraudAlert> findByResolved(Boolean resolved);

}