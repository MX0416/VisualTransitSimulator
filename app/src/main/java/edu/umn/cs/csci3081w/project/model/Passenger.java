package edu.umn.cs.csci3081w.project.model;

import java.io.PrintStream;

/**
 * Passenger class that represents passenger of a Vehicle.
 */
public class Passenger {

  private String name;
  private int destinationStopId;
  private int waitAtStop;
  private int timeOnVehicle;

  /**
   * Constructor for passenger.
   *
   * @param name              name of passenger
   * @param destinationStopId destination stop id
   */
  public Passenger(int destinationStopId, String name) {
    this.name = name;
    this.destinationStopId = destinationStopId;
    this.waitAtStop = 0;
    this.timeOnVehicle = 0;
  }

  /**
   * Updates time variables for passenger.
   */
  public void pasUpdate() {
    if (isOnVehicle()) {
      timeOnVehicle++;
    } else {
      waitAtStop++;
    }
  }

  /**
   * set the time on the vehicle to 1.
   */
  public void setOnVehicle() {
    timeOnVehicle = 1;
  }

  /**
   * returns if the time on Vehicle is greater than zero.
   * @return boolean.
   */
  public boolean isOnVehicle() {
    return timeOnVehicle > 0;
  }

  /**
   * returns destination stop id.
   * @return desitnationStopId as int.
   */
  public int getDestination() {
    return destinationStopId;
  }

  /**
   * Report statistics for passenger.
   *
   * @param out stream for printing
   */
  public void report(PrintStream out) {
    out.println("####Passenger Info Start####");
    out.println("Name: " + name);
    out.println("Destination: " + destinationStopId);
    out.println("Wait at stop: " + waitAtStop);
    out.println("Time on vehicle: " + timeOnVehicle);
    out.println("####Passenger Info End####");
  }

  /**
   * returns wait time at the stop.
   * @return waitAtStop as int.
   */
  public int getWaitAtStop() {
    return waitAtStop;
  }

  /**
   * returns time on the vehicle.
   * @return timeOnVehicle as int.
   */
  public int getTimeOnVehicle() {
    return timeOnVehicle;
  }

  /**
   * returns name of passenger.
   * @return name as String.
   */
  public String getName() {
    return name;
  }

}
