package edu.ntnu.stud.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing DepartureRegistry class
 *
 * @author Nikolai Tandberg
 * @version 1.0
 * @since 2023-11-26
 *
 */
class DepartureRegistryTest {

  DepartureRegistry departureRegistry;

  @BeforeEach
  void setup() {
    departureRegistry = new DepartureRegistry();
  }


  @Nested
  @DisplayName("addDeparture tests")
  class TestsForAddDeparture {
    @Test
    @DisplayName("Should add departure")
    void shouldAddDeparture() {
      departureRegistry.addDeparture(LocalTime.of(10,0), "R14", 1, "Asker", 1, LocalTime.of(0,0));
      assertEquals(1, departureRegistry.getDepartures().size());
    }

    @Test
    @DisplayName("Should not add departure with same train number")
    void shouldNotAddDepartureWithSameTrainNumber() {
      departureRegistry.addDeparture(LocalTime.of(10,0), "R14", 1, "Asker", 1, LocalTime.of(0,0));
      LocalTime departureTime = LocalTime.of(11,0);
      LocalTime delay = LocalTime.of(0,10);
      assertThrows(IllegalArgumentException.class, () -> departureRegistry.addDeparture(departureTime, "R11", 1, "Drammen", 2, delay));
    }

    @Test
    @DisplayName("Should not add departure with departure time before current time")
    void shouldNotAddDepartureWithDepartureTimeBeforeCurrentTime() {
      LocalTime departureTime = LocalTime.of(9,0);
      LocalTime delay = LocalTime.of(0,10);
      departureRegistry.setClock(LocalTime.of(10,0));
      assertThrows(IllegalArgumentException.class, () -> departureRegistry.addDeparture(departureTime, "R11", 1, "Drammen", 2, delay));
    }
  }


  @Nested
  @DisplayName("tests for getters")
  class TestsForGetters {
    @Test
    @DisplayName("Should get departure by train number")
    void shouldGetDepartureByTrainNumber() {
      departureRegistry.addDeparture(LocalTime.of(10,0), "R14", 1, "Asker", 1, LocalTime.of(0,0));
      assertEquals(1, departureRegistry.getDepartureByTrainNumber(1).getTrainNumber());
    }

    @Test
    @DisplayName("Should not get departure by train number")
    void shouldNotGetDepartureByTrainNumber() {
      departureRegistry.addDeparture(LocalTime.of(10,0), "R14", 1, "Asker", 1, LocalTime.of(0,0));
      assertThrows(NoSuchElementException.class, () -> departureRegistry.getDepartureByTrainNumber(2));
    }

    @Test
    @DisplayName("Should get departures by destination")
    void shouldGetDeparturesByDestination() {
      departureRegistry.addDeparture(LocalTime.of(10,0), "R14", 1, "Asker", 1, LocalTime.of(0,0));
      departureRegistry.addDeparture(LocalTime.of(11,0), "R11", 2, "Drammen", 2, LocalTime.of(0,10));
      assertEquals(1, departureRegistry.getDeparturesByDestination("Asker").size());
    }

    @Test
    @DisplayName("Should not get departures by destination")
    void shouldNotGetDeparturesByDestination() {
      departureRegistry.addDeparture(LocalTime.of(10,0), "R14", 1, "Asker", 1, LocalTime.of(0,0));
      departureRegistry.addDeparture(LocalTime.of(11,0), "R11", 2, "Drammen", 2, LocalTime.of(0,10));
      assertThrows(NoSuchElementException.class, () -> departureRegistry.getDeparturesByDestination("Oslo"));
    }

    @Test
    @DisplayName("Should get departures")
    void shouldGetDepartures() {
      departureRegistry.addDeparture(LocalTime.of(10,0), "R14", 1, "Asker", 1, LocalTime.of(0,0));
      departureRegistry.addDeparture(LocalTime.of(11,0), "R11", 2, "Drammen", 2, LocalTime.of(0,10));
      assertEquals(2, departureRegistry.getDepartures().size());
    }

    @Test
    @DisplayName("Should not get departures when departures is empty")
    void shouldNotGetDepartures() {
      assertThrows(NoSuchElementException.class, departureRegistry::getDepartures);
    }



  }

  @Nested
  @DisplayName("tests for clock behaviour")
  class TestsForSetters {
    @Test
    @DisplayName("Should set clock")
    void shouldSetClock() {
      departureRegistry.setClock(LocalTime.of(10,0));
      assertEquals("10:00", departureRegistry.getClock().toString());
    }

    @Test
    @DisplayName("Should not set clock before current time")
    void shouldNotSetClockBeforeCurrentTime() {
      departureRegistry.setClock(LocalTime.of(10,0));
      LocalTime newTime = LocalTime.of(9,0);
      assertThrows(IllegalArgumentException.class, () -> departureRegistry.setClock(newTime));
    }

    @Test
    @DisplayName("Should remove past departures")
    void shouldRemovePastDepartures() {
      departureRegistry.addDeparture(LocalTime.of(10,0), "R14", 1, "Asker", 1, LocalTime.of(0,0));
      departureRegistry.addDeparture(LocalTime.of(11,0), "R11", 2, "Drammen", 2, LocalTime.of(0,10));
      departureRegistry.setClock(LocalTime.of(11,0));
      assertEquals(1, departureRegistry.getDepartures().size());
    }
  }
}


