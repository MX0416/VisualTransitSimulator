package edu.umn.cs.csci3081w.project.model;

import java.util.List;

/**
 * VehicleObserver interface.
 */
public interface VehicleObserver {

  /**
   * interface method updateCO2.
   * @param curVehicle current Vehicle.
   * @param prevCO2 previous CO2.
   */
  void updateCO2(Vehicle curVehicle, List<Integer> prevCO2);

}
