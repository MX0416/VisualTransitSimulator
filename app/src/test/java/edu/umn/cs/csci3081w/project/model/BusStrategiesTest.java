package edu.umn.cs.csci3081w.project.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;
import java.util.List;
import org.junit.jupiter.api.Test;


public class BusStrategiesTest {

  /**
   * Test the constructor for Bus Strategies.
   */
  @Test
  public void busStratConstructorTest() {
    BusStrategies busStrategies = new BusStrategies();

    List<String> busOrderNight = busStrategies.getBusOrderNight();
    List<String> busOrderDay = busStrategies.getBusOrderDay();
    int dayBus = busStrategies.getCurrentDayBus();
    int nightBus = busStrategies.getCurrentNightBus();

    assertEquals("large", busOrderDay.get(0));
    assertEquals("large", busOrderDay.get(1));
    assertEquals("small", busOrderDay.get(2));

    assertEquals("small", busOrderNight.get(0));
    assertEquals("small", busOrderNight.get(1));
    assertEquals("small", busOrderNight.get(2));
    assertEquals("large", busOrderNight.get(3));

    assertEquals(0, dayBus);
    assertEquals(0, nightBus);

  }

  /**
   * Test that it cycles through properly during the Day.
   */
  @Test
  public void busStratDayCycleTest() {
    BusStrategies busStrategies = new BusStrategies();

    assertEquals(0, busStrategies.getCurrentDayBus());
    busStrategies.incrementDayCycle();

    assertEquals(1, busStrategies.getCurrentDayBus());
    busStrategies.incrementDayCycle();

    assertEquals(2, busStrategies.getCurrentDayBus());
    busStrategies.incrementDayCycle();

    assertEquals(0, busStrategies.getCurrentDayBus());

  }

  /**
   * Test that it cycles through properly during the night.
   */
  @Test
  public void busStratNightCycleTest() {
    BusStrategies busStrategies = new BusStrategies();

    assertEquals(0, busStrategies.getCurrentNightBus());
    busStrategies.incrementNightCycle();

    assertEquals(1, busStrategies.getCurrentNightBus());
    busStrategies.incrementNightCycle();

    assertEquals(2, busStrategies.getCurrentNightBus());
    busStrategies.incrementNightCycle();

    assertEquals(3, busStrategies.getCurrentNightBus());
    busStrategies.incrementNightCycle();

    assertEquals(0, busStrategies.getCurrentNightBus());
  }

  /**
   * Test that isDay returns the proper
   * boolean depending on the time.
   */
  @Test
  public void isDayTest() {
    BusStrategies strat = new BusStrategies();
    LocalTime time = LocalTime.now();
    int hour = time.getHour();

    if (hour > 8 && hour < 18) {
      assertTrue(strat.isDay());
    } else {
      assertFalse(strat.isDay());
    }
  }


}
