package com.rapido.analytics_service.warehouse.repository;

import com.rapido.analytics_service.warehouse.entity.FactNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactNotificationRepository extends JpaRepository<FactNotification, Long> {
}