package com.mkiats.commons.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateUtils {

	public static LocalDateTime convertStringToLocalDateTime(String timestamp, String formatterPattern) {
//		"Tue Jan 01 2999 08:00:00"
//		"E MMM dd y HH:mm:ss"
		System.out.println("Converting timestamp: " + timestamp);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
				formatterPattern
		);
		return LocalDateTime.parse(timestamp, formatter);
	}
	public static LocalDate convertStringToLocalDate(String timestamp, String formatterPattern) {
//		"Tue Jan 01 2999 08:00:00"
//		"E MMM dd y HH:mm:ss"
		System.out.println("Converting timestamp: " + timestamp);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
				formatterPattern
		);
		return LocalDate.parse(timestamp, formatter);
	}

	public static double convertStringToUnix(String timestamp, String formatterPattern) {
		LocalDateTime localDateTime = convertStringToLocalDateTime(timestamp, formatterPattern);
		ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("UTC"));
		Instant instant = zonedDateTime.toInstant();
		return instant.getEpochSecond();
	}
}
