package com.issakass.booking;

import com.issakass.car.Car;
import com.issakass.user.User;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Author: abdallah-issakass
 */
@Entity
public class CarBooking {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID bookingId;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "car_id", nullable = false)
	private Car car;

	@CreatedDate
	private LocalDateTime bookingTime;

	private boolean canceled;

	public CarBooking() {
	}

	public CarBooking(User user, Car car) {
		this.user = user;
		this.car = car;
	}

	public CarBooking(User user, Car car, boolean canceled) {
		this.user = user;
		this.car = car;
		this.canceled = canceled;
	}

	public UUID getBookingId() {
		return bookingId;
	}

	public void setBookingId(UUID bookingId) {
		this.bookingId = bookingId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public LocalDateTime getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(LocalDateTime bookingTime) {
		this.bookingTime = bookingTime;
	}

	public boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		CarBooking booking = (CarBooking) o;
		return isCanceled() == booking.isCanceled() && Objects.equals(getBookingId(), booking.getBookingId()) && Objects.equals(getUser(), booking.getUser()) && Objects.equals(getCar(), booking.getCar()) && Objects.equals(getBookingTime(), booking.getBookingTime());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getBookingId(), getUser(), getCar(), getBookingTime(), isCanceled());
	}
}
