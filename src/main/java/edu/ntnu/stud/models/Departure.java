package edu.ntnu.stud.models;

import edu.ntnu.stud.utils.TimeHandling;
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
   * Constructs an object of the Departure class.
   * Takes the following parameters
   * May throw IllegalArgumentException
   *
   * @param departureTime the time of day the train departs
   * @param line the train is travelling on
   * @param trainNumber the unique number of the departure
   * @param destination the last stop on the line
   * @param track the track at which the train arrives at the station
   * @throws IllegalArgumentException When line is empty,
   *                                  when train number is less than 1,
   *                                  when destination is empty,
   *                                  when track is 0 or a negative number other than -1
   *                                  when delay or departureTime is not of format "HH:mm"
   *                                  see {@link TimeHandling#parseTimeString(String)}
   *                                  for more information
   */
  public Departure(
          String departureTime,
          String line,
          int trainNumber,
          String destination,
          int track,
          String delay
  ) throws IllegalArgumentException {
    if (line.isEmpty()) {
      throw new IllegalArgumentException("Line cannot be left empty");
    }
    if (trainNumber < 1) {
      throw new IllegalArgumentException("Train number cannot be less than 1");
    }
    if (destination.isEmpty()) {
      throw new IllegalArgumentException("Destination cannot be left empty");
    }

    this.departureTime = TimeHandling.parseTimeString(departureTime);
    this.line = line;
    this.trainNumber = trainNumber;
    this.destination = destination;
    this.setTrack(track);
    this.setDelay(delay);
  }

  // Setters

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
   * set new delay and uses TimeHandling class to parse from string parameter to LocalTime object.
   *
   * @param delay a string on format "HH:mm"
   * @throws IllegalArgumentException if the string is not of format "HH:mm"
   * @see TimeHandling#parseTimeString(String) for more information
   */
  public void setDelay(String delay) {
    this.delay = TimeHandling.parseTimeString(delay);
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
