package edu.ntnu.stud.view;

import edu.ntnu.stud.utils.TimeHandling;
import java.time.LocalTime;
import java.util.Scanner;

/**
 * A class for fetching and validating user input.
 *
 * @author Nikolai Tandberg
 * @version 1.0
 * @since 2023-12-6
 */
public class Input {

  static Scanner scanner = new Scanner(System.in);

  private Input() {}

  /**
   * Fetches and validates user input for a clock time of format HH:mm.
   *
   * @return fetched time as LocalTime object
   */
  static LocalTime getTime() {
    LocalTime time = null;
    boolean validInput = false;

    while (!validInput) {
      try {
        time = TimeHandling.parseTimeString(scanner.nextLine());
        validInput = true;
      } catch (IllegalArgumentException e) {
        System.out.println("input must be of format HH:mm, please try again: ");
      }
    }
    return time;
  }

/**
   * Fetches and validates user input for a line.
   *
   * @return fetched line as String
   */
  static String getLine() {
    String line = "";
    boolean validInput = false;

    while (!validInput) {
      line = scanner.nextLine();
      if (line.length() < 5) {
        validInput = true;
      } else {
        System.out.println("input must be less than 5 characters, please try again: ");
      }
    }
    return line;
  }

/**
   * Fetches and validates user input for a train number.
   *
   * @return fetched train number as int
   */
  static int getTrainNumber() {
    String trainNumber = "";
    boolean validInput = false;

    while (!validInput) {
      trainNumber = scanner.nextLine();
      try {
        if (Integer.parseInt(trainNumber) > 0 && Integer.parseInt(trainNumber) < 10000) {
          validInput = true;
        } else {
          System.out.println("input must be in range [1-9999], please try again: ");
        }
      } catch (NumberFormatException e) {
        System.out.println("input must be an integer, please try again: ");
      }
    }
    return Integer.parseInt(trainNumber);
  }

/**
   * Fetches and validates user input for a destination.
   *
   * @return fetched destination as String
   */
  static String getDestination() {
    String destination = "";
    boolean validInput = false;

    while (!validInput) {
      destination = scanner.nextLine();
      if (destination.length() < 16) {
        validInput = true;
      } else {
        System.out.println("input has to be less than 16 characters, please try again: ");
      }
    }
    return destination;
  }

/**
   * Fetches and validates user input for a track.
   *
   * @return fetched track as int
   */
  static int getTrack() {
    String track = "";
    boolean validInput = false;

    while (!validInput) {
      track = scanner.nextLine();
      try {
        if (Integer.parseInt(track) > 0 && Integer.parseInt(track) < 10000 || Integer.parseInt(track) == -1) {
          validInput = true;
        } else {
          System.out.println("track must be in range [1-9999] or -1 if no track is assigned, please try again: ");
        }
      } catch (NumberFormatException e) {
        System.out.println("track must be an integer, please try again: ");
      }
    }
    return Integer.parseInt(track);
  }

/**
   * Fetches and validates user input for a menu option.
   *
   * @return fetched menu option as int
   */
  static int getMenuOption() {
    String option = "";
    boolean validInput = false;

    while (!validInput) {
      option = scanner.nextLine();
      try {
        if (Integer.parseInt(option) > 0 && Integer.parseInt(option) < 9) {
          validInput = true;
        } else {
          System.out.println("input must be in range [1-8], please try again: ");
        }
      } catch (NumberFormatException e) {
        System.out.println("input must be an integer, please try again: ");
      }
    }
    return Integer.parseInt(option);
  }
}
