package com.issakass.car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Author: abdallah-issakass
 */
public interface CarRepository extends JpaRepository<Car, String> {

	List<Car> findByElectricTrue();

	@Query("""
			    SELECT DISTINCT c FROM Car c
			    LEFT JOIN c.bookings b
			    WHERE b IS NULL OR b.canceled = true
			""")
	List<Car> findAllAvailableCars();

	@Query("""
			    SELECT DISTINCT c FROM Car c
			    LEFT JOIN c.bookings b
			    WHERE (b IS NULL OR b.canceled = true)
			      AND c.electric = true
			""")
	List<Car> findAllAvailableElectricCars();

	List<Car> findAllByBookingsIsNotEmpty();
}
