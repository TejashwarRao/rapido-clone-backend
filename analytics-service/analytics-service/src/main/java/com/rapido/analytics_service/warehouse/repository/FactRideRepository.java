package com.rapido.analytics_service.warehouse.repository;

import com.rapido.analytics_service.warehouse.entity.FactRide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactRideRepository extends JpaRepository<FactRide, Long> {

    Long countByStatus(String status);

    boolean existsByRideId(Long rideId);

    @Query("""
            SELECT
                f.city,
                COUNT(f),
                SUM(f.fare)
            FROM FactRide f
            GROUP BY f.city
            """)
    List<Object[]> getCityAnalytics();

    @Query("""
            SELECT COALESCE(SUM(f.fare),0)
            FROM FactRide f
            WHERE f.status='COMPLETED'
            """)
    Double getTotalRevenue();

    @Query("""
            SELECT COUNT(f)
            FROM FactRide f
            WHERE f.status='COMPLETED'
            """)
    Long getCompletedRideCount();

    @Query("""
            SELECT COUNT(f)
            FROM FactRide f
            WHERE f.status='CANCELLED'
            """)
    Long getCancelledRideCount();

    @Query("""
            SELECT
                f.city,
                COALESCE(SUM(f.fare),0)
            FROM FactRide f
            WHERE f.status='COMPLETED'
            GROUP BY f.city
            """)
    List<Object[]> getRevenueByCity();

    @Query(value = """
            SELECT city
            FROM fact_rides
            GROUP BY city
            ORDER BY COUNT(*) DESC
            LIMIT 1
            """, nativeQuery = true)
    String getTopCity();

    @Query("""
            SELECT COUNT(f)
            FROM FactRide f
            WHERE f.driverId = :driverId
            """)
    Long getTotalRidesByDriver(
            @Param("driverId") Long driverId
    );

    @Query("""
            SELECT COUNT(f)
            FROM FactRide f
            WHERE f.driverId = :driverId
            AND f.status='COMPLETED'
            """)
    Long getCompletedRidesByDriver(
            @Param("driverId") Long driverId
    );

    @Query("""
            SELECT COUNT(f)
            FROM FactRide f
            WHERE f.driverId = :driverId
            AND f.status='CANCELLED'
            """)
    Long getCancelledRidesByDriver(
            @Param("driverId") Long driverId
    );

    @Query("""
            SELECT COUNT(DISTINCT f.userId)
            FROM FactRide f
            """)
    Long getTotalCustomers();
    @Query("""
SELECT f.driverId
FROM FactRide f
WHERE f.status='CANCELLED'
GROUP BY f.driverId
HAVING COUNT(f) >= 3
""")

    List<Long> getSuspiciousDrivers();
    @Query("""
SELECT
    f.pickupLat,
    f.pickupLon,
    COUNT(f)
FROM FactRide f
WHERE f.pickupLat IS NOT NULL
GROUP BY f.pickupLat, f.pickupLon
ORDER BY COUNT(f) DESC
""")
    List<Object[]> getPickupHeatmap();
    @Query("""
            SELECT f.userId
            FROM FactRide f
            GROUP BY f.userId
            HAVING COUNT(f) > 1
            """)
    List<Long> getReturningCustomers();
}