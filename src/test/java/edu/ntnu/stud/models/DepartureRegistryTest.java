package edu.ntnu.stud.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;


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
    @DisplayName("Should not add departure with wrong departure time format")
    public void shouldNotAddDepartureWithWrongDepartureTimeFormat() {
      DepartureRegistry departureRegistry = new DepartureRegistry();
      assertThrows(IllegalArgumentException.class, () -> departureRegistry.addDeparture("10", "R14", 1, "Asker", 1, "00:00"));
    }

    @Test
    @DisplayName("Should not add departure with empty line")
    public void shouldNotAddDepartureWithEmptyLine() {
      DepartureRegistry departureRegistry = new DepartureRegistry();
      assertThrows(IllegalArgumentException.class, () -> departureRegistry.addDeparture("10:00", "", 1, "Asker", 1, "00:00"));
    }

    @Test
    @DisplayName("Should not add departure with same train number")
    public void shouldNotAddDepartureWithSameTrainNumber() {
      DepartureRegistry departureRegistry = new DepartureRegistry();
      departureRegistry.addDeparture("10:00", "R14", 1, "Asker", 1, "00:00");
      assertThrows(IllegalArgumentException.class, () -> departureRegistry.addDeparture("11:00", "R11", 1, "Drammen", 2, "00:10"));
    }

    @Test
    @DisplayName("Should not add departure when train number is less than 1")
    public void shouldNotAddDepartureWithTrainNumber0() {
      DepartureRegistry departureRegistry = new DepartureRegistry();
      assertThrows(IllegalArgumentException.class, () -> departureRegistry.addDeparture("10:00", "R14", 0, "Asker", 1, "00:00"));
    }

    @Test
    @DisplayName("Should not add departure with empty destination")
    public void shouldNotAddDepartureWithEmptyDestination() {
      DepartureRegistry departureRegistry = new DepartureRegistry();
      assertThrows(IllegalArgumentException.class, () -> departureRegistry.addDeparture("10:00", "R14", 1, "", 1, "00:00"));
    }

    @Test
    @DisplayName("Should not add departure when track is 0")
    public void shouldNotAddDepartureWithTrack0() {
      DepartureRegistry departureRegistry = new DepartureRegistry();
      assertThrows(IllegalArgumentException.class, () -> departureRegistry.addDeparture("10:00", "R14", 1, "Asker", 0, "00:00"));
    }

    @Test
    @DisplayName("Should not add departure when track is a negative number other than -1")
    public void shouldNotAddDepartureWithTrackNegativeNumberOtherThanMinus1() {
      DepartureRegistry departureRegistry = new DepartureRegistry();
      assertThrows(IllegalArgumentException.class, () -> departureRegistry.addDeparture("10:00", "R14", 1, "Asker", -10, "00:00"));
    }

    @Test
    @DisplayName("Should not add departure with wrong delay format")
    public void shouldNotAddDepartureWithWrongDelayFormat() {
      DepartureRegistry departureRegistry = new DepartureRegistry();
      assertThrows(IllegalArgumentException.class, () -> departureRegistry.addDeparture("10:00", "R14", 1, "Asker", 1, "00"));
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
    @DisplayName("Should not get sorted departures")
    public void shouldNotGetSortedDepartures() {
      DepartureRegistry departureRegistry = new DepartureRegistry();
      assertThrows(NoSuchElementException.class, departureRegistry::getSortedDepartures);
    }
  }

  @Nested
  @DisplayName("setTrack tests")
  public class TestsForSetTrack {
    @Test
    @DisplayName("Should set track")
    public void shouldSetTrack() {
      DepartureRegistry departureRegistry = new DepartureRegistry();
      departureRegistry.addDeparture("10:00", "R14", 1, "Asker", 1, "00:00");
      departureRegistry.setTrackForDeparture(1, 2);
      assertEquals(2, departureRegistry.getDepartureByTrainNumber(1).getTrack());
    }

    @Test
    @DisplayName("Should not set track when track is 0")
    public void shouldNotSetTrackWhenTrackIs0() {
      DepartureRegistry departureRegistry = new DepartureRegistry();
      departureRegistry.addDeparture("10:00", "R14", 1, "Asker", 1, "00:00");
      assertThrows(IllegalArgumentException.class, () -> departureRegistry.setTrackForDeparture(1, 0));
    }

    @Test
    @DisplayName("Should not set track when track is a negative number other than -1")
    public void shouldNotSetTrackWhenTrackIsNegativeNumberOtherThan1() {
      DepartureRegistry departureRegistry = new DepartureRegistry();
      departureRegistry.addDeparture("10:00", "R14", 1, "Asker", 1, "00:00");
      assertThrows(IllegalArgumentException.class, () -> departureRegistry.setTrackForDeparture(1, -10));
    }
  }

  @Nested
  @DisplayName("setDelay tests")
  public class TestsForSetDelay {
    @Test
    @DisplayName("Should set delay")
    public void shouldSetDelay() {
      DepartureRegistry departureRegistry = new DepartureRegistry();
      departureRegistry.addDeparture("10:00", "R14", 1, "Asker", 1, "00:00");
      departureRegistry.setDelayForDeparture(1, "00:10");
      assertEquals("00:10", departureRegistry.getDepartureByTrainNumber(1).getDelay().toString());
    }

    @Test
    @DisplayName("Should not set delay with wrong time format")
    public void shouldNotSetDelayWithWrongTimeFormat() {
      DepartureRegistry departureRegistry = new DepartureRegistry();
      assertThrows(IllegalArgumentException.class, () -> departureRegistry.setDelayForDeparture(1, "00"));
    }

  }
  }