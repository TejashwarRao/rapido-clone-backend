package com.rapido.ride_service.service;

import com.rapido.ride_service.entity.Ride;
import com.rapido.ride_service.entity.RideStatus;
import com.rapido.ride_service.entity.SagaExecution;
import com.rapido.ride_service.event.RideRequestedEvent;
import com.rapido.ride_service.kafka.KafkaProducerService;
import com.rapido.ride_service.repository.RideRepository;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class RideService {

    private final RideRepository rideRepository;

    private final KafkaProducerService kafkaProducerService;

    private final RideBookingSagaService rideBookingSagaService;

    private final EventAuditService eventAuditService;

    @Autowired
    private Tracer tracer;

    public RideService(
            RideRepository rideRepository,
            KafkaProducerService kafkaProducerService,
            RideBookingSagaService rideBookingSagaService,
            EventAuditService eventAuditService
    ) {

        this.rideRepository = rideRepository;

        this.kafkaProducerService = kafkaProducerService;

        this.rideBookingSagaService = rideBookingSagaService;

        this.eventAuditService = eventAuditService;
    }

    // CREATE RIDE
    public Ride createRide(Ride ride) {

        Span span =
                tracer.spanBuilder("ride-create")
                        .startSpan();

        try {

            ride.setStatus(
                    RideStatus.REQUESTED
            );

            ride.setFare(
                    150.0
            );

            Ride savedRide =
                    rideRepository.save(
                            ride
                    );

            // START SAGA

            SagaExecution saga =
                    rideBookingSagaService.startSaga();

            log.info(
                    "Saga Started : {}",
                    saga.getSagaId()
            );

            // CREATE EVENT

            RideRequestedEvent event =
                    new RideRequestedEvent();

            event.setEventId(
                    UUID.randomUUID().toString()
            );

            event.setRideId(
                    savedRide.getId()
            );

            event.setUserEmail(
                    savedRide.getUserEmail()
            );

            event.setPickupLocation(
                    savedRide.getPickupLocation()
            );

            event.setDropLocation(
                    savedRide.getDropLocation()
            );

            // PUBLISH TO KAFKA

            kafkaProducerService
                    .publishRideRequestedEvent(
                            event
                    );

            log.info(
                    "RideRequestedEvent Published : {}",
                    event.getEventId()
            );

            // SAVE AUDIT

            eventAuditService.saveEvent(
                    event.getEventId(),
                    "RideRequestedEvent",
                    "ride-requested",
                    event
            );

            log.info(
                    "Event Audit Saved : {}",
                    event.getEventId()
            );

            return savedRide;

        } finally {

            span.end();
        }
    }

    // GET ALL RIDES

    public List<Ride> getAllRides() {

        Span span =
                tracer.spanBuilder("ride-find-all")
                        .startSpan();

        try {

            return rideRepository.findAll();

        } finally {

            span.end();
        }
    }

    // GET RIDE BY ID

    public Ride getRideById(
            Long rideId
    ) {

        Span span =
                tracer.spanBuilder("ride-db-query")
                        .startSpan();

        try {

            return rideRepository.findById(
                    rideId
            ).orElseThrow(
                    () -> new RuntimeException(
                            "Ride not found"
                    )
            );

        } finally {

            span.end();
        }
    }

    // START RIDE

    public Ride startRide(
            Long rideId
    ) {

        Span span =
                tracer.spanBuilder("ride-start")
                        .startSpan();

        try {

            Ride ride =
                    getRideById(
                            rideId
                    );

            ride.setStatus(
                    RideStatus.STARTED
            );

            return rideRepository.save(
                    ride
            );

        } finally {

            span.end();
        }
    }

    // COMPLETE RIDE

    public Ride completeRide(
            Long rideId
    ) {

        Span span =
                tracer.spanBuilder("ride-complete")
                        .startSpan();

        try {

            Ride ride =
                    getRideById(
                            rideId
                    );

            ride.setStatus(
                    RideStatus.COMPLETED
            );

            return rideRepository.save(
                    ride
            );

        } finally {

            span.end();
        }
    }

    // CANCEL RIDE

    public Ride cancelRide(
            Long rideId
    ) {

        Span span =
                tracer.spanBuilder("ride-cancel")
                        .startSpan();

        try {

            Ride ride =
                    getRideById(
                            rideId
                    );

            ride.setStatus(
                    RideStatus.CANCELLED
            );

            return rideRepository.save(
                    ride
            );

        } finally {

            span.end();
        }
    }
}