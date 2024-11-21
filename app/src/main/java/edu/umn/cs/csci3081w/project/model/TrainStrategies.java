package edu.umn.cs.csci3081w.project.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * TrainStrategies implements Strategies.
 */
public class TrainStrategies implements Strategies {
  private int currentDayTrain;
  private int currentNightTrain;
  private List<String> trainOrderDay;
  private List<String> trainOrderNight;

  /**
   * constructor for TrainStrategies.
   */
  public TrainStrategies() {
    this.trainOrderDay = new ArrayList<String>();
    this.trainOrderDay.add("electric");
    this.trainOrderDay.add("electric");
    this.trainOrderDay.add("electric");
    this.trainOrderDay.add("diesel");

    this.trainOrderNight = new ArrayList<String>();
    this.trainOrderNight.add("electric");
    this.trainOrderNight.add("diesel");

    this.currentDayTrain = 0;
    this.currentNightTrain = 0;
  }

  /**
   * determines if time is in the day.
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
   * Determine if it is Day or Night and get the correct Vehicle.
   * @return String indicating Vehicle type
   */
  @Override
  public String getNextVehicleType() {

    if (this.isDay()) {
      return trainOrderDay.get(currentDayTrain);
    } else {
      return trainOrderNight.get(currentNightTrain);
    }
  }

  /**
   * increments day cycle of train.
   */
  @Override
  public void incrementDayCycle() {
    if (currentDayTrain == 3) {
      currentDayTrain = 0;
    } else {
      currentDayTrain++;
    }
  }

  /**
   * increments night cycle of train.
   */
  @Override
  public void incrementNightCycle() {
    if (currentNightTrain == 1) {
      currentNightTrain = 0;
    } else {
      currentNightTrain++;
    }
  }

  /**
   * Get the list for trains for the day.
   * @return List trainOrderDay
   */
  public List<String> getTrainOrderDay() {
    return trainOrderDay;
  }

  /**
   * Get the list for trains for the night.
   * @return List trainOrderNight
   */
  public List<String> getTrainOrderNight() {
    return trainOrderNight;
  }

  /**
   * Get the current index of the day trains.
   * @return int currentDayTrain
   */
  public int getCurrentDayTrain() {
    return currentDayTrain;
  }


  /**
   * Get the current index of the night trains.
   * @return int currentNightTrain
   */
  public int getCurrentNightTrain() {
    return currentNightTrain;
  }
}
