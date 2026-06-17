package com.rapido.ride_service.repository;

import com.rapido.ride_service.entity.SagaExecution;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SagaExecutionRepository
        extends JpaRepository<SagaExecution, Long> {
}