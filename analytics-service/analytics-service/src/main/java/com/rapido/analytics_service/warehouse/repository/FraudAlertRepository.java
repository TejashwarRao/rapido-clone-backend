package com.rapido.analytics_service.warehouse.repository;

import com.rapido.analytics_service.warehouse.entity.FraudAlert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FraudAlertRepository
        extends JpaRepository<FraudAlert, Long> {

    boolean existsByEntityIdAndEntityType(
            Long entityId,
            String entityType
    );
}