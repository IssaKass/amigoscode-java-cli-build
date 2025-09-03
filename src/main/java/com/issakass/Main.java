package com.issakass;

import com.issakass.booking.CarBooking;
import com.issakass.booking.CarBookingDAO;
import com.issakass.booking.CarBookingService;
import com.issakass.car.Car;
import com.issakass.car.CarDAO;
import com.issakass.car.CarService;
import com.issakass.user.*;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

/**
 * Author: abdallah-issakass
 */
public class Main {

    public static void main(String[] args) {
        UserDAO userDAO = new UserFakerDataAccessService();
        UserService userService = new UserService(userDAO);

        CarBookingDAO carBookingDAO = new CarBookingDAO();
        CarDAO carDAO = new CarDAO();

        CarService carService = new CarService(carDAO);
        CarBookingService carBookingService = new CarBookingService(carBookingDAO, carService);

        Scanner scanner = new Scanner(System.in);

        boolean keepLooping = true;

        while (keepLooping) {
            try {
                displayMenu();
                String choice = scanner.nextLine();
                switch (Integer.parseInt(choice)) {
                    case 1 -> bookCar(userService, carBookingService, scanner);
                    case 2 -> displayAllUserBookedCars(userService, carBookingService, scanner);
                    case 3 -> displayAllBookings(carBookingService);
                    case 4 -> displayAvailableCars(carBookingService, false);
                    case 5 -> displayAvailableCars(carBookingService, true);
                    case 6 -> displayAllUsers(userService);
                    case 7 -> keepLooping = false;
                    default -> System.out.println(choice + " not a valid option ❌");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println();
        }

        scanner.close();
    }

    private static void bookCar(
            UserService userService,
            CarBookingService carBookingService,
            Scanner scanner
    ) {
        displayAvailableCars(carBookingService, false);
        System.out.println("➡️ select car reg number");
        String regNumber = scanner.nextLine();

        displayAllUsers(userService);
        System.out.println("➡️ select user id");
        String userId = scanner.nextLine();

        try {
            User user = userService.getUserById(UUID.fromString(userId));
            if (user == null) {
                System.out.println("❌ No user found with id " + userId);
            } else {
                UUID bookingId = carBookingService.bookCar(user, regNumber);
                String confirmationMessage = """
                        🎉 Successfully booked car with reg number %s for user %s
                        Booking ref: %s
                        """.formatted(regNumber, user, bookingId);
                System.out.println(confirmationMessage);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void displayAllUserBookedCars(
            UserService userService,
            CarBookingService carBookingService,
            Scanner scanner
    ) {
        displayAllUsers(userService);
        System.out.println("➡️ select user id");
        String userId = scanner.nextLine();

        User user = userService.getUserById(UUID.fromString(userId));
        if (user == null) {
            System.out.println("❌ No user found with id " + userId);
            return;
        }

        List<Car> userBookedCars = carBookingService.getUserBookedCars(user.getId());
        if (userBookedCars.isEmpty()) {
            System.out.println("❌ user " + user + " has no cars booked");
            return;
        }

        for (Car car : userBookedCars) {
            System.out.println(car);
        }
    }

    private static void displayAllBookings(CarBookingService carBookingService) {
        List<CarBooking> bookings = carBookingService.getBookings();
        if (bookings.isEmpty()) {
            System.out.println("No bookings available 🙁");
            return;
        }

        for (CarBooking booking : bookings) {
            System.out.println("booking = " + booking);
        }
    }

    private static void displayAvailableCars(CarBookingService carBookingService, boolean isElectric) {
        List<Car> cars = isElectric ? carBookingService.getAvailableElectricCars() : carBookingService.getAvailableCars();
        if (cars.isEmpty()) {
            System.out.println("❌ No " + (isElectric ? "electric " : "") + "cars available for renting");
            return;
        }

        for (Car availableCar : cars) {
            System.out.println(availableCar);
        }
    }

    private static void displayAllUsers(UserService userService) {
        List<User> users = userService.getUsers();
        if (users.isEmpty()) {
            System.out.println("❌ No users in the system");
            return;
        }

        for (User user : users) {
            System.out.println(user);
        }
    }

    private static void displayMenu() {
        System.out.println("""
                1️⃣ - Book Car
                2️⃣ - View All User Booked Cars
                3️⃣️ - View All Bookings
                4️⃣ - View Available Car
                5️⃣ - View Available Electric Car
                6️⃣ - View All Users
                7️⃣ - Exit
                """);
    }
}
