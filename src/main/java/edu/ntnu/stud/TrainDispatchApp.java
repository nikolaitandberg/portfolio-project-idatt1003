package edu.ntnu.stud;

import edu.ntnu.stud.models.Departure;
import edu.ntnu.stud.models.DepartureRegistry;
import java.util.Scanner;

/**
 * This is the application class for the train dispatch system.
 *
 * @author nikolaitandberg
 * @version 1.0
 * @since 2023-13-11
 */
public class TrainDispatchApp {
  private static DepartureRegistry departureRegistry;
  static Scanner input;
  static boolean running;

  /**
   * initializes registry needed to run application and adds some starting data.
   */
  private static void init() {
    departureRegistry = new DepartureRegistry();

    input = new Scanner(System.in);
    running = true;

    departureRegistry.addDeparture("14:00", "L2", 55, "Drammen", 2, "00:10");
    departureRegistry.addDeparture("09:00", "R13", 19, "Lillestrøm", 3, "00:00");
    departureRegistry.addDeparture("08:45", "R11", 77, "Arendal", 1, "00:20");
    departureRegistry.addDeparture("09:22", "R60", 456, "Hamar", -1, "00:15");

  }

  /**
   * Runs the user interface.
   */
  private static void start() {

    // TODO: switchcase and while-loop




    while (running) {
      System.out.println("1. List all departures");
      System.out.println("2. Add a departure");
      System.out.println("3. Assign a track to a departure");
      System.out.println("4. Assign a delay to a departure");
      System.out.println("5. Search for a departure by its train number");
      System.out.println("6. Search for departures by their destination");
      System.out.println("7. Update the clock");
      System.out.println("8. Shit down the application");


      switch (input.nextInt()) {

        case 1:





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
          for (Departure departure : departureRegistry.getSortedDepartures()) {
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

          break;
        case 2:

          break;
        case 3:
          break;
        case 4:
          break;
        case 5:
          break;
        case 6:
          break;
        case 7:
          break;
        case 8:
          System.out.println("Shutting down...");
          break;


      }
    }
  }

  /**
   * Main method for the train dispatch application, calls for init() and start().
   *
   * @param args
   */
  public static void main(String[] args) {
    init();
    start();
  }

}




