package edu.ntnu.stud.models;

import java.time.LocalTime;

/**
 * A class representing a single departure.
 *
 * @author Nikolai Tandberg
 * @version 1.0
 * @since 2023-11-8
 *
 */
public class Departure {
  private final LocalTime departureTime;
  private final String line;
  private final int trainNumber;
  private final String destination;
  private int track;
  private LocalTime delay;

  /**
   * <h1>Constructs an object of the Departure class.</h1>
   *
   *     <p>
   *         Takes the following parameters.
   *         May throw IllegalArgumentException.
   *     </p>
   *
   *     <ul>
   *         <li>
   *             <code>@param departureTime</code>: the time of day the train departs
   *         </li>
   *         <li>
   *             <code>@param line</code>: the line the train is traveling on
   *         </li>
   *         <li>
   *             <code>@param trainNumber</code>: the unique number of the departure
   *         </li>
   *         <li>
   *             <code>@param destination</code>: the last stop on the line
   *         </li>
   *         <li>
   *             <code>@param track</code>: the track at which the train arrives at the station
   *         </li>
   *         <li>
   *             <code>@throws IllegalArgumentException</code>:
   *             When line is empty,
   *             when train number is less than 1,
   *             when destination is empty,
   *             when track is 0 or a negative number other than -1
   *             <code>@throws NullPointerException</code>:
   *             when departure time is null,
   *             when delay is null
   *         </li>
   *     </ul>
   */

  public Departure(
          LocalTime departureTime,
          String line,
          int trainNumber,
          String destination,
          int track,
          LocalTime delay
  ) throws IllegalArgumentException {
    if (departureTime == null) {
      throw new NullPointerException("Departure time cannot be null");
    }
    if (line.isEmpty()) {
      throw new IllegalArgumentException("Line cannot be left empty");
    }
    if (trainNumber < 1) {
      throw new IllegalArgumentException("Train number cannot be less than 1");
    }
    if (destination.isEmpty()) {
      throw new IllegalArgumentException("Destination cannot be left empty");
    }

    this.departureTime = departureTime;
    this.line = line;
    this.trainNumber = trainNumber;
    this.destination = destination;
    this.setTrack(track);
    this.setDelay(delay);
  }

  /**
   * Sets a new track for the departure.
   *
   * @param track the new track
   * @throws IllegalArgumentException if the track is 0 or a negative number other than -1
   */
  public void setTrack(int track) throws IllegalArgumentException {
    if (track < -1 || track == 0) {
      throw new IllegalArgumentException("Track cannot be 0 or a negative number other than -1");
    }
    this.track = track;
  }

  /**
   * set new delay.
   *
   * @param delay new delay
   * @throws NullPointerException if delay is null
   */
  public void setDelay(LocalTime delay) {
    if (delay == null) {
      throw new NullPointerException("Delay cannot be null");
    }
    this.delay = delay;
  }

  /**
   * gets departure time.
   *
   * @return departure time
   */
  public LocalTime getDepartureTime() {
    return departureTime;
  }

  /**
   * gets line.
   *
   * @return line
   */
  public String getLine() {
    return line;
  }

  /**
   * gets train number.
   *
   * @return train number
   */
  public int getTrainNumber() {
    return trainNumber;
  }

  /**
   * gets destination.
   *
   * @return destination
   */
  public String getDestination() {
    return destination;
  }

  /**
   * gets track.
   *
   * @return track
   */
  public int getTrack() {
    return track;
  }

  /**
   * gets delay.
   *
   * @return delay
   */
  public LocalTime getDelay() {
    return delay;
  }

  /**
   * gets actual departure time of the departure.
   *
   * @return scheduled departure time with delay added
   */
  public LocalTime getRealDepartureTime() {
    return departureTime.plusHours(delay.getHour()).plusMinutes(delay.getMinute());
  }


  /**
   * creates a string representation of the departure, hides track if -1 and delay if none.
   *
   * @return string containing departure time, line, train number, destination, track and delay
   */
  @Override
  public String toString() {

    String delayShown;
    String trackShown;

    if (delay == LocalTime.of(0, 0)) {
      delayShown = " ";
    } else {
      delayShown = delay.toString();
    }

    if (track == -1) {
      trackShown = " ";
    } else {
      trackShown = String.valueOf(track);
    }

    return String.format(
            "| %14s | %4s | %12s | %15s | %5s | %5s |",
            departureTime,
            line,
            trainNumber,
            destination,
            trackShown,
            delayShown
    );
  }
}
