package com.issakass.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Author: abdallah-issakass
 */
class CarBookingDAOTest {

    private CarBookingDAO underTest;

    @BeforeEach
    void setUp() {
        underTest = new CarBookingDAO();
        underTest.getCarBookings().clear();
    }

    @Test
    void getCarBookings_shouldReturnAnEmptyList_whenNoBookingsExist() {
        // When
        List<CarBooking> bookings = underTest.getCarBookings();

        // Then
        assertThat(bookings).isNotNull()
                .isEmpty();
    }

    @Test
    void getCarBookings_shouldReturnAllBookings_whenBookingsExist() {
        // Given
        CarBooking booking1 = new CarBooking(UUID.randomUUID(), null, null, LocalDateTime.now(), false);
        CarBooking booking2 = new CarBooking(UUID.randomUUID(), null, null, LocalDateTime.now(), false);
        underTest.book(booking1);
        underTest.book(booking2);

        // When
        List<CarBooking> bookings = underTest.getCarBookings();

        // Then
        assertThat(bookings)
                .hasSize(2)
                .containsExactly(booking1, booking2);
    }

    @Test
    void book_shouldAddCarBookingToList() {
        // Given
        CarBooking booking = new CarBooking(UUID.randomUUID(), null, null, LocalDateTime.now(), false);

        // When
        underTest.book(booking);

        // Then
        List<CarBooking> bookings = underTest.getCarBookings();

        assertThat(bookings).hasSize(1);
        assertThat(bookings.getFirst()).isEqualTo(booking);
    }
}