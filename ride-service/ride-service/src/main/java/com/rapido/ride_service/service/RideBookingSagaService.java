package com.rapido.ride_service.service;

import com.rapido.ride_service.entity.*;

import com.rapido.ride_service.repository.SagaExecutionRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RideBookingSagaService {

    private final SagaExecutionRepository repository;

    public SagaExecution startSaga() {

        SagaExecution saga =
                new SagaExecution();

        saga.setSagaId(
                UUID.randomUUID().toString()
        );

        saga.setSagaType(
                "RIDE_BOOKING"
        );

        saga.setCurrentStep(
                SagaStep.RIDE_REQUESTED.name()
        );

        saga.setStatus(
                SagaStatus.STARTED.name()
        );

        return repository.save(
                saga
        );
    }
}