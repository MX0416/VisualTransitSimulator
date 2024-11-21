package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class StorageFacilityTest {


  /**
   * Test that the StorageFacility constructors work
   * and decrement and increment properly.
   */
  @Test
  public void testStorageFacility() {
    StorageFacility storage1 = new StorageFacility();
    StorageFacility storage2 = new StorageFacility(5, 6, 4, 7);

    assertEquals(0, storage1.getLargeBusesNum());
    assertEquals(0, storage1.getSmallBusesNum());
    assertEquals(0, storage1.getElectricTrainsNum());
    assertEquals(0, storage1.getDieselTrainsNum());
    assertEquals(5, storage2.getLargeBusesNum());
    assertEquals(6, storage2.getSmallBusesNum());
    assertEquals(4, storage2.getElectricTrainsNum());
    assertEquals(7, storage2.getDieselTrainsNum());

    storage1.incrementLargeBusesNum();
    storage1.incrementSmallBusesNum();
    storage1.incrementLargeBusesNum();
    storage1.incrementElectricTrainsNum();
    storage1.incrementDieselTrainsNum();

    storage2.decrementLargeBusesNum();
    storage2.decrementSmallBusesNum();
    storage2.decrementLargeBusesNum();
    storage2.decrementElectricTrainsNum();
    storage2.decrementDieselTrainsNum();

    assertEquals(2, storage1.getLargeBusesNum());
    assertEquals(1, storage1.getSmallBusesNum());
    assertEquals(1, storage1.getElectricTrainsNum());
    assertEquals(1, storage1.getElectricTrainsNum());

    assertEquals(3, storage2.getLargeBusesNum());
    assertEquals(5, storage2.getSmallBusesNum());
    assertEquals(3, storage2.getElectricTrainsNum());
    assertEquals(6, storage2.getDieselTrainsNum());

  }
}
