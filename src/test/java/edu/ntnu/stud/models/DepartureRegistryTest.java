package edu.ntnu.stud.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing DepartureRegistry class
 *
 * @author nikolaitandberg
 * @version 1.0
 * @since 2023-11-26
 *
 */
class DepartureRegistryTest {
  @Nested
  @DisplayName("addDeparture tests")
  public class TestsForAddDeparture {
    @Test
    @DisplayName("Should add departure")
    public void shouldAddDeparture() {
      DepartureRegistry departureRegistry = new DepartureRegistry();
      departureRegistry.addDeparture("10:00", "R14", 1, "Asker", 1, "00:00");
      assertEquals(1, departureRegistry.getSortedDepartures().size());
    }

    @Test
    @DisplayName("Should not add departure with same train number")
    public void shouldNotAddDepartureWithSameTrainNumber() {
      DepartureRegistry departureRegistry = new DepartureRegistry();
      departureRegistry.addDeparture("10:00", "R14", 1, "Asker", 1, "00:00");
      assertThrows(IllegalArgumentException.class, () -> departureRegistry.addDeparture("11:00", "R11", 1, "Drammen", 2, "00:10"));
    }
  }


  @Nested
  @DisplayName("getDepartureByTrainNumber tests")
  public class TestsForGetDepartureByTrainNumber {
    @Test
    @DisplayName("Should get departure by train number")
    public void shouldGetDepartureByTrainNumber() {
      DepartureRegistry departureRegistry = new DepartureRegistry();
      departureRegistry.addDeparture("10:00", "R14", 1, "Asker", 1, "00:00");
      assertEquals(1, departureRegistry.getDepartureByTrainNumber(1).getTrainNumber());
    }

    @Test
    @DisplayName("Should not get departure by train number")
    public void shouldNotGetDepartureByTrainNumber() {
      DepartureRegistry departureRegistry = new DepartureRegistry();
      departureRegistry.addDeparture("10:00", "R14", 1, "Asker", 1, "00:00");
      assertThrows(NoSuchElementException.class, () -> departureRegistry.getDepartureByTrainNumber(2));
    }
  }

  @Nested
  @DisplayName("getDeparturesByDestination tests")
  public class TestsForGetDeparturesByDestination {
    @Test
    @DisplayName("Should get departures by destination")
    public void shouldGetDeparturesByDestination() {
      DepartureRegistry departureRegistry = new DepartureRegistry();
      departureRegistry.addDeparture("10:00", "R14", 1, "Asker", 1, "00:00");
      departureRegistry.addDeparture("11:00", "R11", 2, "Drammen", 2, "00:10");
      assertEquals(1, departureRegistry.getDeparturesByDestination("Asker").size());
    }

    @Test
    @DisplayName("Should not get departures by destination")
    public void shouldNotGetDeparturesByDestination() {
      DepartureRegistry departureRegistry = new DepartureRegistry();
      departureRegistry.addDeparture("10:00", "R14", 1, "Asker", 1, "00:00");
      departureRegistry.addDeparture("11:00", "R11", 2, "Drammen", 2, "00:10");
      assertThrows(NoSuchElementException.class, () -> departureRegistry.getDeparturesByDestination("Oslo"));
    }
  }

  @Nested
  @DisplayName("getSortedDepartures tests")
  public class TestsForGetSortedDepartures {
    @Test
    @DisplayName("Should get sorted departures")
    public void shouldGetSortedDepartures() {
      DepartureRegistry departureRegistry = new DepartureRegistry();
      departureRegistry.addDeparture("10:00", "R14", 1, "Asker", 1, "00:00");
      departureRegistry.addDeparture("11:00", "R11", 2, "Drammen", 2, "00:10");
      assertEquals(2, departureRegistry.getSortedDepartures().size());
    }

    @Test
    @DisplayName("Should not get sorted departures when departures is empty")
    public void shouldNotGetSortedDepartures() {
      DepartureRegistry departureRegistry = new DepartureRegistry();
      assertThrows(NoSuchElementException.class, departureRegistry::getSortedDepartures);
    }
  }

  @Test
  @DisplayName("Should remove passed departures")
  public void shouldRemovePassedDepartures() {
    DepartureRegistry departureRegistry = new DepartureRegistry();
    departureRegistry.addDeparture("10:00", "R14", 1, "Asker", 1, "00:00");
    departureRegistry.addDeparture("11:00", "R11", 2, "Drammen", 2, "00:10");
    departureRegistry.removePassedDepartures("10:30");
    assertEquals(1, departureRegistry.getSortedDepartures().size());
  }

}


