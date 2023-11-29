package edu.ntnu.stud.models;

import edu.ntnu.stud.utils.TimeHandling;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * A class representing the departure registry.
 *
 * @author Nikolai Tandberg
 * @version 1.0
 * @since 2023-11-9
 */
public class DepartureRegistry {
  private final ArrayList<Departure> departures = new ArrayList<>();

  private LocalTime clock = LocalTime.of(0, 0);

  public DepartureRegistry() {}

  /**
   * Adds a single departure to the registry.
   *
   * @param departureTime the time of day the train departs
   * @param line          the line the train is travelling on
   * @param trainNumber   the unique number of the departure
   * @param destination   the last stop on the line
   * @param track         the track at which the train arrives at the station
   * @param delay         the amount of time the train is delayed compared to it's scheduled time
   * @throws IllegalArgumentException
   *         if a departure with the same train number is already registered
   */
  public void addDeparture(
          String departureTime,
          String line,
          int trainNumber,
          String destination,
          int track,
          String delay
  ) throws IllegalArgumentException {
    if (checkIfDepartureWithTrainNumberExists(trainNumber)) {
      throw new IllegalArgumentException(
              "A departure with this train number is already registered!"
      );
    } else {
      Departure newDeparture = new Departure(
              departureTime,
              line,
              trainNumber,
              destination,
              track,
              delay
      );
      departures.add(newDeparture);
    }
  }

  /**
   * Checks if there is a departure registered with train number.
   *
   * @param trainNumber The train number that's checked to see if it's in use
   * @return a boolean value depending on if the train number is in use
   */
  private boolean checkIfDepartureWithTrainNumberExists(int trainNumber) {
    return departures.stream().anyMatch(departure -> departure.getTrainNumber() == trainNumber);
  }

  /**
   * Gets one single departure with a given train number.
   *
   * @param trainNumber the train number of the departure
   * @return A list containing the departure with the given train number
   * @throws NoSuchElementException if no departure with the given train number exists
   */
  private Departure getDepartureByTrainNumber(int trainNumber) throws NoSuchElementException {
    return departures.stream()
            .filter(departure -> departure.getTrainNumber() == trainNumber)
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException(
                    "No departure with this train number exists!"
            ));
  }
  
  public String getDepartureByTrainNumberString(int trainNumber) throws NoSuchElementException{
    return getDepartureByTrainNumber(trainNumber).toString();
  }

  /**
   * Checks if there is a departure registered with destination.
   *
   * @param destination The destination that's checked to see if it's in use
   * @return a boolean value depending on if the destination is in use
   */
  private boolean checkIfDepartureWithDestinationExists(String destination) {
    return departures.stream().anyMatch(
            departure -> departure.getDestination().equals(destination)
    );
  }

  /**
   * Gets all departures with a given destination in ascending order by departure time.
   *
   * @param destination the destination of the departures
   * @return A list of departures with the given destination
   * @throws NoSuchElementException if no departure with the given destination exists
   */
  public String getDeparturesByDestination(String destination)
          throws NoSuchElementException {
    if (checkIfDepartureWithDestinationExists(destination)) {
      return departures.stream()
              .filter(departure -> departure.getDestination().equals(destination))
              .sorted(Comparator.comparing(Departure::getDepartureTime))
              .map(Departure::toString)
              .collect(Collectors.joining("\n"));
    } else {
      throw new NoSuchElementException("No departure with this destination exists!");
    }
  }

  /**
   * Gets all departures in registry sorted in ascending order by departure time.
   *
   * @return an ArrayList of the sorted departures
   * @throws NoSuchElementException if no departures are registered
   */
  public String getSortedDepartures() throws NoSuchElementException {
    if (!departures.isEmpty()) {
      return departures.stream()
              .sorted(Comparator.comparing(Departure::getDepartureTime))
              .map(Departure::toString).collect(Collectors.joining("\n"));
    } else {
      throw new NoSuchElementException("No departures are registered!");
    }
  }

  /**
   * gets clock for the DepartureRegistry
   *
   * @return the clock time as a localTime object
   */
  public LocalTime getClock() {
    return clock;
  }

  /**
   * Sets new track for a specific departure in the departure registry.
   *
   * @param trainNumber the number of the train for the departure
   * @param track the track the train of the departure is assigned to
   */
  public void setTrackForDeparture(int trainNumber, int track) {
    getDepartureByTrainNumber(trainNumber).setTrack(track);
  }

  /**
   * Sets new delay for a specific departure in the departure registry.
   *
   * @param trainNumber the number of the train for the departure
   * @param delay updated delay for the specific departure
   */
  public void setDelayForDeparture(int trainNumber, String delay) {
    getDepartureByTrainNumber(trainNumber).setDelay(delay);
  }

  /**
   * Sets clock to a new time and removes past departures.
   *
   * @param newTime the new time
   * @throws IllegalArgumentException if the new time is before the current time
   */
  public void setClock(String newTime) throws IllegalArgumentException {
    if (TimeHandling.parseTimeString(newTime).isBefore(clock)) {
      throw new IllegalArgumentException("New time cannot be before current time!");
    }
    clock = TimeHandling.parseTimeString(newTime);
    removePastDepartures();
  }

  /**
   * removes all departures before current time from departure registry.
   *
   */
  private void removePastDepartures() {
    departures.removeIf(
            departure -> TimeHandling.addDelay(departure.getDepartureTime(), departure.getDelay())
            .isBefore(clock));
  }
}
