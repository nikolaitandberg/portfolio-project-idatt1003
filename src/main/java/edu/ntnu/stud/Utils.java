package edu.ntnu.stud;

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
public class Utils {

  /** Parses string of format "HH:mm" into a LocalTime object.
   *
   * @param timeString the string that is parsed
   * @return the time as a LocalTime object
   */
  public static LocalTime parseTimeString(String timeString) {
    LocalTime time = null;
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
   * @return returns the sum of the two LocalTime objects as a single LocalTime object
   */
  public static LocalTime addDelay(LocalTime time, LocalTime delay) {
    return time.plusHours(delay.getHour()).plusMinutes(delay.getMinute());
  }
}
