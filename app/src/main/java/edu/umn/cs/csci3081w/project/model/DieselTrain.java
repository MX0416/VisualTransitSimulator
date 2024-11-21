package edu.umn.cs.csci3081w.project.model;

/**
 * DieselTrain extends Train.
 */
public class DieselTrain extends Train {
  /**
   * Name of DieselTrain Vehicle.
   */
  public static final String TRAIN_VEHICLE = "Diesel_TRAIN_VEHICLE";
  /**
   * speed of DieselTrainVehicle.
   */
  public static final double SPEED = 1;
  /**
   * capacity of DieselTrainVehicle.
   */
  public static final int CAPACITY = 120;

  /**
   * Constructor for DieselTrain.
   *
   * @param id id.
   * @param line line.
   */

  public DieselTrain(int id, Line line) {
    super(id, line, CAPACITY, SPEED);
    setName(TRAIN_VEHICLE);
  }

  /**
   * calculates CO2 emission for DieselTrain.
   * @return CO2 Emission.
   */
  @Override
  public int getCurrentCO2Emission() {
    return (3 * getPassengers().size()) + 6;
  }
}
