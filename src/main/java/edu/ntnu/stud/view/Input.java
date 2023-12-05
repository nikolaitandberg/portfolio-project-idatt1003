package edu.ntnu.stud.view;

import edu.ntnu.stud.utils.TimeHandling;
import java.util.Scanner;

/**
 * A class for handling user input.
 */
public class Input {

  static Scanner scanner = new Scanner(System.in);

  private Input() {
    scanner.useDelimiter("\r?\n");
  }


  /**
   * Gets valid user input for time.
   *
   * @return the string input
   */
  static String getTime() {
    String departureTime = "";
    boolean validInput = false;

    while (!validInput) {
      try {
        departureTime = scanner.next();
        TimeHandling.parseTimeString(departureTime);
        validInput = true;
      } catch (IllegalArgumentException e) {
        System.out.println("input has to be in the format HH:mm, please try again: ");
        scanner.nextLine();
      }
    }
    return departureTime;
  }

  /**
   * Gets valid user input for line.
   *
   * @return the string input
   */
  static String getLine() {
    String line = "";
    boolean validInput = false;

    while (!validInput) {
      line = scanner.next();
      if (line.length() < 5) {
        validInput = true;
      } else {
        System.out.println("input has to be less than 5 characters, please try again: ");
        scanner.nextLine();
      }
    }
    return line;
  }

  /**
   * Gets valid user input for train number.
   *
   * @return the integer input
   */
  static int getTrainNumber() {
    int trainNumber = 0;

    boolean validInput = false;
    while (!validInput) {
      if (scanner.hasNextInt()) {
        trainNumber = scanner.nextInt();
        if (trainNumber > 0) {
          validInput = true;
        } else {
          System.out.println("input has to be positive, please try again: ");
          scanner.nextLine();
        }
      } else {
        System.out.println("input has to be an integer, please try again: ");
        scanner.next();
      }
    }
    return trainNumber;
  }

  /**
   * Gets valid user input for destination.'
   *
   * @return the string input
   */
  static String getDestination() {
    String destination = "";
    boolean validInput = false;

    while (!validInput) {
      destination = scanner.next();
      if (destination.length() < 12) {
        validInput = true;
      } else {
        System.out.println("input has to be less than 16 characters, please try again: ");
        scanner.nextLine();
      }
    }
    return destination;
  }

  /**
   * Gets valid user input for track.
   *
   * @return the integer input
   */
  static int getTrack() {
    int track = 0;

    boolean validInput = false;
    while (!validInput) {
      if (scanner.hasNextInt()) {
        track = scanner.nextInt();
        if (track  == -1 || track > 0) {
          validInput = true;
        } else {
          System.out.println(
                  "input has to be positive (or -1 if no track is assigned), please try again: "
          );
          scanner.nextLine();
        }
      } else {
        System.out.println("input has to be a an integer, please try again: ");
        scanner.next();
      }
    }
    return track;
  }

  /**
   * Gets valid user input for menu option.
   *
   * @return the integer input
   */
  static int getMenuOption() {
    int option = 0;

    boolean validInput = false;
    while (!validInput) {
      if (scanner.hasNextInt()) {
        option = scanner.nextInt();
        if (option > 0 && option < 9) {
          validInput = true;
        } else {
          System.out.println("input has to be between 0 and 9, please try again: ");
          scanner.nextLine();
        }
      } else {
        System.out.println("input has to be an integer, please try again: ");
        scanner.next();
      }
    }
    return option;
  }
}
