package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassengerUnloaderTest {
  private List<Passenger> passengers;

  /**
   * Load passengers before the tests.
   */
  @BeforeEach
  public void setup() {
    PassengerLoader loader = new PassengerLoader();
    List<Passenger> pass = new ArrayList<>();
    Passenger testPassenger1 = new Passenger(2, "testPassenger1");
    Passenger testPassenger2 = new Passenger(2, "testPassenger2");
    Passenger testPassenger3 = new Passenger(2, "testPassenger3");
    Passenger testPassenger4 = new Passenger(1, "testPassenger4");
    int max = 5;
    loader.loadPassenger(testPassenger1, max, pass);
    loader.loadPassenger(testPassenger2, max, pass);
    loader.loadPassenger(testPassenger3, max, pass);
    loader.loadPassenger(testPassenger4, max, pass);

    this.passengers = pass;

  }


  /**
   * Tests if unloadPassengers works as expected.
   */
  @Test
  public void testUnloadPassengers() {
    PassengerUnloader unloader = new PassengerUnloader();
    Stop stop1 = new Stop(0, "test stop 1", new Position(-93.243774, 44.972392));
    Stop stop2 = new Stop(1, "test stop 2", new Position(-93.235071, 44.973580));
    Stop stop3 = new Stop(2, "test stop 3", new Position(-93.226632, 44.975392));
    Stop stop4 = new Stop(3, "test stop 4", new Position(-93.226680, 44.975392));

    assertEquals(4, passengers.size());

    int unload1 = unloader.unloadPassengers(passengers, stop1);
    assertEquals(0, unload1);
    assertEquals(4, passengers.size());

    int unload2 = unloader.unloadPassengers(passengers, stop2);
    assertEquals(1, unload2);
    assertEquals(3, passengers.size());

    int unload3 = unloader.unloadPassengers(passengers, stop3);
    assertEquals(3, unload3);
    assertEquals(0, passengers.size());

    int unload4 = unloader.unloadPassengers(passengers, stop4);
    assertEquals(0, unload4);
    assertEquals(0, passengers.size());

  }


}
