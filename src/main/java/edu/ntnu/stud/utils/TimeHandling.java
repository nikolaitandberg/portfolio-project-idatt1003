package edu.ntnu.stud.utils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utils class for methods used in train dispatch system.
 *
 * @author nikolaitandberg
 * @since 2023-11-13
 * @version 1.0
 */
public class TimeHandling {

  public TimeHandling() {}

  /** Parses string of format "HH:mm" into a LocalTime object.
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
    }
    catch (DateTimeParseException dateTimeParseException) {
      throw new IllegalArgumentException("Invalid time format. Expected format: 'HH:mm'", dateTimeParseException);
    }
    return time;
  }

  /** Adds two LocalTimeObjects with format "HH:mm".
   *
   *
   * @param time the time to be added to
   * @param delay the delay to be added
   * @return returns the sum of the two LocalTime objects as a single LocalTime object
   * @throws IllegalArgumentException if the sum of the two LocalTime objects is greater than 24 hours
   * @throws NullPointerException if either of the LocalTime objects are null
   */
  public static LocalTime addDelay(LocalTime time, LocalTime delay) throws NullPointerException, IllegalArgumentException {
    if (time == null || delay == null) {
      throw new NullPointerException("Time or delay cannot be null");
    }

    if (time.getHour() + delay.getHour() > 23 || time.getHour() + delay.getHour() == 23 && time.getMinute() + delay.getMinute() > 59) {
      throw new IllegalArgumentException("Return time value cannot be greater than 24 hours");
    }

    return time.plusHours(delay.getHour()).plusMinutes(delay.getMinute());
  }
}
