package com.mkiats.commons.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DateUtils {

	public static LocalDateTime convertStringToLocalDateTime(
		String timestamp,
		String formatterPattern
	) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
			formatterPattern
		);
		LocalDateTime localDateTimeObject = LocalDateTime.parse(
			timestamp,
			formatter
		);
		//		System.out.println(
		//			"Converting timestamp: " +
		//			timestamp +
		//			"\nFormatter pattern used: " +
		//			formatterPattern +
		//			"\nlocalDateTime created: " +
		//			localDateTimeObject
		//		);
		return localDateTimeObject;
	}

	public static LocalDate convertStringToLocalDate(
		String timestamp,
		String formatterPattern
	) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
			formatterPattern
		);
		LocalDate localDateObject = LocalDate.parse(timestamp, formatter);
		//		System.out.println(
		//			"\nConverting timestamp: " +
		//			timestamp +
		//			"\nFormatter pattern used: " +
		//			formatterPattern +
		//			"\nLocalDate created: " +
		//			localDateObject
		//		);
		return localDateObject;
	}

	public static long convertStringToUnix(
		String timestamp,
		String formatterPattern
	) {
		LocalDate localDate = convertStringToLocalDate(
			timestamp,
			formatterPattern
		);
		Instant instant = localDate.atStartOfDay().toInstant(ZoneOffset.UTC);
		//		ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("UTC"));
		//		Instant instant = zonedDateTime.toInstant();
		return instant.getEpochSecond();
	}

	public static String convertUnixToString(
		long unixTimestamp,
		String formatterPattern
	) {
		Instant instant = Instant.ofEpochSecond(unixTimestamp);
		LocalDate localDate = instant.atOffset(ZoneOffset.UTC).toLocalDate();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(
			formatterPattern
		);
		return localDate.format(dateTimeFormatter);
	}

	public static String getLatestDate(
		String date1,
		String date1Formatter,
		String date2,
		String date2Formatter
	) {
		long date1Unix = convertStringToUnix(date1, date1Formatter);
		long date2Unix = convertStringToUnix(date2, date2Formatter);

		if (date1Unix < date2Unix) {
			return date2;
		} else {
			return date1;
		}
	}

	public static int binarySearchDate(
		List<String> dateList,
		String targetDate
	) {
		int left = 0;
		int right = dateList.size() - 1;
		long targetDateInUnix = convertStringToUnix(targetDate, "yyyy-MM-dd");

		while (left <= right) {
			int mid = left + (right - left) / 2;
			long midDateInUnix = convertStringToUnix(
				dateList.get(mid),
				"yyyy-MM-dd"
			);

			if (midDateInUnix == targetDateInUnix) {
				return mid; // Target found
			}
			if (midDateInUnix < targetDateInUnix) {
				left = mid + 1; // Search right half
			} else {
				right = mid - 1; // Search left half
			}
		}
		return left; // Target not found
	}
}
// "yyyy-MM-dd"
