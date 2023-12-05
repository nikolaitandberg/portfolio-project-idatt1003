package edu.ntnu.stud.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Class for testing TimeHandling class
 *
 * @author Nikolai Tandberg
 * @version 1.0
 * @since 2023-11-27
 */
class TimeHandlingTest {

  @Nested
  @DisplayName("parseTimeString tests")
  class TestsForParseTimeString {

    @Test
    @DisplayName("Should create LocalTime object from time string with correct format 'HH:mm'")
    void shouldCreateLocalTimeObject() {
      assertEquals(LocalTime.of(10,30), TimeHandling.parseTimeString("10:30"));
    }

    @Test
    @DisplayName("Should parse time string with correct format 'HH:mm'")
    void shouldParseTimeString() {
      assertEquals("10:00", TimeHandling.parseTimeString("10:00").toString());
    }

    @Test
    @DisplayName("Should not parse time string with wrong format 'HH'")
    void shouldNotParseTimeString() {
      assertThrows(IllegalArgumentException.class, () -> TimeHandling.parseTimeString("10"));
    }

    @Test
    @DisplayName("Should not parse time string with wrong format 'HH:mm:ss'")
    void shouldNotParseTimeString2() {
      assertThrows(IllegalArgumentException.class, () -> TimeHandling.parseTimeString("10:00:00"));
    }

    @Test
    @DisplayName("Should not parse time string with wrong format 'HHmm'")
    void shouldNotParseTimeString3() {
      assertThrows(IllegalArgumentException.class, () -> TimeHandling.parseTimeString("1000"));
    }

    @Test
    @DisplayName("Should not parse time string with wrong format 'H:mm'")
    void shouldNotParseTimeString4() {
      assertThrows(IllegalArgumentException.class, () -> TimeHandling.parseTimeString("9:30"));
    }

    @Test
    @DisplayName("Should not parse time string with wrong format 'HH:m'")
    void shouldNotParseTimeString5() {
      assertThrows(IllegalArgumentException.class, () -> TimeHandling.parseTimeString("09:3"));
    }

    @Test
    @DisplayName("Should not parse time string with wrong format 'H:m'")
    void shouldNotParseTimeString6() {
      assertThrows(IllegalArgumentException.class, () -> TimeHandling.parseTimeString("9:3"));
    }

    @Test
    @DisplayName("Should not parse time string with wrong format 'hour:minute'")
    void shouldNotParseTimeString7() {
      assertThrows(IllegalArgumentException.class, () -> TimeHandling.parseTimeString("ten:thirty"));
    }

    @Test
    @DisplayName("Should not parse time string with wrong format 'HH,mm'")
    void shouldNotParseTimeString8() {
      assertThrows(IllegalArgumentException.class, () -> TimeHandling.parseTimeString("10,00"));
    }

    @Test
    @DisplayName("Should not parse time string with wrong format 'HH.mm'")
    void shouldNotParseTimeString9() {
      assertThrows(IllegalArgumentException.class, () -> TimeHandling.parseTimeString("10.00"));
    }
  }
}