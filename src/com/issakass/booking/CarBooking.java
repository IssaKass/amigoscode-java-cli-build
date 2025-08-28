package com.issakass.booking;

import com.issakass.car.Car;
import com.issakass.user.User;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Author: abdallah-issakass
 */
public class CarBooking {
    private UUID bookingId;
    private User user;
    private Car car;
    private LocalDateTime bookingTime;
    private boolean isCanceled;

    public CarBooking(UUID bookingId, User user, Car car, LocalDateTime bookingTime, boolean isCanceled) {
        this.bookingId = bookingId;
        this.user = user;
        this.car = car;
        this.bookingTime = bookingTime;
        this.isCanceled = isCanceled;
    }

    public CarBooking(UUID bookingId, User user, Car car, LocalDateTime bookingTime) {
        this(bookingId, user, car, bookingTime, false);
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
        return isCanceled;
    }

    public void setCanceled(boolean canceled) {
        isCanceled = canceled;
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

    @Override
    public String toString() {
        return "CarBooking{" +
               "bookingId=" + bookingId +
               ", user=" + user +
               ", car=" + car +
               ", bookingTime=" + bookingTime +
               ", isCanceled=" + isCanceled +
               '}';
    }
}
