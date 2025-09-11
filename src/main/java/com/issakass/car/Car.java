package com.issakass.car;

import com.issakass.booking.CarBooking;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * Author: abdallah-issakass
 */
@Entity
public class Car {
	@Id
	private String regNumber;

	private BigDecimal rentalPricePerDay;

	@Enumerated(EnumType.STRING)
	private Brand brand;

	private boolean electric;

	@OneToMany(mappedBy = "car")
	private List<CarBooking> bookings;

	public Car() {
	}

	public Car(
			String regNumber,
			BigDecimal rentalPricePerDay,
			Brand brand,
			boolean electric
	) {
		this.regNumber = regNumber;
		this.rentalPricePerDay = rentalPricePerDay;
		this.brand = brand;
		this.electric = electric;
	}

	public String getRegNumber() {
		return regNumber;
	}

	public BigDecimal getRentalPricePerDay() {
		return rentalPricePerDay;
	}

	public Brand getBrand() {
		return brand;
	}

	public boolean isElectric() {
		return electric;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Car car = (Car) o;
		return isElectric() == car.isElectric() && Objects.equals(getRegNumber(), car.getRegNumber()) && Objects.equals(getRentalPricePerDay(), car.getRentalPricePerDay()) && getBrand() == car.getBrand();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getRegNumber(), getRentalPricePerDay(), getBrand(), isElectric());
	}
}
