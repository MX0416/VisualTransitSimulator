package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class PassengerLoaderTest {


  /**
   * Tests if loadPassenger works properly with excess space.
   */
  @Test
  public void testPassengerLoaderSpaceAvailable() {
    PassengerLoader loader = new PassengerLoader();
    List<Passenger> passengers = new ArrayList<>();

    Passenger testPassenger1 = new Passenger(3, "testPassenger1");
    Passenger testPassenger2 = new Passenger(2, "testPassenger2");


    int max = 5;

    int load1 = loader.loadPassenger(testPassenger1, max, passengers);
    assertEquals(1, load1);
    assertEquals(1, passengers.size());

    int load2 = loader.loadPassenger(testPassenger2, max, passengers);
    assertEquals(1, load2);
    assertEquals(2, passengers.size());


  }

  /**
   * Tests if loadPassenger works properly with no extra space.
   */
  @Test
  public void testPassengerLoaderNoSpaceAvailable() {
    PassengerLoader loader = new PassengerLoader();
    List<Passenger> passengers = new ArrayList<>();

    Passenger testPassenger1 = new Passenger(3, "testPassenger1");
    Passenger testPassenger2 = new Passenger(2, "testPassenger2");
    Passenger testPassenger3 = new Passenger(1, "testPassenger3");

    int max = 2;

    int load1 = loader.loadPassenger(testPassenger1, max, passengers);
    assertEquals(1, load1);
    assertEquals(1, passengers.size());

    int load2 = loader.loadPassenger(testPassenger2, max, passengers);
    assertEquals(1, load2);
    assertEquals(2, passengers.size());

    int load3 = loader.loadPassenger(testPassenger3, max, passengers);
    assertEquals(0, load3);
    assertEquals(2, passengers.size());


  }


}
