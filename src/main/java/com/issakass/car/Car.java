package com.issakass.car;

import java.math.BigDecimal;

/**
 * Author: abdallah-issakass
 */
public record Car(
        String regNumber,
        BigDecimal rentalPricePerDay,
        Brand brand,
        boolean isElectric
) {

}
