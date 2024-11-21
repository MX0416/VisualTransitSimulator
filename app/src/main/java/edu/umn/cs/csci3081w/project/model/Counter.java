package edu.umn.cs.csci3081w.project.model;

/**
 * Counter of route, and Bus/Train types.
 */
public class Counter {

  /**
   * Counter for route id.
   */
  public int routeIdCounter = 10;

  /**
   * Counter for stop id.
   */
  public int stopIdCounter = 100;

  /**
   * Counter for bus id.
   */
  public int busIdCounter = 1000;

  /**
   * Counter for train id.
   */
  public int trainIdCounter = 2000;

  /**
   * Counter for line id.
   */
  public int lineIdCounter = 10000;

  /**
   * Constructor for counter.
   */
  public Counter() {

  }

  /**
   * returns routeIdCounter plus one.
   * @return routeIdCounter++ as int.
   */
  public int getRouteIdCounterAndIncrement() {
    return routeIdCounter++;
  }

  /**
   * returns stopIdCounter plus one.
   * @return stopIdCounter++ as int.
   */
  public int getStopIdCounterAndIncrement() {
    return stopIdCounter++;
  }

  /**
   * returns busIdCouter plus one.
   * @return busIdCounter++ as int.
   */
  public int getBusIdCounterAndIncrement() {
    return busIdCounter++;
  }

  /**
   * returns increment of trainIdCounter.
   * @return trainIdCounter++ as int.
   */
  public int getTrainIdCounterAndIncrement() {
    return trainIdCounter++;
  }

  /**
   * returns increment of lineIdCounter.
   * @return lineIdCounter++ as int.
   */
  public int getLineIdCounterAndIncrement() {
    return lineIdCounter++;
  }

}
