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
   */
  public void addDeparture(
          String departureTime,
          String line,
          int trainNumber,
          String destination,
          int track,
          String delay
  ) {
    if (checkIfTrainNumberExists(trainNumber)) {
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
  public boolean checkIfTrainNumberExists(int trainNumber) {
    return departures.stream().anyMatch(departure -> departure.getTrainNumber() == trainNumber);
  }

  /**
   * Gets a single departure with a given train number.
   *
   * @param trainNumber the train number of the departure
   * @return the departure with the given train number
   * @throws NoSuchElementException if no departure with the given train number exists
   */
  public Departure getDepartureByTrainNumber(int trainNumber) {
    if (checkIfTrainNumberExists(trainNumber)) {
      return departures.stream()
              .filter(departure -> departure.getTrainNumber() == trainNumber)
              .findFirst()
              .orElseThrow(NoSuchElementException::new);
    } else {
      throw new NoSuchElementException("No departure with this train number exists!");
    }
  }

  /**
   * Gets all departures with a given destination.
   *
   * @param destination the destination of the departures
   * @return an ArrayList of departures with the given destination
   */
  public ArrayList<Departure> getDeparturesByDestination(String destination) {
    return departures.stream()
                    .filter(departure -> departure.getDestination().equalsIgnoreCase(destination))
                    .collect(Collectors.toCollection(ArrayList::new));
  }

  /**
   * removes all the departures before a given time.
   *
   * @param newTime all departures before this time are removed
   */
  public void removePassedDepartures(LocalTime newTime) {
    departures.removeIf(
        departure -> TimeHandling.addDelay(departure.getDepartureTime(), departure.getDelay())
        .isBefore(newTime));
  }

  /**
   * Gets all departures in registry sorted in ascending order by departure time.
   *
   * @return an ArrayList of the sorted departures
   */
  public ArrayList<Departure> getSortedDepartures() {
    return departures.stream()
            .sorted(Comparator.comparing(Departure::getDepartureTime))
            .collect(Collectors.toCollection(ArrayList::new));
  }

  /**
   * Sets a new track for a specific departure in the departure registry.
   *
   * @param trainNumber the number of the train for the departure
   * @param track the track the train of the departure is assigned to
   */
  public void setTrackForDeparture(int trainNumber, int track) {
    getDepartureByTrainNumber(trainNumber).setTrack(track);
  }

  /**
   * Sets a new delay for a specific departure in the departure registry.
   *
   * @param trainNumber the number of the train for the departure
   * @param delay updated delay for the specific departure
   */
  public void setDelayForDeparture(int trainNumber, String delay) {
    getDepartureByTrainNumber(trainNumber).setDelay(delay);
  }


}
