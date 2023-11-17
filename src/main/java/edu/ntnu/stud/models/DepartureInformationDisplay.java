package edu.ntnu.stud.models;


import java.time.LocalTime;
import java.util.ArrayList;

public class DepartureInformationDisplay {

  public static void printDepartureList(ArrayList<Departure> departureList){
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

  public static void printSingleDeparture(Departure departure){
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
