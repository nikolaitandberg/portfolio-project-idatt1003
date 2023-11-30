package edu.ntnu.stud.utils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utils class with methods for handling time used in train dispatch system.
 *
 * @author Nikolai Tandberg
 * @since 2023-11-13
 * @version 1.0
 */
public class TimeHandling {

  public TimeHandling() {}

  /**
   * Parses string of format "HH:mm" into a LocalTime object.
   *
   * @param timeString the string that is parsed
   * @return the time as a LocalTime object
   * @throws IllegalArgumentException if the string is not of format "HH:mm"
   */
  public static LocalTime parseTimeString(String timeString) throws IllegalArgumentException {
    LocalTime time;
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
      time = LocalTime.parse(timeString, formatter);
    } catch (DateTimeParseException dateTimeParseException) {
      throw new IllegalArgumentException(
              "Invalid time format. Expected format: 'HH:mm'", dateTimeParseException
      );
    }
    return time;
  }

  /**
   * Adds the hours and minutes of LocalTimeObject together with another LocalTime object.
   *
   *
   * @param time the time to be added to
   * @param delay the delay to be added
   * @return LocalTime object with the added minutes and hours of the two LocalTime objects
   * @throws IllegalArgumentException if either of the LocalTime objects are null
   */
  public static LocalTime addDelay(LocalTime time, LocalTime delay)
          throws IllegalArgumentException {
    if (time == null || delay == null) {
      throw new IllegalArgumentException("Time or delay cannot be null");
    }

    return time.plusHours(delay.getHour()).plusMinutes(delay.getMinute());
  }
}
