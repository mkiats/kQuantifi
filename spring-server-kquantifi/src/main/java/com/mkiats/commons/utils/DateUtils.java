package com.mkiats.commons.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateUtils {

	public static LocalDateTime convertStringToLocalDateTime(String timestamp, String formatterPattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
				formatterPattern
		);
		LocalDateTime localDateTimeObject = LocalDateTime.parse(timestamp, formatter);
		System.out.println("Converting timestamp: " + timestamp + "\nFormatter pattern used: " + formatterPattern + "\nlocalDateTime created: " + localDateTimeObject);
		return localDateTimeObject;
	}
	public static LocalDate convertStringToLocalDate(String timestamp, String formatterPattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
				formatterPattern
		);
		LocalDate localDateObject = LocalDate.parse(timestamp, formatter);
		System.out.println("\nConverting timestamp: " + timestamp + "\nFormatter pattern used: " + formatterPattern + "\nLocalDate created: " + localDateObject);
		return localDateObject;
	}

	public static long convertStringToUnix(String timestamp, String formatterPattern) {
		LocalDate localDate = convertStringToLocalDate(timestamp, formatterPattern);
		Instant instant = localDate.atStartOfDay().toInstant(ZoneOffset.UTC);
//		ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("UTC"));
//		Instant instant = zonedDateTime.toInstant();
		return instant.getEpochSecond();
	}

	public static String convertUnixToString(long unixTimestamp, String formatterPattern) {
		Instant instant = Instant.ofEpochSecond(unixTimestamp);
		LocalDate localDate = instant.atOffset(ZoneOffset.UTC).toLocalDate();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatterPattern);
		return localDate.format(dateTimeFormatter);
	}
}
