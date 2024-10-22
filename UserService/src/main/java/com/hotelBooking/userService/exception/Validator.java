package com.hotelBooking.userService.exception;

import java.util.regex.Pattern;

public class Validator {
	
	private static final Pattern ROLE_PATTERN = Pattern.compile("^(owner|manager|receptionist)$");

	public static boolean validateRole(String role) {
		role = role.toLowerCase();
	    if (role == null || !ROLE_PATTERN.matcher(role).matches()) {
	        throw new RoleInvalidException("Invalid role: " + role);
	    }
	    return true;
	}
}
