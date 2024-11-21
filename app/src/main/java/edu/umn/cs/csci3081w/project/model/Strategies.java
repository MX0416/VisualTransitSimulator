package edu.umn.cs.csci3081w.project.model;

/**
 * Stratagies interface.
 */
interface Strategies {

  /**
   * interface isDay method.
   * @return boolen.
   */
  public boolean isDay();

  /**
   * interface getNextVehicleType method.
   * @return String.
   */
  public String getNextVehicleType();

  /**
   * interface incrementDayCycle method.
   */
  public void incrementDayCycle();

  /**
   * interface incrementNightCycle method.
   */
  public void incrementNightCycle();
}
