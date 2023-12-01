package edu.ntnu.stud.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Class for testing the Departure class
 *
 * @author Nikolai Tandberg
 * @version 1.0
 * @since 2023-11-18
 */
class DepartureTest {
  @Nested
  @DisplayName("Departure constructor tests")
  class TestsForDepartureConstructor {
    @Test
    @DisplayName("Should create departure")
    void shouldCreateDeparture() {
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
    void shouldNotCreateDepartureWithWrongTimeFormat() {
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
    void shouldNotCreateDepartureWithWrongDelayFormat() {
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
    void shouldNotCreateDepartureWithEmptyLine() {
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
    void shouldNotCreateDepartureWithTrainNumberLessThan1() {
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
    void shouldNotCreateDepartureWithEmptyDestination() {
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
    void shouldNotCreateDepartureWithTrack0() {
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
    void shouldNotCreateDepartureWithTrackLessThanMinus1() {
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
  class TestsForDeparturesGettersSetters {

    @Test
    @DisplayName("Get departure time test")
    void getDepartureTimeTest() {
      Departure departure = new Departure("10:00", "R14", 1, "Asker", 1, "00:00");
      assertEquals(departure.getDepartureTime(), LocalTime.of(10,0));
    }

    @Test
    @DisplayName("Get line test")
    void getLineTest() {
      Departure departure = new Departure("10:00", "R14", 1, "Asker", 1, "00:00");
      assertEquals("R14", departure.getLine());
    }

    @Test
    @DisplayName("Get train number test")
    void getTrainNumberTest() {
      Departure departure = new Departure("10:00", "R14", 1, "Asker", 1, "00:00");
      assertEquals(1, departure.getTrainNumber());
    }

    @Test
    @DisplayName("Get destination test")
    void getDestinationTest() {
      Departure departure = new Departure("10:00", "R14", 1, "Asker", 1, "00:00");
      assertEquals("Asker", departure.getDestination());
    }

    @Test
    @DisplayName("Get and set track test")
    void getSetTrackTest() {
      Departure departure = new Departure("10:00", "R14", 1, "Asker", -1, "00:00");
      assertEquals(departure.getTrack(), -1);
      departure.setTrack(1);
      assertEquals(1,departure.getTrack());
    }

    @Test
    @DisplayName("Get and set delay test")
    void getSetDelayTest() {
      Departure departure = new Departure("10:00", "R14", 1, "Asker", -1, "00:10");
      assertEquals(departure.getDelay(), LocalTime.of(0,10));
      departure.setDelay("10:00");
      assertEquals(departure.getDelay(), LocalTime.of(10,0));
    }
  }

  @Nested
  @DisplayName("Tests for toString")
  class TestsForToString {
    @Test
    @DisplayName("Should return string with track and delay")
    void shouldReturnCorrectString() {
      Departure departure = new Departure("10:00", "R14", 1, "Asker", 1, "00:20");
      assertEquals("|          10:00 |  R14 |            1 |           Asker |     1 | 00:20 |", departure.toString());
    }

    @Test
    @DisplayName("Should return string with track and no delay")
    void shouldReturnCorrectString2() {
      Departure departure = new Departure("10:00", "R14", 1, "Asker", 1, "00:00");
      assertEquals("|          10:00 |  R14 |            1 |           Asker |     1 |       |", departure.toString());
    }

    @Test
    @DisplayName("Should return string with no track and delay")
    void shouldReturnCorrectString3() {
      Departure departure = new Departure("10:00", "R14", 1, "Asker", -1, "00:20");
      assertEquals("|          10:00 |  R14 |            1 |           Asker |       | 00:20 |", departure.toString());
    }

    @Test
    @DisplayName("Should return string with no track and no delay")
    void shouldReturnCorrectString4() {
      Departure departure = new Departure("10:00", "R14", 1, "Asker", -1, "00:00");
      assertEquals("|          10:00 |  R14 |            1 |           Asker |       |       |", departure.toString());
    }
  }

}