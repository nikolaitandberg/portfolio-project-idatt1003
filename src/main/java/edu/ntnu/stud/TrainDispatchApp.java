package edu.ntnu.stud;

/**
 * This is the application class for the train dispatch system, containing the main method.
 *
 * @author nikolaitandberg
 * @version 1.0
 * @since 2023-13-11
 */
public class TrainDispatchApp {
  private static DepartureRegistry departureRegistry;

  /**
   * initializes registry needed to run application and adds some starting data.
   */
  private static void init() {
    departureRegistry = new DepartureRegistry();

    departureRegistry.addDeparture("14:00", "L2", 55, "Drammen", 2, "00:10");
    departureRegistry.addDeparture("09:00", "R13", 19, "Lillestrøm", 3, "00:00");
    departureRegistry.addDeparture("08:45", "R11", 77, "Arendal", 1, "00:20");

  }

  /**
   * Runs the user interface.
   */
  private static void start() {

    // TODO: switchcase and while-loop


    System.out.println(departureRegistry.getDeparturesByDestination("Drammen"));
  }

  public static void main(String[] args) {
    init();
    start();
  }

}




