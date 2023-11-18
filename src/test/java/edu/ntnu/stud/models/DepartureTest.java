package edu.ntnu.stud.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Class for testing the Departure class
 *
 * @author nikolaitandberg
 * @version 1.0
 * @since 2023-11-18
 */
public class DepartureTest {
  @Nested
  @DisplayName("Departure constructor tests")
  public class TestsForDepartureConstructor {
    @Test
    @DisplayName("Departure creation test")
    public void shouldCreateDeparture() {
      Departure departure1 = new Departure("10:00", "R14", 1, "Asker", 1, "00:00");
      assertEquals(Departure.class, departure1.getClass());

      try {
        Departure departure2 = new Departure("11:00", "R11", 2, "Drammen", 2, "00:10");
      } catch (Exception e) {
        fail("Should not get IllegalArgumentException");
      }
    }

    @Test
    @DisplayName("Departure time exception handling test")
    public void badDepartureTime() {
      try {
        Departure departureWithWrongDepartureTimeFormat1 = new Departure(
                "10",
                "R14",
                1,
                "Asker",
                1,
                "00:10"
        );
        fail("should throw IllegalArgumentException");
      } catch (IllegalArgumentException e) {
        assertEquals("Invalid time format. Expected format: 'HH:mm'", e.getMessage());
      }

      try {
        Departure departureWithWrongDepartureTimeFormat2 = new Departure(
                "10:00:00",
                "R14",
                1,
                "Asker",
                1,
                "00:10"
        );
        fail("should throw IllegalArgumentException");
      } catch (IllegalArgumentException e) {
        assertEquals("Invalid time format. Expected format: 'HH:mm'", e.getMessage());
      }

      try {
        Departure departureWithWrongDepartureTimeFormat3 = new Departure(
                "25:61",
                "R14",
                1,
                "Asker",
                1,
                "00:10"
        );
        fail("should throw IllegalArgumentException");
      } catch (IllegalArgumentException e) {
        assertEquals("Invalid time format. Expected format: 'HH:mm'", e.getMessage());
      }

      try {
        Departure departureWithEmptyDepartureTime = new Departure(
                "",
                "R14",
                1,
                "Asker",
                1,
                "00:10"
        );
        fail("should throw IllegalArgumentException");
      } catch (IllegalArgumentException e) {
        assertEquals("Departure time cannot be left empty", e.getMessage());
      }
    }

    @Test
    @DisplayName("Line exception handling test")
    public void badLine() {
      try {
        Departure departureWithEmptyLine = new Departure(
                "10:00",
                "",
                1,
                "Asker",
                1,
                "00:10"
        );
        fail("should throw IllegalArgumentException");
      } catch (IllegalArgumentException e) {
        assertEquals("Line cannot be left empty", e.getMessage());
      }
    }

    @Test
    @DisplayName("Train number exception handling test")
    public void badTrainNumber() {
      try {
        Departure departureWithWrongTrainNumber = new Departure(
                "10:00",
                "R14",
                0,
                "Asker",
                1,
                "00:10"
        );
        fail("should throw IllegalArgumentException");
      } catch (IllegalArgumentException e) {
        assertEquals("Train number cannot be less than 1", e.getMessage());
      }
    }

    @Test
    @DisplayName("Destination exception handling test")
    public void badDestination() {
      try {
        Departure departureWithEmptyDestination = new Departure(
                "10:00",
                "R14",
                1,
                "",
                1,
                "00:10"
        );
        fail("should throw IllegalArgumentException");
      } catch (IllegalArgumentException e) {
        assertEquals("Destination cannot be left empty", e.getMessage());
      }
    }

    @Test
    @DisplayName("Track exception handling test")
    public void badTrack() {
      try {
        Departure departureBadTrack1 = new Departure(
                "10:00",
                "R14",
                1,
                "Asker",
                -10,
                "00:10"
        );
        fail("should throw IllegalArgumentException");
      } catch (IllegalArgumentException e) {
        assertEquals("Track cannot be 0 or a negative number other than -1", e.getMessage());
      }

      try {
        Departure departureWithBadTrack2 = new Departure(
                "10:00",
                "R14",
                1,
                "Asker",
                0,
                "00:10"
        );
        fail("should throw IllegalArgumentException");
      } catch (IllegalArgumentException e) {
        assertEquals("Track cannot be 0 or a negative number other than -1", e.getMessage());
      }
    }


