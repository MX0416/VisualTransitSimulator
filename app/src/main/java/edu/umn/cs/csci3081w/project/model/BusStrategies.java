package edu.umn.cs.csci3081w.project.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * BusStragegies implements Strategies.
 */
public class BusStrategies implements Strategies {
  private int currentDayBus;
  private int currentNightBus;
  private List<String> busOrderDay;
  private List<String> busOrderNight;

  /**
   * BusStrategies cosntructor.
   */
  public BusStrategies() {
    this.busOrderDay = new ArrayList<String>();
    this.busOrderDay.add("large");
    this.busOrderDay.add("large");
    this.busOrderDay.add("small");
    this.busOrderNight = new ArrayList<String>();
    this.busOrderNight.add("small");
    this.busOrderNight.add("small");
    this.busOrderNight.add("small");
    this.busOrderNight.add("large");
    this.currentDayBus = 0;
    this.currentNightBus = 0;
  }

  /**
   * Determines if vehicle is in day time.
   * @return boolean.
   */
  @Override
  public boolean isDay() {
    //    get local time
    LocalTime time = LocalTime.now();
    int hour = time.getHour();

    return hour > 8 && hour < 18;
  }

  /**
   * Returns the next Vehicle type depending on day or nighttime.
   * @return next Vehicle type as String.
   */
  @Override
  public String getNextVehicleType() {
    if (this.isDay()) {
      return busOrderDay.get(currentDayBus);
    } else {
      return busOrderNight.get(currentNightBus);
    }
  }

  /**
   * increment the day cycle.
   */
  @Override
  public void incrementDayCycle() {
    if (currentDayBus == 2) {
      currentDayBus = 0;
    } else {
      currentDayBus++;
    }
  }

  /**
   * increment night cycle.
   */
  @Override
  public void incrementNightCycle() {
    if (currentNightBus == 3) {
      currentNightBus = 0;
    } else {
      currentNightBus++;
    }
  }

  /**
   * Get the order for the buses in the day.
   * @return busOrderDay
   */
  public List<String> getBusOrderDay() {
    return busOrderDay;
  }

  /**
   * Get the order for the busses at night.
   * @return busOrderNight
   */
  public List<String> getBusOrderNight() {
    return busOrderNight;
  }

  /**
   * Get the current bus type for day.
   * @return currentDayBus
   */
  public int getCurrentDayBus() {
    return currentDayBus;
  }

  /**
   * Get the current bus type for night.
   * @return currentNightBus
   */
  public int getCurrentNightBus() {
    return currentNightBus;
  }

}
