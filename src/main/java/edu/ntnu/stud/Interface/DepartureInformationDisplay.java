package edu.ntnu.stud.Interface;

import edu.ntnu.stud.models.Departure;

import java.time.LocalTime;
import java.util.List;

/**
 * Class for displaying departure information in a neat manner in the console.
 *
 * @author nikolaitandberg
 * @since 2023-11-17
 * @version 1.0
 */
public class DepartureInformationDisplay {

  /** Prints a list of departures with labels to the terminal.
   *
   * @param departureList the list of departures to print
   */
  public static void printDepartureList(List<Departure> departureList) {
    System.out.println(
            "----------------------------------------------------------------------"
    );
    System.out.printf(
            "| %14s | %4s | %12s | %11s | %5s | %5s |",
            "DEPARTURE TIME", "LINE", "TRAIN NUMBER", "DESTINATION", "TRACK", "DELAY"
    );
    System.out.println(
            "\n----------------------------------------------------------------------"
    );
    for (Departure departure : departureList) {

      if (departure.getDelay() == LocalTime.parse("00:00")) {
        System.out.printf("| %14s | %4s | %12s | %11s | %5s |       |\n",
                departure.getDepartureTime(),
                departure.getLine(),
                departure.getTrainNumber(),
                departure.getDestination(),
                departure.getTrack());
      } else {
        System.out.printf("| %14s | %4s | %12s | %11s | %5s | %5s |\n",
                departure.getDepartureTime(),
                departure.getLine(),
                departure.getTrainNumber(),
                departure.getDestination(),
                departure.getTrack(),
                departure.getDelay());
      }
    }
    System.out.println(
            "----------------------------------------------------------------------"
    );
  }

  /** Prints a single departure with labels to the terminal.
   *
   * @param departure the departure to print
   */
  public static void printSingleDeparture(Departure departure) {
    System.out.println(
            "----------------------------------------------------------------------"
    );
    System.out.printf(
            "| %14s | %4s | %12s | %11s | %5s | %5s |",
            "DEPARTURE TIME", "LINE", "TRAIN NUMBER", "DESTINATION", "TRACK", "DELAY"
    );
    System.out.println(
            "\n----------------------------------------------------------------------"
    );

    if (departure.getDelay() == LocalTime.parse("00:00")) {
      System.out.printf("| %14s | %4s | %12s | %11s | %5s |       |\n",
              departure.getDepartureTime(),
              departure.getLine(),
              departure.getTrainNumber(),
              departure.getDestination(),
              departure.getTrack());
    } else {
      System.out.printf("| %14s | %4s | %12s | %11s | %5s | %5s |\n",
              departure.getDepartureTime(),
              departure.getLine(),
              departure.getTrainNumber(),
              departure.getDestination(),
              departure.getTrack(),
              departure.getDelay());
    }

    System.out.println(
            "----------------------------------------------------------------------"
    );
  }

}
