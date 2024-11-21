package edu.umn.cs.csci3081w.project.model;

import java.util.List;

/**
 * CO2Observer implements VehicleObserver.
 */
public class CO2Observer implements VehicleObserver {

  /**
   * Default constructor for CO2Observer.
   */
  public CO2Observer() {}


  /**
   * Updates the CO2 list with the current CO2 value
   * and removes the oldest one.
   * @param curVehicle vehicle.
   * @param prevCO2 co2.
   */
  @Override
  public void updateCO2(Vehicle curVehicle, List<Integer> prevCO2) {
    int newC02 = curVehicle.getCurrentCO2Emission();
    prevCO2.remove(4);
    prevCO2.add(0, newC02);

  }

}
