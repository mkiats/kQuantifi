package com.mkiats.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import org.springframework.cglib.core.Local;

public class dateConversion {

	public static LocalDateTime convertTimeToLocalDateTime(String timestamp) {
		// "Tue Jan 01 2999 08:00:00"

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
			"E MMM dd y HH:mm:ss"
		);
		return LocalDateTime.parse(timestamp, formatter);
	}

	public static double convertTimeToUnix(String timestamp) {
		LocalDateTime localDateTime = convertTimeToLocalDateTime(timestamp);
		ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("UTC"));
		Instant instant = zonedDateTime.toInstant();
		return instant.getEpochSecond();
	}
}
