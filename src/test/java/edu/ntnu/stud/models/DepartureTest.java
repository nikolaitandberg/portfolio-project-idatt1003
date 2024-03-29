package edu.ntnu.stud.models;

import org.junit.jupiter.api.*;

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

  Departure departure;
  static LocalTime departureTime;
  static LocalTime delay;

  @BeforeAll
  static void setupAll() {
    departureTime = LocalTime.of(10,0);
    delay = LocalTime.of(0,0);
  }

  @Nested
  @DisplayName("Departure constructor tests")
  class TestsForDepartureConstructor {
    @Test
    @DisplayName("Should create departure")
    void shouldCreateDeparture() {
      assertDoesNotThrow(() -> new Departure(
              LocalTime.of(10,0),
              "R14",
              1,
              "Asker",
              1,
              LocalTime.of(0,10)
      ));
    }

    @Test
    @DisplayName("Should not create departure with null departure time")
    void shouldNotCreateDepartureWithNullDepartureTime() {
      assertThrows(NullPointerException.class, () -> new Departure(
              null,
              "R14",
              1,
              "Asker",
              1,
              delay
      ));
    }

    @Test
    @DisplayName("Should not create departure with empty line")
    void shouldNotCreateDepartureWithEmptyLine() {
      assertThrows(IllegalArgumentException.class, () -> new Departure(
              departureTime,
              "",
              1,
              "Asker",
              1,
              delay
      ));
    }

    @Test
    @DisplayName("Should not create departure with train number less than 1")
    void shouldNotCreateDepartureWithTrainNumberLessThan1() {
      assertThrows(IllegalArgumentException.class, () -> new Departure(
              departureTime,
              "R14",
              0,
              "Asker",
              1,
              delay
      ));
    }

    @Test
    @DisplayName("Should not create departure with empty destination")
    void shouldNotCreateDepartureWithEmptyDestination() {
      assertThrows(IllegalArgumentException.class, () -> new Departure(
              departureTime,
              "R14",
              1,
              "",
              1,
              delay
      ));
    }

    @Test
    @DisplayName("Should not create departure with track 0")
    void shouldNotCreateDepartureWithTrack0() {
      assertThrows(IllegalArgumentException.class, () -> new Departure(
              departureTime,
              "R14",
              1,
              "Asker",
              0,
              delay
      ));
    }

    @Test
    @DisplayName("Should not create departure with track less than -1")
    void shouldNotCreateDepartureWithTrackLessThanMinus1() {
      assertThrows(IllegalArgumentException.class, () -> new Departure(
              departureTime,
              "R14",
              1,
              "Asker",
              -2,
              delay
      ));
    }

    @Test
    @DisplayName("Should not create departure with null delay")
    void shouldNotCreateDepartureWithNullDelay() {
      assertThrows(NullPointerException.class, () -> new Departure(
              departureTime,
              "R14",
              1,
              "Asker",
              1,
              null
      ));
    }
  }

  @BeforeEach
  void getterSetterTestSetup() {
    departure = new Departure(LocalTime.of(10,0), "R14", 1, "Asker", 1, LocalTime.of(0,10));
  }

  @Nested
  @DisplayName("Tests for getters")
  class TestsForGetters {

    @Test
    @DisplayName("should get departure time")
    void shouldGetDepartureTime() {
      assertEquals(departure.getDepartureTime(), LocalTime.of(10,0));
    }

    @Test
    @DisplayName("should get line")
    void shouldGetLine() {
      assertEquals("R14", departure.getLine());
    }

    @Test
    @DisplayName("should get train number")
    void shouldGetTrainNumber() {
      assertEquals(1, departure.getTrainNumber());
    }

    @Test
    @DisplayName("should get destination")
    void shouldGetDestination() {
      assertEquals("Asker", departure.getDestination());
    }

    @Test
    @DisplayName("should get real departure time")
    void shouldGetRealDepartureTime() {
      assertEquals(LocalTime.of(10,10), departure.getRealDepartureTime());
    }

    @Test
    @DisplayName("should get track")
    void shouldGetTrack() {
      assertEquals(1,departure.getTrack());
    }
  }


  @Nested
  @DisplayName("Tests for setters")
  class TestsForSetters {
    @Test
    @DisplayName("Should set track")
    void shouldSetTrack() {
      departure.setTrack(2);
      assertEquals(2, departure.getTrack());
    }

    @Test
    @DisplayName("Should set track when -1")
    void shouldSetTrackWhenMinus1() {
      departure.setTrack(-1);
      assertEquals(-1, departure.getTrack());
    }

    @Test
    @DisplayName("Should not set track when 0")
    void shouldNotSetTrackWhen0() {
      assertThrows(IllegalArgumentException.class, () -> departure.setTrack(0));
    }

    @Test
    @DisplayName("Should not set track when less than -1")
    void shouldNotSetTrackWhenLessThanMinus1() {
      assertThrows(IllegalArgumentException.class, () -> departure.setTrack(-2));
    }

    @Test
    @DisplayName("Should set delay")
    void shouldSetDelay() {
      departure.setDelay(LocalTime.of(10,0));
      assertEquals(LocalTime.of(10,0), departure.getDelay());
    }

    @Test
    @DisplayName("Should not set delay when null")
    void shouldNotSetDelayWhenNull() {
      assertThrows(NullPointerException.class, () -> departure.setDelay(null));
    }
  }

  @Nested
  @DisplayName("Tests for toString")
  class TestsForToString {
    @Test
    @DisplayName("Should return string with track and delay")
    void shouldReturnCorrectString() {
      departure = new Departure(LocalTime.of(10,0), "R14", 1, "Asker", 1, LocalTime.of(0,20));
      assertEquals("|          10:00 |  R14 |            1 |           Asker |     1 | 00:20 |", departure.toString());
    }

    @Test
    @DisplayName("Should return string with track and no delay")
    void shouldReturnCorrectString2() {
      departure = new Departure(LocalTime.of(10,0), "R14", 1, "Asker", 1, LocalTime.of(0,0));
      assertEquals("|          10:00 |  R14 |            1 |           Asker |     1 |       |", departure.toString());
    }

    @Test
    @DisplayName("Should return string with no track and delay")
    void shouldReturnCorrectString3() {
      departure = new Departure(LocalTime.of(10,0), "R14", 1, "Asker", -1, LocalTime.of(0,20));
      assertEquals("|          10:00 |  R14 |            1 |           Asker |       | 00:20 |", departure.toString());
    }

    @Test
    @DisplayName("Should return string with no track and no delay")
    void shouldReturnCorrectString4() {
      departure = new Departure(LocalTime.of(10,0), "R14", 1, "Asker", -1, LocalTime.of(0,0));
      assertEquals("|          10:00 |  R14 |            1 |           Asker |       |       |", departure.toString());
    }
  }

}