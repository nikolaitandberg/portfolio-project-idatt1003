package edu.ntnu.stud.models;

import edu.ntnu.stud.Utils;
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
     * Constructor.
     *
     * @param departureTime the time of day the train departs
     * @param line the line the train is travelling on
     * @param trainNumber the unique number of the departure
     * @param destination the last stop on the line
     * @param track the track at which the train arrives at the station
     * @param delay the amount of time the train is delayed compared to it's scheduled time
     */

  public Departure(
          String departureTime,
          String line,
          int trainNumber,
          String destination,
          int track,
          String delay
  ) {
    if (departureTime.isEmpty()) {
      throw new IllegalArgumentException("Departure time cannot be left empty");
    }
    this.departureTime = Utils.parseTimeString(departureTime);
    if (line.isEmpty()) {
      throw new IllegalArgumentException("Line cannot be left empty");
    }
    this.line = line;
    if (trainNumber < 1) {
      throw new IllegalArgumentException("Train number cannot be less than 1");
    }
    this.trainNumber = trainNumber;
    if (destination.isEmpty()) {
      throw new IllegalArgumentException("Destination cannot be left empty");
    }
    this.destination = destination;
    if (track < -1 || track == 0) {
      throw new IllegalArgumentException("Track cannot be 0 or a negative number other than -1");
    }
    this.track = track;
    if (delay.isEmpty()) {
      throw new IllegalArgumentException("Delay cannot be left empty");
    }
    this.delay = Utils.parseTimeString(delay);
  }

  // Setters
  public void setTrack(int track) {
    this.track = track;
  }

  /** set new delay.
 *
 * @param delay a string on format "HH:mm"
 */
  public void setDelay(String delay) {
    this.delay = Utils.parseTimeString(delay);
  }

  // Getters
  public LocalTime getDepartureTime() {
    return departureTime;
  }

  public String getLine() {
    return line;
  }

  public int getTrainNumber() {
    return trainNumber;
  }

  public String getDestination() {
    return destination;
  }

  public int getTrack() {
    return track;
  }

  public LocalTime getDelay() {
    return delay;
  }

}
