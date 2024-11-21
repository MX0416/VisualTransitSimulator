package edu.umn.cs.csci3081w.project.model;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class PositionTest {

  /**
   * Test if Position constructor works and it returns the
   * correct values.
   */
  @Test
  public void testPosition() {
    Position pos1 = new Position(55.0, 29.4);
    assertEquals(55.0, pos1.getLongitude());
    assertEquals(29.4, pos1.getLatitude());

    Position pos2 = new Position(0.99, 29.4);
    assertEquals(0.99, pos2.getLongitude());
    assertEquals(29.4, pos2.getLatitude());
  }
}
