package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PassengerFactoryTest {

  /**
   * Set up before each test.
   */
  @BeforeEach
  public void setup() {
    PassengerFactory.DETERMINISTIC = true;
    PassengerFactory.DETERMINISTIC_NAMES_COUNT = 4;
    PassengerFactory.DETERMINISTIC_DESTINATION_COUNT = 4;
  }

  /**
   * Test if name generation works.
   */
  @Test
  public void testNameGeneration() {
    String name = PassengerFactory.nameGeneration();
    String name2 = PassengerFactory.nameGeneration();
    String name3 = PassengerFactory.nameGeneration();
    String name4 = PassengerFactory.nameGeneration();

    assertEquals(name, "President");
    assertEquals(name2, "Coach");
    assertEquals(name3, "Goldy");
    assertEquals(name4, "President");
  }


  /**
   * Test if a Passenger is properly generated.
   */
  @Test
  public void testPassengerGeneration() {
    Passenger pass1 = PassengerFactory.generate(1, 4);

    assertEquals(pass1.getName(), "President");
    assertEquals(pass1.getDestination(), 4);
    assertEquals(pass1.getTimeOnVehicle(), 0);
    assertEquals(pass1.getWaitAtStop(), 0);

    Passenger pass2 = PassengerFactory.generate(1, 4);

    assertEquals(pass2.getName(), "Coach");
    assertEquals(pass2.getDestination(), 2);
    assertEquals(pass2.getTimeOnVehicle(), 0);
    assertEquals(pass2.getWaitAtStop(), 0);

    Passenger pass3 = PassengerFactory.generate(1, 4);

    assertEquals(pass3.getName(), "Goldy");
    assertEquals(pass3.getDestination(), 3);
    assertEquals(pass3.getTimeOnVehicle(), 0);
    assertEquals(pass3.getWaitAtStop(), 0);

    Passenger pass4 = PassengerFactory.generate(1, 4);

    assertEquals(pass4.getName(), "President");
    assertEquals(pass4.getDestination(), 4);
    assertEquals(pass4.getTimeOnVehicle(), 0);
    assertEquals(pass4.getWaitAtStop(), 0);

  }


}
