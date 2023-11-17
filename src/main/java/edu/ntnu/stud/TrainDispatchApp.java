package edu.ntnu.stud;

import edu.ntnu.stud.models.DepartureInformationDisplay;
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

    while (running) {
      System.out.println("1. List all departures");
      System.out.println("2. Add a departure");
      System.out.println("3. Assign a track to a departure");
      System.out.println("4. Assign a delay to a departure");
      System.out.println("5. Search for a departure by its train number");
      System.out.println("6. Search for departures by their destination");
      System.out.println("7. Update the clock");
      System.out.println("8. Shut down the application");


      switch (input.nextInt()) {

        case 1:
          DepartureInformationDisplay.printDepartureList(departureRegistry.getSortedDepartures());
          break;
        case 2:
          System.out.println("Departure time (HH:mm format): ");
          String departureTime = input.next();
          System.out.println("Line: ");
          String line = input.next();
          System.out.println("Train number: ");
          int trainNumber = input.nextInt();
          System.out.println("Destination: ");
          String destination = input.next();
          System.out.println("Delay (HH:mm format): ");
          String delay = input.next();
          System.out.println("Track (-1 if no track is assigned): ");
          int track = input.nextInt();

          departureRegistry.addDeparture(departureTime, line, trainNumber, destination, track, delay);
          break;
        case 3:
          System.out.println("Train number: ");
          int trainNumberNewTrack = input.nextInt();
          System.out.println("Track: ");
          int newTrack = input.nextInt();
          departureRegistry.setTrackForDeparture(trainNumberNewTrack, newTrack);
          break;
        case 4:
          System.out.println("Train number: ");
          int trainNumberNewDelay = input.nextInt();
          System.out.println("Delay (HH:mm format): ");
          String newDelay = input.next();
          departureRegistry.setDelayForDeparture(trainNumberNewDelay, newDelay);
          break;
        case 5:
          System.out.println("Train number: ");
          DepartureInformationDisplay.printSingleDeparture(departureRegistry.getDepartureByTrainNumber(input.nextInt()));
          break;
        case 6:
          System.out.println("Destination: ");
          DepartureInformationDisplay.printDepartureList(departureRegistry.getDeparturesByDestination(input.next()));
          break;
        case 7:
          System.out.println("New time (HH:mm format): ");
          departureRegistry.removePassedDepartures(Utils.parseTimeString(input.next()));
          break;
        case 8:
          System.out.println("Shutting down...");
          running = false;
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




