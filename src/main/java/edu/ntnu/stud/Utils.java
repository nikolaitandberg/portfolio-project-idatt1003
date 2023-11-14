package edu.ntnu.stud;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    return LocalTime.parse(timeString, formatter);
  }

  /** Adds two LocalTimeObjects with format "HH:mm".
   *
   * @return returns the sum of the two LocalTime objects as a single LocalTime object
   */
  public static LocalTime addDelay(LocalTime time, LocalTime delay) {
    int timeHours = time.getHour();
    int timeMinutes = time.getMinute();
    int delayHours = delay.getHour();
    int delayMinutes = delay.getMinute();

    return LocalTime.of(timeHours + delayHours, timeMinutes + delayMinutes);
  }
}
