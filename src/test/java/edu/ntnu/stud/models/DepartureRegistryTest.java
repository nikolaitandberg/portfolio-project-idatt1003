package edu.ntnu.stud.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
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
      departureRegistry.addDeparture("10:00", "R14", 1, "Asker", 1, "00:00");
      assertEquals(1, departureRegistry.getDepartures().size());
    }

    @Test
    @DisplayName("Should not add departure with same train number")
    void shouldNotAddDepartureWithSameTrainNumber() {
      departureRegistry.addDeparture("10:00", "R14", 1, "Asker", 1, "00:00");
      assertThrows(IllegalArgumentException.class, () -> departureRegistry.addDeparture("11:00", "R11", 1, "Drammen", 2, "00:10"));
    }
  }


  @Nested
  @DisplayName("getDepartureByTrainNumber tests")
  class TestsForGetDepartureByTrainNumber {
    @Test
    @DisplayName("Should get departure by train number")
    void shouldGetDepartureByTrainNumber() {
      departureRegistry.addDeparture("10:00", "R14", 1, "Asker", 1, "00:00");
      assertEquals(1, departureRegistry.getDepartureByTrainNumber(1).getTrainNumber());
    }

    @Test
    @DisplayName("Should not get departure by train number")
    void shouldNotGetDepartureByTrainNumber() {
      departureRegistry.addDeparture("10:00", "R14", 1, "Asker", 1, "00:00");
      assertThrows(NoSuchElementException.class, () -> departureRegistry.getDepartureByTrainNumber(2));
    }
  }

  @Nested
  @DisplayName("getDeparturesByDestination tests")
  class TestsForGetDeparturesByDestination {
    @Test
    @DisplayName("Should get departures by destination")
    void shouldGetDeparturesByDestination() {
      departureRegistry.addDeparture("10:00", "R14", 1, "Asker", 1, "00:00");
      departureRegistry.addDeparture("11:00", "R11", 2, "Drammen", 2, "00:10");
      assertEquals(1, departureRegistry.getDeparturesByDestination("Asker").size());
    }

    @Test
    @DisplayName("Should not get departures by destination")
    void shouldNotGetDeparturesByDestination() {
      departureRegistry.addDeparture("10:00", "R14", 1, "Asker", 1, "00:00");
      departureRegistry.addDeparture("11:00", "R11", 2, "Drammen", 2, "00:10");
      assertThrows(NoSuchElementException.class, () -> departureRegistry.getDeparturesByDestination("Oslo"));
    }
  }

  @Nested
  @DisplayName("getSortedDepartures tests")
  class TestsForGetSortedDepartures {
    @Test
    @DisplayName("Should get sorted departures")
    void shouldGetSortedDepartures() {
      departureRegistry.addDeparture("10:00", "R14", 1, "Asker", 1, "00:00");
      departureRegistry.addDeparture("11:00", "R11", 2, "Drammen", 2, "00:10");
      assertEquals(2, departureRegistry.getDepartures().size());
    }

    @Test
    @DisplayName("Should not get sorted departures when departures is empty")
    void shouldNotGetSortedDepartures() {
      assertThrows(NoSuchElementException.class, departureRegistry::getDepartures);
    }
  }

  @Nested
  @DisplayName("setClock tests")
  class TestsForSetClock {
    @Test
    @DisplayName("Should set clock")
    void shouldSetClock() {
      departureRegistry.setClock("10:00");
      assertEquals("10:00", departureRegistry.getClock().toString());
    }

    @Test
    @DisplayName("Should not set clock before current time")
    void shouldNotSetClockBeforeCurrentTime() {
      departureRegistry.setClock("10:00");
      assertThrows(IllegalArgumentException.class, () -> departureRegistry.setClock("09:00"));
    }

    @Test
    @DisplayName("Should remove past departures")
    void shouldRemovePastDepartures() {
      departureRegistry.addDeparture("10:00", "R14", 1, "Asker", 1, "00:00");
      departureRegistry.addDeparture("11:00", "R11", 2, "Drammen", 2, "00:10");
      departureRegistry.setClock("10:30");
      assertEquals(1, departureRegistry.getDepartures().size());
    }
  }
}