    @Test
    @DisplayName("Delay exception handling test")
    public void badDelay() {
      try {
        Departure departureWithBadDelay1 = new Departure(
                "10:00",
                "R14",
                1,
                "Asker",
                1,
                "100000000"
        );
        fail("should throw IllegalArgumentException");
      } catch(IllegalArgumentException e) {
        assertEquals("Invalid time format. Expected format: 'HH:mm'", e.getMessage());
      }

      try {
        Departure departureWithBadDelay2 = new Departure(
                "10:00",
                "R14",
                1,
                "Asker",
                1,
                "10:10:10"
        );
        fail("should throw IllegalArgumentException");
      } catch(IllegalArgumentException e) {
        assertEquals("Invalid time format. Expected format: 'HH:mm'", e.getMessage());
      }

      try {
        Departure departureWithBadDelay3 = new Departure(
                "10:00",
                "R14",
                1,
                "Asker",
                1,
                "-10:10"
        );
        fail("should throw IllegalArgumentException");
      } catch(IllegalArgumentException e) {
        assertEquals("Invalid time format. Expected format: 'HH:mm'", e.getMessage());
      }

      try {
        Departure departureWithBadDelay4 = new Departure(
                "10:00",
                "R14",
                1,
                "Asker",
                1,
                "test"
        );
        fail("should throw IllegalArgumentException");
      } catch(IllegalArgumentException e) {
        assertEquals("Invalid time format. Expected format: 'HH:mm'", e.getMessage());
      }


      try {
        Departure departureWithEmptyDelay = new Departure(
                "00:10",
                "R14",
                1,
                "Asker",
                1,
                ""
        );
        fail("should throw IllegalArgumentException");
      } catch (IllegalArgumentException e) {
        assertEquals("Delay cannot be left empty", e.getMessage());
      }
    }

  }

  @Nested
  @DisplayName("Tests for getters and setters")
  public class TestsForDeparturesGettersSetters {

    @Test
    @DisplayName("Get departure time test")
    public void getDepartureTimeTest() {
      Departure departure = new Departure("10:00", "R14", 1, "Asker", 1, "00:00");
      assertEquals(departure.getDepartureTime(), LocalTime.of(10,0));
    }

    @Test
    @DisplayName("Get line test")
    public void getLineTest() {
      Departure departure = new Departure("10:00", "R14", 1, "Asker", 1, "00:00");
      assertEquals(departure.getLine(), "R14");
    }

    @Test
    @DisplayName("Get train number test")
    public void getTrainNumberTest() {
      Departure departure = new Departure("10:00", "R14", 1, "Asker", 1, "00:00");
      assertEquals(departure.getTrainNumber(), 1);
    }

    @Test
    @DisplayName("Get destination test")
    public void getDestinationTest() {
      Departure departure = new Departure("10:00", "R14", 1, "Asker", 1, "00:00");
      assertEquals(departure.getDestination(), "Asker");
    }

    @Test
    @DisplayName("Get and set track test")
    public void getSetTrackTest() {
      Departure departure = new Departure("10:00", "R14", 1, "Asker", -1, "00:00");
      assertEquals(departure.getTrack(), -1);
      departure.setTrack(1);
      assertEquals(departure.getTrack(), 1);
    }

    @Test
    @DisplayName("Get and set delay test")
    public void getSetDelayTest() {
      Departure departure = new Departure("10:00", "R14", 1, "Asker", -1, "00:10");
      assertEquals(departure.getDelay(), LocalTime.of(0,10));
      departure.setDelay("10:00");
      assertEquals(departure.getDelay(), LocalTime.of(10,0));
    }
  }

}