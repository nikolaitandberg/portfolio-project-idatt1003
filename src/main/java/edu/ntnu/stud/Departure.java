package edu.ntnu.stud;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A class representing a single departure.
 * @version 1.0
 * @author Nikolai Tandberg
 */
public class Departure {
    private final LocalTime departureTime;
    private final String line;
    private final int trainNumber;
    private final String destination;
    private int track;
    private LocalTime delay;

    /**
     * Constructor
     * @param departureTime the time of day the train departs
     * @param line the line the train is travelling on
     * @param trainNumber the unique number of the departure
     * @param destination the last stop on the line
     * @param track the track at which the train arrives at the station
     * @param delay the amount of time the train is delayed compared to it's scheduled time
     */
    public Departure(String departureTime, String line, int trainNumber, String destination, int track, String delay) {
        this.departureTime = parseTimeString(departureTime);
        this.line = line;
        this.trainNumber = trainNumber;
        this.destination = destination;
        this.track = track;
        this.delay = parseTimeString(delay);
    }

    /** Parses string into a LocalTime object.
     *
     * @param timeString the string that is parsed
     * @return returns the departure time as a LocalTime object
     */
    private LocalTime parseTimeString(String timeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return LocalTime.parse(timeString, formatter);
    }

    /**
     *
     * @param track assign new track for the train to arrive at
     */
    public void setTrack(int track) {
        this.track = track;
    }

    /**
     *
     * @param delay assign a new delay for the departure
     */
    public void setDelay(String delay) {
        this.delay = parseTimeString(delay);
    }

    /**
     *
     * @return returns the departure time
     */
    public LocalTime getDepartureTime() {
        return departureTime;
    }

    /**
     *
     * @return returns the line the train is travelling on
     */
    public String getLine() {
        return line;
    }

    /**
     *
     * @return returns the unique number of the train
     */
    public int getTrainNumber() {
        return trainNumber;
    }


    /**
     *
     * @return returns the last stop on the line
     */
    public String getDestination() {
        return destination;
    }

    /**
     *
     * @return returns the track the train is arriving at
     */
    public int getTrack() {
        return track;
    }

    /**
     *
     * @return returns the delay of the train compared to it's scheduled arrival
     */
    public LocalTime getDelay() {
        return delay;
    }
}
