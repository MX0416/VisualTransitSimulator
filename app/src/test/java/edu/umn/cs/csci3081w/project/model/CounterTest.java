package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class CounterTest {


  /**
   * Test the Counter constructor and its getters and increments.
   */
  @Test
  public void testCounter() {
    Counter counter = new Counter();

    assertEquals(10, counter.routeIdCounter);
    assertEquals(100, counter.stopIdCounter);
    assertEquals(1000, counter.busIdCounter);
    assertEquals(2000, counter.trainIdCounter);
    assertEquals(10000, counter.lineIdCounter);

    counter.getRouteIdCounterAndIncrement();
    counter.getLineIdCounterAndIncrement();
    counter.getStopIdCounterAndIncrement();
    counter.getTrainIdCounterAndIncrement();
    counter.getBusIdCounterAndIncrement();

    assertEquals(counter.getRouteIdCounterAndIncrement(), 11);
    assertEquals(101, counter.getStopIdCounterAndIncrement());
    assertEquals(1001, counter.getBusIdCounterAndIncrement());
    assertEquals(2001, counter.getTrainIdCounterAndIncrement());
    assertEquals(10001, counter.getLineIdCounterAndIncrement());


  }
}
