package edu.umn.cs.csci3081w.project.model;

import java.util.ArrayList;
import java.util.List;

/**
 * passengerGenerator for generating passengers.
 */
public abstract class PassengerGenerator {
  private List<Stop> stops;
  private List<Double> probabilities;

  /**
   * Constructor for Abstract class.
   *
   * @param stops         list of stops
   * @param probabilities list of probabilities
   */
  public PassengerGenerator(List<Stop> stops, List<Double> probabilities) {
    this.probabilities = new ArrayList<>();
    this.stops = new ArrayList<>();
    for (Stop s : stops) {
      this.stops.add(s);
    }
    for (Double probability : probabilities) {
      this.probabilities.add(probability);
    }
  }

  /**
   * abstract method.
   * @return number of passengers generated as int.
   */
  public abstract int generatePassengers();

  /**
   * returns stops.
   * @return stops as List.
   */
  public List<Stop> getStops() {
    return stops;
  }

  /**
   * returns probabilities of generating passengers.
   * @return probabilities as List.
   */
  public List<Double> getProbabilities() {
    return probabilities;
  }

}