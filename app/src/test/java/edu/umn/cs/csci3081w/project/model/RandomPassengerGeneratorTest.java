package edu.umn.cs.csci3081w.project.model;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class RandomPassengerGeneratorTest {

  /**
   * Set up before each test.
   */
  @BeforeEach
  public void setUp() {
    RandomPassengerGenerator.DETERMINISTIC = true;
  }

  /**
   * Create a passenger generator.
   */
  public RandomPassengerGenerator createGenerator() {
    Stop stop1 = new Stop(0, "test stop 1", new Position(-93.243774, 44.972392));
    Stop stop2 = new Stop(1, "test stop 2", new Position(-93.235071, 44.973580));
    Stop stop3 = new Stop(2, "test stop 3", new Position(-93.226632, 44.975392));
    List<Stop> stopsOut = new ArrayList<Stop>();
    stopsOut.add(stop1);
    stopsOut.add(stop2);
    stopsOut.add(stop3);
    List<Double> probabilitiesOut = new ArrayList<Double>();
    probabilitiesOut.add(.15);
    probabilitiesOut.add(0.3);
    probabilitiesOut.add(.0);
    return new RandomPassengerGenerator(stopsOut, probabilitiesOut);
  }


  /**
   * Test the constructor for Random Passenger Generator.
   */
  @Test
  public void testRandomPassGenConstructor() {
    RandomPassengerGenerator generator = createGenerator();

    assertEquals(3, generator.getStops().size());
    assertEquals(3, generator.getProbabilities().size());

    assertEquals(0, generator.getStops().get(0).getId());
    assertEquals(1, generator.getStops().get(1).getId());
    assertEquals(2, generator.getStops().get(2).getId());
    assertEquals("test stop 1", generator.getStops().get(0).getName());
    assertEquals("test stop 2", generator.getStops().get(1).getName());
    assertEquals("test stop 3", generator.getStops().get(2).getName());

    assertEquals(0.15, generator.getProbabilities().get(0));
    assertEquals(0.3, generator.getProbabilities().get(1));
    assertEquals(.0, generator.getProbabilities().get(2));
  }

  /**
   * Test the generate passengers function with a
   * deterministic value.
   */
  @Test
  public void testGeneratePassengers() {
    RandomPassengerGenerator generator = createGenerator();
    int passengers = generator.generatePassengers();

    assertEquals(2, passengers);
  }


}
