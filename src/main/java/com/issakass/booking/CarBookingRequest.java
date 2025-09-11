package com.issakass.booking;

import java.util.UUID;

/**
 * Author: abdallah-issakass
 */
public record CarBookingRequest(
		String userId,
		String regNumber
) {
}
