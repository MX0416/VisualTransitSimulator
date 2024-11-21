package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;
import java.util.List;
import org.junit.jupiter.api.Test;



public class TrainStrategiesTest {

  /**
   * Test the constructor for Bus Strategies.
   */
  @Test
  public void testTrainStrategies() {
    TrainStrategies trainStrategies = new TrainStrategies();

    List<String> trainOrderDay = trainStrategies.getTrainOrderDay();
    List<String> trainOrderNight = trainStrategies.getTrainOrderNight();
    int dayTrain = trainStrategies.getCurrentDayTrain();
    int nightTrain = trainStrategies.getCurrentNightTrain();

    assertEquals("electric", trainOrderDay.get(0));
    assertEquals("electric", trainOrderDay.get(1));
    assertEquals("electric", trainOrderDay.get(2));
    assertEquals("diesel", trainOrderDay.get(3));

    assertEquals("electric", trainOrderNight.get(0));
    assertEquals("diesel", trainOrderNight.get(1));

    assertEquals(0, dayTrain);
    assertEquals(0, nightTrain);

  }

  /**
   * Test that it cycles through properly during the Day.
   */
  @Test
  public void testIncrementDayCycle() {
    TrainStrategies trainStrategies = new TrainStrategies();

    assertEquals(0, trainStrategies.getCurrentDayTrain());
    trainStrategies.incrementDayCycle();

    assertEquals(1, trainStrategies.getCurrentDayTrain());
    trainStrategies.incrementDayCycle();

    assertEquals(2, trainStrategies.getCurrentDayTrain());
    trainStrategies.incrementDayCycle();

    assertEquals(3, trainStrategies.getCurrentDayTrain());
    trainStrategies.incrementDayCycle();

    assertEquals(0, trainStrategies.getCurrentDayTrain());
  }

  /**
   * Test that it cycles through properly during the night.
   */
  @Test
  public void testIncrementNightCycle() {
    TrainStrategies trainStrategies = new TrainStrategies();

    assertEquals(0, trainStrategies.getCurrentNightTrain());
    trainStrategies.incrementNightCycle();

    assertEquals(1, trainStrategies.getCurrentNightTrain());
    trainStrategies.incrementNightCycle();

    assertEquals(0, trainStrategies.getCurrentNightTrain());
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
