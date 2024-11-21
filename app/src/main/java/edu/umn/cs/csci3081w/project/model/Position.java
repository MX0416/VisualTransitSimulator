package edu.umn.cs.csci3081w.project.model;

/**
 * Position. determines latitude and longitue of Vehicle.
 */
public class Position {

  private double longitude;
  private double latitude;

  /**
   * Position constructor.
   * @param longitude longitude.
   * @param latitude latitude.
   */
  public Position(double longitude, double latitude) {
    this.longitude = longitude;
    this.latitude = latitude;
  }

  /**
   * returns longitude of Vehicle.
   * @return longitude as double.
   */
  public double getLongitude() {
    return longitude;
  }

  /**
   * returns latitude of Vehicle.
   * @return latitude as double.
   */
  public double getLatitude() {
    return latitude;
  }

}
