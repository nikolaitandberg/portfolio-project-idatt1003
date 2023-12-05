package edu.ntnu.stud.view;

import edu.ntnu.stud.models.Departure;
import edu.ntnu.stud.models.DepartureRegistry;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Class for displaying information to the user.
 *
 * @author Nikolai Tandberg
 * @version 1.0
 * @since 2023-11-21
 */
public class UserInterface {
  private static DepartureRegistry departureRegistry;
  private static boolean running;
  private static final String TRAIN_NUMBER_REQUEST = "Train number: ";

  private UserInterface() {}

  /**
   * initializes registry needed to run application and adds some starting data.
   */
  public static void init() {
    departureRegistry = new DepartureRegistry();
    running = true;

    departureRegistry.addDeparture("14:00", "L2", 55, "Drammen", 2, "00:10");
    departureRegistry.addDeparture("09:00", "R13", 19, "Lillestrøm", 3, "00:00");
    departureRegistry.addDeparture("08:45", "R11", 77, "Arendal", 1, "00:20");
    departureRegistry.addDeparture("09:22", "R60", 456, "Hamar", -1, "00:15");

  }

  /**
   * Starts the application.
   */
  public static void start() {

    System.out.println("Welcome to the train dispatch application!");

    while (running) {
      System.out.println(
              "\nPlease choose an option below by typing the corresponding number (1-8): "
      );
      System.out.println("1. List all departures");
      System.out.println("2. Add a departure");
      System.out.println("3. Assign a track to a departure");
      System.out.println("4. Assign a delay to a departure");
      System.out.println("5. Search for a departure by its train number");
      System.out.println("6. Search for departures by their destination");
      System.out.println("7. Update the clock");
      System.out.println("8. Shut down the application");

      switch (Input.getMenuOption()) {

        case 1:
          listAllDepartures();
          break;
        case 2:
          addDeparture();
          break;
        case 3:
          assignTrackToDeparture();
          break;
        case 4:
          assignDelayToDeparture();
          break;
        case 5:
          searchForDepartureByTrainNumber();
          break;
        case 6:
          searchForDeparturesByDestination();
          break;
        case 7:
          updateClock();
          break;
        case 8:
          System.out.println("Shutting down...");
          running = false;
          break;
        default:
          System.out.println("Input has to be an integer (1 - 8)");
      }
    }


  }

  /**
   * Prints all departures in registry to the terminal.
   *
   */
  private static void listAllDepartures() {
    try {
      printDepartureList(departureRegistry.getDepartures());
    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Adds a departure to the registry.
   *
   */
  private static void addDeparture() {
    System.out.println("Departure time (HH:mm format): ");
    String departureTime = Input.getTime();

    System.out.println("Line: ");
    String line = Input.getLine();

    System.out.println(TRAIN_NUMBER_REQUEST);
    int trainNumber = Input.getTrainNumber();

    System.out.println("Destination: ");
    String destination = Input.getDestination();

    System.out.println("Delay (HH:mm format): ");
    String delay = Input.getTime();

    System.out.println("Track (-1 if no track is assigned): ");
    int track = Input.getTrack();

    try {
      departureRegistry.addDeparture(
              departureTime,
              line,
              trainNumber,
              destination,
              track,
              delay);
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }

  }

  /**
   * Assigns a track to a departure.
   *
   */
  private static void assignTrackToDeparture() {
    System.out.println(TRAIN_NUMBER_REQUEST);
    int trainNumber = Input.getTrainNumber();
    System.out.println("New track: ");
    int newTrack = Input.getTrack();
    try {
      departureRegistry.setTrackForDeparture(trainNumber, newTrack);
    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Assigns a delay to a departure.
   *
   */
  private static void assignDelayToDeparture() {
    System.out.println(TRAIN_NUMBER_REQUEST);
    int trainNumber = Input.getTrainNumber();
    System.out.println("Delay (HH:mm format): ");
    String newDelay = Input.getTime();
    try {
      departureRegistry.setDelayForDeparture(trainNumber, newDelay);
    } catch (NoSuchElementException | IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Searches for a departure by its train number.
   *
   */
  private static void searchForDepartureByTrainNumber() {
    System.out.println(TRAIN_NUMBER_REQUEST);
    try {
      printDeparture(
              departureRegistry.getDepartureByTrainNumber(Input.getTrainNumber())
      );
    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Searches for departures by their destination.
   *
   */
  private static void searchForDeparturesByDestination() {
    System.out.println("Destination: ");
    try {
      printDepartureList(
              departureRegistry.getDeparturesByDestination(Input.getDestination())
      );
    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Updates the clock.
   *
   */
  private static void updateClock() {
    System.out.println("New time (HH:mm format): ");
    try {
      departureRegistry.setClock(Input.getTime());
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Prints a list of departures in a table to the terminal.
   *
   * @param departureList the list of departures to print
   */
  private static void printDepartureList(List<Departure> departureList) {
    printTable(
            departureList.stream().map(Departure::toString).collect(Collectors.joining("\n"))
    );
  }

  /**
   * Prints a single departure in a table to the terminal.
   *
   * @param departure the departure to print
   */
  private static void printDeparture(Departure departure) {
    printTable(departure.toString());
  }

  /**
   * Prints a table with labels to the terminal.
   *
   * @param items the items to print in the table
   */
  private static void printTable(String items) {
    System.out.println(
            "--------------------------------------------------------------------------"
    );
    System.out.printf("| %21s %28s %19s |",
            "Train dispatch system", "", "CURRENT TIME: " + departureRegistry.getClock());
    System.out.println(
            "\n--------------------------------------------------------------------------"
    );
    System.out.printf(
            "| %14s | %4s | %12s | %15s | %5s | %5s |",
            "DEPARTURE TIME", "LINE", "TRAIN NUMBER", "DESTINATION", "TRACK", "DELAY"
    );
    System.out.println(
            "\n--------------------------------------------------------------------------"
    );
    System.out.println(items);
    System.out.println(
            "--------------------------------------------------------------------------"
    );
  }
}
