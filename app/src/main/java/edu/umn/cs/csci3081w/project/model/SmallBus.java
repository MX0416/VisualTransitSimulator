package edu.umn.cs.csci3081w.project.model;

/**
 * SmallBus extends Bus.
 */
public class SmallBus extends Bus {
  /**
   * capacity of SmallBus Vehicle.
   */
  public static final int SMALL_BUS_CAPACITY = 20;
  /**
   * speed for SmallBus Vehicle.
   */
  public static final double SMALL_BUS_SPEED = 0.5;
  /**
   * name of SmallBus Vehicle.
   */

  public static final String BUS_VEHICLE = "SMALL_BUS_VEHICLE";

  /**
   * constructor for SmallBus.
   * @param id id.
   * @param line line.
   */

  public SmallBus(int id, Line line) {
    super(id, line, SMALL_BUS_CAPACITY, SMALL_BUS_SPEED);
    setName(BUS_VEHICLE);
  }

  /**
   * calculates CO2 emission for SmallBus.
   * @return CO2 emission for SmallBus.
   */
  @Override
  public int getCurrentCO2Emission() {
    return (2 * getPassengers().size()) + 3;
  }


}
