package com.issakass.car;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Author: abdallah-issakass
 */
public class Car {
    private String regNumber;
    private BigDecimal rentalPricePerDay;
    private Brand brand;
    private boolean isElectric;

    public Car(String regNumber, BigDecimal rentalPricePerDay, Brand brand, boolean isElectric) {
        this.regNumber = regNumber;
        this.rentalPricePerDay = rentalPricePerDay;
        this.brand = brand;
        this.isElectric = isElectric;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public BigDecimal getRentalPricePerDay() {
        return rentalPricePerDay;
    }

    public void setRentalPricePerDay(BigDecimal rentalPricePerDay) {
        this.rentalPricePerDay = rentalPricePerDay;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public boolean isElectric() {
        return isElectric;
    }

    public void setElectric(boolean electric) {
        isElectric = electric;
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

    @Override
    public String toString() {
        return "Car{" +
               "regNumber='" + regNumber + '\'' +
               ", rentalPricePerDay=" + rentalPricePerDay +
               ", brand=" + brand +
               ", isElectric=" + isElectric +
               '}';
    }
}
