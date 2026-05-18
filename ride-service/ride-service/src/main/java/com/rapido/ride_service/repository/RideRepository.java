package com.rapido.ride_service.repository;

import com.rapido.ride_service.entity.Ride;
import com.rapido.ride_service.entity.RideStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RideRepository extends JpaRepository<Ride, Long> {

    List<Ride> findByUserId(Long userId);

    List<Ride> findByDriverId(Long driverId);

    Optional<Ride> findByUserIdAndStatusIn(
            Long userId,
            List<RideStatus> statuses
    );
}