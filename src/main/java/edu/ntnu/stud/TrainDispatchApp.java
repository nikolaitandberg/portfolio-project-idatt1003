package edu.ntnu.stud;

import edu.ntnu.stud.view.UserInterface;


/**
 * This is the application class for the train dispatch system.
 *
 * @author Nikolai Tandberg
 * @version 1.0
 * @since 2023-13-11
 */
public class TrainDispatchApp {
  /**
   * Main method for the train dispatch application, calls for init() and start().
   *
   * @param args not used in this application
   */
  public static void main(String[] args) {
    UserInterface.init();
    UserInterface.start();
  }

}




