package edu.umn.cs.csci3081w.project.model;

/**
 * LargeBus extends Bus.
 */
public class LargeBus extends Bus {

  /**
   * Capacity of LargeBus Vehicle.
   */
  public static final int LARGE_BUS_CAPACITY = 80;

  /**
   * Speed of LargeBus Vehicle.
   */
  public static final double LARGE_BUS_SPEED = 0.5;

  /**
   * Name for LargeBus Vehicle.
   */
  public static final String BUS_VEHICLE = "LARGE_BUS_VEHICLE";





  /**
   * Constructor for LargeBus.
   *
   * @param id   id.
   * @param line line.
   */

  public LargeBus(int id, Line line) {
    super(id, line, LARGE_BUS_CAPACITY, LARGE_BUS_SPEED);
    setName(BUS_VEHICLE);
  }

  /**
   * Calculates CO2 for LargeBus.
   *
   * @return CO2 for LargeBus.
   */
  @Override
  public int getCurrentCO2Emission() {
    return (2 * getPassengers().size()) + 5;
  }
}
