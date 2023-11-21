package edu.ntnu.stud.Interface;

import edu.ntnu.stud.models.DepartureRegistry;
import edu.ntnu.stud.utils.TimeHandling;

import java.util.Scanner;

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

  public static void start() {

    while (running) {
      System.out.println("1. List all departures");
      System.out.println("2. Add a departure");
      System.out.println("3. Assign a track to a departure");
      System.out.println("4. Assign a delay to a departure");
      System.out.println("5. Search for a departure by its train number");
      System.out.println("6. Search for departures by their destination");
      System.out.println("7. Update the clock");
      System.out.println("8. Shut down the application");
      input = new Scanner(System.in);

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
          int trainNumber;
          try {
            trainNumber = input.nextInt();
          } catch(Exception e) {
            System.out.println("input has to be an integer");
            break;
          }

          System.out.println("Destination: ");
          String destination = input.next();
          System.out.println("Delay (HH:mm format): ");
          String delay = input.next();
          System.out.println("Track (-1 if no track is assigned): ");
          int track = input.nextInt();

          try {
            departureRegistry.addDeparture(
                    departureTime,
                    line,
                    trainNumber,
                    destination,
                    track,
                    delay);
          } catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
          }

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
          departureRegistry.removePassedDepartures(TimeHandling.parseTimeString(input.next()));
          break;
        case 8:
          System.out.println("Shutting down...");
          running = false;
          break;
        default:
          System.out.println("please enter a number from 1 through 8");


      }
    }
  }



}
