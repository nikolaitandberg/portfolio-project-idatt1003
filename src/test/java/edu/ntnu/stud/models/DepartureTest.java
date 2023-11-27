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
      assertDoesNotThrow(() -> new Departure(
              "10:00",
              "R14",
              1,
              "Asker",
              1,
              "00:10"
      ));
    }

    @Test
    @DisplayName("Should not create departure with wrong time format")
    public void shouldNotCreateDepartureWithWrongTimeFormat() {
      assertThrows(IllegalArgumentException.class, () -> new Departure(
              "10",
              "R14",
              1,
              "Asker",
              1,
              "00:10"
      ));
    }

    @Test
    @DisplayName("Should not create departure with wrong delay format")
    public void shouldNotCreateDepartureWithWrongDelayFormat() {
      assertThrows(IllegalArgumentException.class, () -> new Departure(
              "10:00",
              "R14",
              1,
              "Asker",
              1,
              "00"
      ));
    }

    @Test
    @DisplayName("Should not create departure with empty line")
    public void shouldNotCreateDepartureWithEmptyLine() {
      assertThrows(IllegalArgumentException.class, () -> new Departure(
              "10:00",
              "",
              1,
              "Asker",
              1,
              "00:10"
      ));
    }

    @Test
    @DisplayName("Should not create departure with train number less than 1")
    public void shouldNotCreateDepartureWithTrainNumberLessThan1() {
      assertThrows(IllegalArgumentException.class, () -> new Departure(
              "10:00",
              "R14",
              0,
              "Asker",
              1,
              "00:10"
      ));
    }

    @Test
    @DisplayName("Should not create departure with empty destination")
    public void shouldNotCreateDepartureWithEmptyDestination() {
      assertThrows(IllegalArgumentException.class, () -> new Departure(
              "10:00",
              "R14",
              1,
              "",
              1,
              "00:10"
      ));
    }

    @Test
    @DisplayName("Should not create departure with track 0")
    public void shouldNotCreateDepartureWithTrack0() {
      assertThrows(IllegalArgumentException.class, () -> new Departure(
              "10:00",
              "R14",
              1,
              "Asker",
              0,
              "00:10"
      ));
    }

    @Test
    @DisplayName("Should not create departure with track less than -1")
    public void shouldNotCreateDepartureWithTrackLessThanMinus1() {
      assertThrows(IllegalArgumentException.class, () -> new Departure(
              "10:00",
              "R14",
              1,
              "Asker",
              -2,
              "00:10"
      ));
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