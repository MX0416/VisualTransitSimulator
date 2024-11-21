package edu.umn.cs.csci3081w.project.model;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Vehicle is the parent class of two types: Bus and Train.
 */

public abstract class Vehicle {
  private int id;
  private int capacity;
  //the speed is in distance over a time unit
  private double speed;
  private PassengerLoader loader;
  private PassengerUnloader unloader;
  private List<Passenger> passengers;
  private String name;
  private Position position;
  //  refactoring 1

  /**
   * line of vehicle.
   */
  protected Line line;

  /**
   * next stop of vehicle.
   */
  protected Stop nextStop;

  /**
   * distance remaining of vehicle.
   */
  protected double distanceRemaining;

  //Feature 4
  private List<Integer> prevCO2 = new ArrayList<>();
  private List<VehicleObserver> vehicleObserver = new ArrayList<>();


  /**
   * Constructor for a vehicle.
   *
   * @param id       vehicle identifier
   * @param capacity vehicle capacity
   * @param speed    vehicle speed
   * @param loader   passenger loader for vehicle
   * @param unloader passenger unloader for vehicle
   */
  public Vehicle(int id, int capacity, double speed, PassengerLoader loader,
                 PassengerUnloader unloader) {
    this.id = id;
    this.capacity = capacity;
    this.speed = speed;
    this.loader = loader;
    this.unloader = unloader;
    this.passengers = new ArrayList<Passenger>();

    for (int i = 0; i < 5; i++) {
      prevCO2.add(0);
    }
  }


  /**
   * add observer to the registerObserver list.
   * @param o observer
   */
  public void registerObserver(VehicleObserver o) {
    this.vehicleObserver.add(o);
  }

  /**
   * remove observer from the registerObserver list.
   * @param o observer.
   */
  public void removeObserver(VehicleObserver o) {
    this.vehicleObserver.remove(o);
  }

  /**
   * Notify every observer that the C02 for the vehicle has updated
   * and update it.
   */
  public void notifyObserver() {
    for (VehicleObserver o : vehicleObserver) {
      o.updateCO2(this, prevCO2);
    }
  }

  /**
   * returns the previous CO2 in the list.
   * @return prevCO2.
   */
  public List<Integer> getPrevCO2() {
    return prevCO2;
  }

  /**
   * report PrintStream.
   * @param out type PrintStream.
   */

  public abstract void report(PrintStream out);

  //  refactoring 1

  /**
   * determins if the trip is complete based on inbound and outbound routes.
   * @return boolean.
   */
  public boolean isTripComplete() {
    return line.getOutboundRoute().isAtEnd() && line.getInboundRoute().isAtEnd();
  }

  ;

  //  refactoring 1

  /**
   * load passenger along with capacity.
   * @param newPassenger Passenger.
   * @return int.
   */
  public int loadPassenger(Passenger newPassenger) {
    return getPassengerLoader().loadPassenger(newPassenger, getCapacity(), getPassengers());
  }

  ;

  //  refactoring 1

  /**
   * update by calling move().
   */
  public void update() {
    move();
  }

  //  refactoring 1

  /**
   * unload passengers at next stop.
   * @return number of passengers to unload.
   */
  protected int unloadPassengers() {
    return getPassengerUnloader().unloadPassengers(getPassengers(), nextStop);
  }

  /**
   * handles arrival at vehicle stop.
   * @return number of passengers handled.
   */
  protected int handleStop() {
    // This function handles arrival at a train stop
    int passengersHandled = 0;
    // unloading passengers
    passengersHandled += unloadPassengers();
    // loading passengers
    passengersHandled += nextStop.loadPassengers(this);
    if (passengersHandled != 0) {
      distanceRemaining = 0;
    }
    return passengersHandled;
  }

  /**
   * determine the next stop if one exists.
   */
  protected void toNextStop() {
    //current stop
    currentRoute().nextStop();
    if (!isTripComplete()) {
      // it's important we call currentRoute() again,
      // as nextStop() may have caused it to change.
      nextStop = currentRoute().getNextStop();
      distanceRemaining +=
          currentRoute().getNextStopDistance();
      // note, if distanceRemaining was negative because we
      // had extra time left over, that extra time is
      // effectively counted towards the next stop
    } else {
      nextStop = null;
      distanceRemaining = 999;
    }
  }

  //  refactoring 1

