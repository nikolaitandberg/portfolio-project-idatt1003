package edu.ntnu.stud.view;

import edu.ntnu.stud.models.Departure;
import edu.ntnu.stud.models.DepartureRegistry;
import edu.ntnu.stud.utils.TimeHandling;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Class for handling user input and displaying information to the user.
 */
public class UserInterface {
  private static DepartureRegistry departureRegistry;
  static Scanner input;
  static boolean running;

  /**
   * initializes registry needed to run application and adds some starting data.
   */
  public static void init() {
    departureRegistry = new DepartureRegistry();

    input = new Scanner(System.in);
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
      System.out.println("\nPlease choose an option from the menu below by typing the corresponding number (1-8): ");
      System.out.println("1. List all departures");
      System.out.println("2. Add a departure");
      System.out.println("3. Assign a track to a departure");
      System.out.println("4. Assign a delay to a departure");
      System.out.println("5. Search for a departure by its train number");
      System.out.println("6. Search for departures by their destination");
      System.out.println("7. Update the clock");
      System.out.println("8. Shut down the application");

      switch (getValidMenuOptionInput()) {

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
   * Prints all departures in registry in the terminal.
   *
   */
  private static void listAllDepartures() {
    try {
      printDepartureList(departureRegistry.getSortedDepartures());
    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Adds a departure to the registry through user input.
   *
   */
  private static void addDeparture() {
    System.out.println("Departure time (HH:mm format): ");
    String departureTime = getValidDepartureTimeInput();

    System.out.println("Line: ");
    String line = getValidLineInput();

    System.out.println("Train number: ");
    int trainNumber = getValidTrainNumberInput();

    System.out.println("Destination: ");
    String destination = getValidDestinationInput();

    System.out.println("Delay (HH:mm format): ");
    String delay = getValidDepartureTimeInput();

    System.out.println("Track (-1 if no track is assigned): ");
    int track = getValidTrackInput();

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
   * Assigns a track to a departure through user input.
   *
   */
  private static void assignTrackToDeparture() {
    System.out.println("Train number: ");
    int trainNumberNewTrack = getValidTrainNumberInput();
    System.out.println("New track: ");
    int newTrack = getValidTrackInput();
    try {
      departureRegistry.setTrackForDeparture(trainNumberNewTrack, newTrack);
    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Assigns a delay to a departure through user input.
   *
   */
  private static void assignDelayToDeparture() {
    System.out.println("Train number: ");
    int trainNumberNewDelay = getValidTrainNumberInput();
    System.out.println("Delay (HH:mm format): ");
    String newDelay = getValidDepartureTimeInput();
    try {
      departureRegistry.setDelayForDeparture(trainNumberNewDelay, newDelay);
    } catch (NoSuchElementException | IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Searches for a departure by its train number through user input.
   *
   */
  private static void searchForDepartureByTrainNumber() {
    System.out.println("Train number: ");
    try {
      printDepartureList(
              departureRegistry.getDepartureByTrainNumber(getValidTrainNumberInput())
      );
    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Searches for departures by their destination through user input.
   *
   */
  private static void searchForDeparturesByDestination() {
    System.out.println("Destination: ");
    try {
      printDepartureList(
              departureRegistry.getDeparturesByDestination(getValidDestinationInput())
      );
    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Updates the clock through user input.
   *
   */
  private static void updateClock() {
    System.out.println("New time (HH:mm format): ");
    try {
      departureRegistry.setClock(getValidDepartureTimeInput());
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }

  private static String getValidDepartureTimeInput() {
    String departureTime = "";
    boolean validInput = false;

    while (!validInput) {
      try {
        departureTime = input.next();
        TimeHandling.parseTimeString(departureTime);
        validInput = true;
      } catch (IllegalArgumentException e) {
        System.out.println("input has to be in the format HH:mm, please try again: ");
        input.nextLine();
      }
  }
    return departureTime;
  }

  private static String getValidLineInput() {
    String line = "";
    boolean validInput = false;

    while (!validInput) {
      line = input.next();
      if (line.length() < 5) {
        validInput = true;
      } else {
        System.out.println("input has to be less than 5 characters, please try again: ");
        input.nextLine();
      }
  }
    return line;
  }

  private static int getValidTrainNumberInput() {
    int trainNumber = 0;

    boolean validInput = false;
    while (!validInput) {
      try {
        trainNumber = input.nextInt();
        if (trainNumber < 10000 && trainNumber > 0) {
          validInput = true;
        } else {
          System.out.println("input has to be a an integer (1 - 10000), please try again: ");
          input.nextLine();
        }
      } catch (InputMismatchException e) {
        System.out.println("input has to be an integer (1 - 10000), please try again: ");
        input.next();
      }
    }
    return trainNumber;
  }

  private static String getValidDestinationInput() {
    String destination = "";
    boolean validInput = false;

    while (!validInput) {
      destination = input.next();
      if (destination.length() < 12) {
        validInput = true;
      } else {
        System.out.println("input has to be less than 16 characters, please try again: ");
        input.nextLine();
      }
    }
    return destination;
  }

  private static int getValidTrackInput() {
    int track = 0;

    boolean validInput = false;
    while (!validInput) {
      try {
        track = input.nextInt();
        if (track < 10000 && track > 0 || track == -1) {
          validInput = true;
        } else {
          System.out.println("input has to be a an integer (1 - 10000), or -1 if no track is assigned yet, please try again: ");
          input.nextLine();
        }
      } catch (InputMismatchException e) {
        System.out.println("input has to be an integer (1 - 10000), or -1 if no track is assigned yet, please try again: ");
        input.next();
      }
    }
    return track;
  }

  private static int getValidMenuOptionInput() {
    int menuOption = 0;

    boolean validInput = false;
    while (!validInput) {
      try {
        menuOption = input.nextInt();
        if (menuOption < 9 && menuOption > 0) {
          validInput = true;
        } else {
          System.out.println("Input has to be an integer (1 - 8), please try again: ");
          input.nextLine();
        }
      } catch (InputMismatchException e) {
        System.out.println("Input has to be an integer (1 - 8), please try again: ");
        input.next();
      }
    }
    return menuOption;
  }

  /** Prints a list of departures with labels to the terminal.
   *
   * @param departureList the list of departures to print
   */
  private static void printDepartureList(List<Departure> departureList) {
    System.out.println(
            "--------------------------------------------------------------------------"
    );
    System.out.println("| Train dispatch system                              CURRENT TIME: " + departureRegistry.getClock() + " |");
    System.out.println(
            "--------------------------------------------------------------------------"
    );
    System.out.printf(
            "| %14s | %4s | %12s | %15s | %5s | %5s |",
            "DEPARTURE TIME", "LINE", "TRAIN NUMBER", "DESTINATION", "TRACK", "DELAY"
    );
    System.out.println(
            "\n--------------------------------------------------------------------------"
    );
    for (Departure departure : departureList) {

      String track;

      if (departure.getTrack() == -1) {
        track = "";
      } else {
        track = Integer.toString(departure.getTrack());
      }

      String delay;

      if (departure.getDelay().equals(TimeHandling.parseTimeString("00:00"))) {
        delay = "";
      } else {
        delay = departure.getDelay().toString();
      }

      System.out.printf("| %14s | %4s | %12s | %15s | %5s | %5s |\n",
              departure.getDepartureTime(),
              departure.getLine(),
              departure.getTrainNumber(),
              departure.getDestination(),
              track,
              delay);
    }
    System.out.println(
            "--------------------------------------------------------------------------"
    );
  }
}
