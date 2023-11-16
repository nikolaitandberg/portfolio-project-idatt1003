package edu.ntnu.stud.models;

import edu.ntnu.stud.Utils;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * A class representing the departure registry.
 *
 * @author Nikolai Tandberg
 * @version 1.0
 * @since 2023-11-9
 */
public class DepartureRegistry {
  private final ArrayList<Departure> departures;

  public DepartureRegistry() {
    this.departures = new ArrayList<>();
  }

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
    for (Departure departure : departures) {
      if (departure.getTrainNumber() == trainNumber) {
        return true;
      }
    }
    return false;
  }

  /**
   * Gets a departure by its train number.
   *
   * @param trainNumber the unique number of the departure
   * @return the departure that has the matching train number
   */
  public Departure getDepartureByTrainNumber(int trainNumber) {
    for (Departure departure : departures) {
      if (departure.getTrainNumber() == trainNumber) {
        return departure;
      }
    }
    throw new NoSuchElementException("No Departure with this train number was found");
  }

  /**
   * gets all the departures going to a given destination.
   *
   * @param destination the destination the departures are checked for
   * @return String with all the departures with a matching destination
   */
  public String getDeparturesByDestination(String destination) {
    StringBuilder departuresWithDestination = new StringBuilder();
    for (Departure departure : departures) {
      if (Objects.equals(departure.getDestination(), destination)) {
        departuresWithDestination.append(departure);
      }
    }
    return departuresWithDestination.toString();
  }

  /**
   * removes all the departures before a given time.
   *
   * @param newTime all departures before this time are removed
   */
  public void removePassedDepartures(String newTime) {
    LocalTime newTimeObj = Utils.parseTimeString(newTime);
    for (Departure departure : departures) {
      LocalTime realDeparture = Utils.addDelay(departure.getDepartureTime(), departure.getDelay());
      if (realDeparture.isBefore(newTimeObj)) {
        departures.remove(departure);
      }
    }
  }

  /**
   * Gets all departures in registry sorted in ascending order by departure time.
   *
   * @return the ArrayList of the sorted departures
   */
  public ArrayList<Departure> getSortedDepartures() {
    ArrayList<Departure> sortedDepartures = departures;
    sortedDepartures.sort(Comparator.comparing(Departure::getDepartureTime));
    return sortedDepartures;
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