  /**
   * figure out is the current route is an Inbound or Outbound Route.
   * @return Route.
   */
  protected Route currentRoute() {
    // Figure out if we're on the outgoing or incoming route
    if (!line.getOutboundRoute().isAtEnd()) {
      return line.getOutboundRoute();
    }
    return line.getInboundRoute();
  }

  //  refactoring 1

  /**
   * update distance remaining.
   * @return speed of Vehicle.
   */
  protected double updateDistance() {
    // Updates the distance remaining and returns the effective speed of the vehicle
    //  does not move if speed is negative or train is at end of route
    if (isTripComplete()) {
      return 0;
    }
    if (getSpeed() < 0) {
      return 0;
    }
    distanceRemaining -= getSpeed();
    return getSpeed();
  }

  //  refactoring 1

  /**
   * Moves the vehicle on its route.
   */
  public void move() {
    // update passengers FIRST
    // new passengers will get "updated" when getting on the train
    for (Passenger passenger : getPassengers()) {
      passenger.pasUpdate();
    }
    //actually move
    double speed = updateDistance();
    if (!isTripComplete() && distanceRemaining <= 0) {
      //load & unload
      int passengersHandled = handleStop();
      if (passengersHandled >= 0) {
        distanceRemaining = 0;
      }
      //switch to next stop
      toNextStop();
    }

    // Get the correct route and early exit
    Route currentRoute = line.getOutboundRoute();
    if (line.getOutboundRoute().isAtEnd()) {
      if (line.getInboundRoute().isAtEnd()) {
        return;
      }
      currentRoute = line.getInboundRoute();
    }
    Stop prevStop = currentRoute.prevStop();
    Stop nextStop = currentRoute.getNextStop();
    double distanceBetween = currentRoute.getNextStopDistance();
    // the ratio shows us how far from the previous stop are we in a ratio from 0 to 1
    double ratio;
    // check if we are at the first stop
    if (distanceBetween - 0.00001 < 0) {
      ratio = 1;
    } else {
      ratio = distanceRemaining / distanceBetween;
      if (ratio < 0) {
        ratio = 0;
        distanceRemaining = 0;
      }
    }
    double newLongitude = nextStop.getPosition().getLongitude() * (1 - ratio)
        + prevStop.getPosition().getLongitude() * ratio;
    double newLatitude = nextStop.getPosition().getLatitude() * (1 - ratio)
        + prevStop.getPosition().getLatitude() * ratio;
    setPosition(new Position(newLongitude, newLatitude));

    //Feature 4
    notifyObserver();
  }

  /**
   * abstract method for getting current Vehicle CO2 emission.
   * @return CO2 emission
   */
  public abstract int getCurrentCO2Emission();

  /**
   * returns Vehicle Id.
   * @return Id as int.
   */
  public int getId() {
    return id;
  }

  /**
   * returns Vehicle capacity.
   * @return capacity as int.
   */
  public int getCapacity() {
    return capacity;
  }

  /**
   * returns the Vehicle speed.
   * @return speed as double
   */
  public double getSpeed() {
    return speed;
  }

  /**
   * returns Vehicle Passenger loader.
   * @return loader as PassengerLoader.
   */
  public PassengerLoader getPassengerLoader() {
    return loader;
  }

  /**
   * returns Vehicle Passenger unloader.
   * @return unloader as PassengerUnloader.
   */
  public PassengerUnloader getPassengerUnloader() {
    return unloader;
  }

  /**
   * returns passenger of Vehicle.
   * @return passenger as List.
   */
  public List<Passenger> getPassengers() {
    return passengers;
  }

  /**
   * returns name of Vehicle.
   * @return name as String.
   */
  public String getName() {
    return name;
  }

  /**
   * sets the name of the Vehicle.
   * @param name name of Vehicle.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * returns position of Vehicle.
   * @return position as Position.
   */
  public Position getPosition() {
    return position;
  }

  /**
   * sets position of Vehicle.
   * @param position position of vehicle.
   */
  public void setPosition(Position position) {
    this.position = position;
  }

  //  refactoring 1

  /**
   * returns next stop of Vehicle.
   * @return nextStop as Stop.
   */
  public Stop getNextStop() {
    return nextStop;
  }

  //  refactoring 1

  /**
   * returns line of Vehicle.
   * @return line as Line.
   */
  public Line getLine() {
    return line;
  }

}
