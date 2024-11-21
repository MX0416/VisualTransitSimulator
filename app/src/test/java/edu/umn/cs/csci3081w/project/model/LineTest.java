package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class LineTest {

  private Line line;
  private Route testRouteOut;
  private Route testRouteIn;
  private Route simpleTestRouteIn;
  private Route simpleTestRouteOut;
  private Line simpleLine;


  /**
   * Set up a Line before each test.
   */
  @BeforeEach
  public void setup() {
    Stop stop1 = new Stop(0, "test stop 1", new Position(-93.243774, 44.972392));
    Stop stop2 = new Stop(1, "test stop 2", new Position(-93.235071, 44.973580));
    Stop stop3 = new Stop(2, "test stop 3", new Position(-93.226632, 44.975392));
    List<Stop> stopsOut = new ArrayList<Stop>();
    stopsOut.add(stop1);
    stopsOut.add(stop2);
    stopsOut.add(stop3);
    List<Double> distancesOut = new ArrayList<Double>();
    distancesOut.add(0.9712663713083954);
    distancesOut.add(0.961379387775189);
    List<Double> probabilitiesOut = new ArrayList<Double>();
    probabilitiesOut.add(.15);
    probabilitiesOut.add(0.3);
    probabilitiesOut.add(.0);
    PassengerGenerator generatorOut = new RandomPassengerGenerator(stopsOut, probabilitiesOut);
    testRouteOut = new Route(10, "testRouteOut",
        stopsOut, distancesOut, generatorOut);

    List<Stop> stopsIn = new ArrayList<>();
    stopsIn.add(stop3);
    stopsIn.add(stop2);
    stopsIn.add(stop1);
    List<Double> distancesIn = new ArrayList<>();
    distancesIn.add(0.961379387775189);
    distancesIn.add(0.9712663713083954);
    List<Double> probabilitiesIn = new ArrayList<>();
    probabilitiesIn.add(.4);
    probabilitiesIn.add(0.3);
    probabilitiesIn.add(.0);
    PassengerGenerator generatorIn = new RandomPassengerGenerator(stopsIn, probabilitiesIn);
    testRouteIn = new Route(11, "testRouteIn",
        stopsIn, distancesIn, generatorIn);

    this.line = new Line(10000, "testLine", "BUS", testRouteOut, testRouteIn);


    List<Stop> simpleStopsIn = new ArrayList<Stop>();
    Stop simpleStop1 = new Stop(0, "test stop", new Position(-93.243774, 44.972392));
    simpleStopsIn.add(simpleStop1);
    List<Double> simpleDistancesIn = new ArrayList<>();
    simpleDistancesIn.add(0.961379387775189);
    List<Double> simpleProbabilitiesIn = new ArrayList<Double>();
    simpleProbabilitiesIn.add(.5);
    PassengerGenerator simpleGeneratorIn = new RandomPassengerGenerator(simpleStopsIn,
        simpleProbabilitiesIn);

    simpleTestRouteIn = new Route(0, "simpleTestRouteIn",
        simpleStopsIn, simpleDistancesIn, simpleGeneratorIn);

    List<Stop> simpleStopsOut = new ArrayList<Stop>();
    Stop simpleStop2 = new Stop(1, "test stop", new Position(-93.243774, 44.972392));
    simpleStopsOut.add(simpleStop2);
    List<Double> simpleDistancesOut = new ArrayList<>();
    simpleDistancesIn.add(0.961379387775170);
    List<Double> simpleProbabilitiesOut = new ArrayList<Double>();
    simpleProbabilitiesOut.add(.7);
    PassengerGenerator simpleGeneratorOut = new RandomPassengerGenerator(simpleStopsOut,
        simpleProbabilitiesOut);

    simpleTestRouteOut = new Route(5, "simpleTestRouteOut",
        simpleStopsOut, simpleDistancesOut, simpleGeneratorOut);

    this.simpleLine = new Line(900, "simpleLine", "BUS", simpleTestRouteOut, simpleTestRouteIn);
  }


  /**
   * Test a constructor for a Line.
   */
  @Test
  public void testLineConstructor() {
    assertEquals(10000, line.getId());
    assertEquals("testLine", line.getName());
    assertEquals("BUS", line.getType());

    assertEquals(11, line.getInboundRoute().getId());
    assertEquals("testRouteIn", line.getInboundRoute().getName());
    assertEquals(3, line.getInboundRoute().getStops().size());

    assertEquals(10, line.getOutboundRoute().getId());
    assertEquals("testRouteOut", line.getOutboundRoute().getName());
    assertEquals(3, line.getOutboundRoute().getStops().size());
  }


  /**
   * Test to see if a shallowCopy of a Line
   * keeps all the correct data.
   */
  @Test
  public void testLineShallowCopy() {
    Line lineCopy = line.shallowCopy();

    assertEquals(lineCopy.getId(), line.getId());
    assertEquals(lineCopy.getName(), line.getName());
    assertEquals(lineCopy.getType(), line.getType());

    assertEquals(lineCopy.getInboundRoute().getId(), line.getInboundRoute().getId());
    assertEquals(lineCopy.getInboundRoute().getName(), line.getInboundRoute().getName());
    assertEquals(lineCopy.getInboundRoute().getStops(), line.getInboundRoute().getStops());
    assertEquals(lineCopy.getInboundRoute().getStops().size(),
        line.getInboundRoute().getStops().size());

    assertEquals(lineCopy.getOutboundRoute().getId(), line.getOutboundRoute().getId());
    assertEquals(lineCopy.getOutboundRoute().getName(), line.getOutboundRoute().getName());
    assertEquals(lineCopy.getOutboundRoute().getStops().size(),
        line.getOutboundRoute().getStops().size());
    assertEquals(lineCopy.getOutboundRoute().getStops(), line.getOutboundRoute().getStops());

    line.getOutboundRoute().nextStop();

    assertEquals(1, line.getOutboundRoute().getNextStopIndex());
    assertEquals(0, lineCopy.getOutboundRoute().getNextStopIndex());

  }


  /**
   * Test the reporting method for a Line.
   */
  @Test
  public void testLineReport() {
    try {
      final Charset charset = StandardCharsets.UTF_8;
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      PrintStream testStream = new PrintStream(outputStream, true, charset.name());
      simpleLine.report(testStream);
      outputStream.flush();
      String data = new String(outputStream.toByteArray(), charset);
      testStream.close();
      outputStream.close();
      String strToCompare =
          "====Line Info Start====" + System.lineSeparator()
              + "ID: 900" + System.lineSeparator()
              + "Name: simpleLine" + System.lineSeparator()
              + "Type: BUS" + System.lineSeparator()

              + "####Route Info Start####" + System.lineSeparator()
              + "ID: 5" + System.lineSeparator()
              + "Name: simpleTestRouteOut" + System.lineSeparator()
              + "Num stops: 1" + System.lineSeparator()
              + "****Stops Info Start****" + System.lineSeparator()
              + "++++Next Stop Info Start++++" + System.lineSeparator()
              + "####Stop Info Start####" + System.lineSeparator()
              + "ID: 1" + System.lineSeparator()
              + "Name: test stop" + System.lineSeparator()
              + "Position: 44.972392,-93.243774" + System.lineSeparator()
              + "****Passengers Info Start****" + System.lineSeparator()
              + "Num passengers waiting: 0" + System.lineSeparator()
              + "****Passengers Info End****" + System.lineSeparator()
              + "####Stop Info End####" + System.lineSeparator()
              + "++++Next Stop Info End++++" + System.lineSeparator()
              + "****Stops Info End****" + System.lineSeparator()
              + "####Route Info End####" + System.lineSeparator()

              + "####Route Info Start####" + System.lineSeparator()
              + "ID: 0" + System.lineSeparator()
              + "Name: simpleTestRouteIn" + System.lineSeparator()
              + "Num stops: 1" + System.lineSeparator()
              + "****Stops Info Start****" + System.lineSeparator()
              + "++++Next Stop Info Start++++" + System.lineSeparator()
              + "####Stop Info Start####" + System.lineSeparator()
              + "ID: 0" + System.lineSeparator()
              + "Name: test stop" + System.lineSeparator()
              + "Position: 44.972392,-93.243774" + System.lineSeparator()
              + "****Passengers Info Start****" + System.lineSeparator()
              + "Num passengers waiting: 0" + System.lineSeparator()
              + "****Passengers Info End****" + System.lineSeparator()
              + "####Stop Info End####" + System.lineSeparator()
              + "++++Next Stop Info End++++" + System.lineSeparator()
              + "****Stops Info End****" + System.lineSeparator()
              + "####Route Info End####" + System.lineSeparator()

              + "====Line Info End====" + System.lineSeparator();
      assertEquals(data, strToCompare);
    } catch (IOException ioe) {
      fail();
    }


  }


}
