package com.issakass.booking;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Author: abdallah-issakass
 */
public class CarBookingDAO {

    private static final List<CarBooking> carBookings;

    static {
        carBookings = new ArrayList<>();
    }

    public List<CarBooking> getCarBookings() {
        return carBookings;
    }

    public void book(CarBooking carBooking) {
        carBookings.add(carBooking);
    }

    public void cancelCarBooking(UUID id) {

    }
}
