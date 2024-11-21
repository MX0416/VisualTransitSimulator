package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class C02ObserverTest {
  private Bus testBus;
  private Route testRouteIn;
  private Route testRouteOut;

  /**
   * Setup operations before each test runs.
   */
  @BeforeEach
  public void setUp() {
    List<Stop> stopsIn = new ArrayList<Stop>();
    Stop stop1 = new Stop(0, "test stop 1", new Position(-93.243774, 44.972392));
    Stop stop2 = new Stop(1, "test stop 2", new Position(-93.235071, 44.973580));
    Stop stop3 = new Stop(2, "test stop 3", new Position(-93.235072, 44.973565));
    stopsIn.add(stop1);
    stopsIn.add(stop2);
    stopsIn.add(stop3);
    List<Double> distancesIn = new ArrayList<>();
    distancesIn.add(0.843774422231134);
    distancesIn.add(0.843774422231348);
    List<Double> probabilitiesIn = new ArrayList<Double>();
    probabilitiesIn.add(.025);
    probabilitiesIn.add(0.3);
    probabilitiesIn.add(0.5);
    PassengerGenerator generatorIn = new RandomPassengerGenerator(stopsIn, probabilitiesIn);

    testRouteIn = new Route(0, "testRouteIn",
        stopsIn, distancesIn, generatorIn);

    List<Stop> stopsOut = new ArrayList<Stop>();
    stopsOut.add(stop3);
    stopsOut.add(stop2);
    stopsOut.add(stop1);
    List<Double> distancesOut = new ArrayList<>();
    distancesOut.add(0.843774422231134);
    distancesOut.add(0.843774422231348);
    List<Double> probabilitiesOut = new ArrayList<Double>();
    probabilitiesOut.add(0.5);
    probabilitiesOut.add(0.3);
    probabilitiesOut.add(.025);
    PassengerGenerator generatorOut = new RandomPassengerGenerator(stopsOut, probabilitiesOut);

    testRouteOut = new Route(1, "testRouteOut",
        stopsOut, distancesOut, generatorOut);

    testBus = new Bus(1, new Line(10000, "testLine", "BUS", testRouteOut, testRouteIn), 3, 1.0);
  }

  /**
   * Test if the prevCO2 list is updated correctly.
   */
  @Test
  public void observerTest() {
    CO2Observer observer = new CO2Observer();
    testBus.registerObserver(observer);

    assertEquals(0, testBus.getPrevCO2().get(0));
    assertEquals(0, testBus.getPrevCO2().get(4));

    testBus.move();
    int co21 = testBus.getCurrentCO2Emission();
    assertEquals(co21, testBus.getPrevCO2().get(0));

    testBus.move();
    int co22 = testBus.getCurrentCO2Emission();
    assertEquals(co22, testBus.getPrevCO2().get(0));
    assertEquals(co21, testBus.getPrevCO2().get(1));

    testBus.move();
    int co23 = testBus.getCurrentCO2Emission();
    assertEquals(co23, testBus.getPrevCO2().get(0));
    assertEquals(co21, testBus.getPrevCO2().get(2));

    testBus.move();
    int co24 = testBus.getCurrentCO2Emission();
    assertEquals(co24, testBus.getPrevCO2().get(0));
    assertEquals(co21, testBus.getPrevCO2().get(3));

    testBus.move();
    int co25 = testBus.getCurrentCO2Emission();
    assertEquals(co25, testBus.getPrevCO2().get(0));
    assertEquals(co21, testBus.getPrevCO2().get(4));


  }
}
