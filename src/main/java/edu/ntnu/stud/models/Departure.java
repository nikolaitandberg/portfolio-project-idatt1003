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
    this.departureTime = Utils.parseTimeString(departureTime);
    this.line = line;
    this.trainNumber = trainNumber;
    this.destination = destination;
    this.track = track;
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
