package com.rapido.auth_server.repository;

import com.rapido.auth_server.entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepository
        extends JpaRepository<Ride, Long> {
}