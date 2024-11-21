package edu.umn.cs.csci3081w.project.model;

/**
 * ElectricTrain extends Train.
 */
public class ElectricTrain extends Train {
  /**
   * name of ElectricTrain Vehicle.
   */
  public static final String TRAIN_VEHICLE = "ELECTRIC_TRAIN_VEHICLE";
  /**
   * speed of ElectricTrain Vehicle.
   */
  public static final double SPEED = 1;
  /**
   * capacity of ElectricTrain Vehicle.
   */
  public static final int CAPACITY = 120;

  /**
   * constructor for ElectricTrain.
   * @param id id.
   * @param line line.
   */

  public ElectricTrain(int id, Line line) {
    super(id, line, CAPACITY, SPEED);
    setName(TRAIN_VEHICLE);
  }

  /**
   * calculates CO2 for ElectricTrain (Always zero).
   * @return 0.
   */
  @Override
  public int getCurrentCO2Emission() {
    return 0;
  }
}
