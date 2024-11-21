package edu.umn.cs.csci3081w.project.webserver;


import edu.umn.cs.csci3081w.project.model.BusStrategies;
import edu.umn.cs.csci3081w.project.model.CO2Observer;
import edu.umn.cs.csci3081w.project.model.Counter;
import edu.umn.cs.csci3081w.project.model.DieselTrain;
import edu.umn.cs.csci3081w.project.model.ElectricTrain;
import edu.umn.cs.csci3081w.project.model.LargeBus;
import edu.umn.cs.csci3081w.project.model.Line;
import edu.umn.cs.csci3081w.project.model.Route;
import edu.umn.cs.csci3081w.project.model.SmallBus;
import edu.umn.cs.csci3081w.project.model.StorageFacility;
import edu.umn.cs.csci3081w.project.model.TrainStrategies;
import edu.umn.cs.csci3081w.project.model.Vehicle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



/**
 * VisualTransitSimulator. starts and updates the simulation.
 */
public class VisualTransitSimulator {

  private static boolean LOGGING = false;
  private int numTimeSteps = 0;
  private int simulationTimeElapsed = 0;
  private Counter counter;
  private List<Line> lines;
  private List<Vehicle> activeVehicles;
  private List<Vehicle> completedTripVehicles;
  private List<Integer> vehicleStartTimings;
  private List<Integer> timeSinceLastVehicle;
  private StorageFacility storageFacility;
  private WebServerSession webServerSession;
  private boolean paused = false;
  private final BusStrategies busStrat;
  private final TrainStrategies trainStrat;
  HashMap<Integer, Integer> lineIssues = new HashMap<Integer, Integer>();
  private CO2Observer observer = new CO2Observer();


  /**
   * Constructor for Simulation.
   *
   * @param configFile       file containing the simulation configuration
   * @param webServerSession session associated with the simulation
   */
  public VisualTransitSimulator(String configFile, WebServerSession webServerSession) {
    this.webServerSession = webServerSession;
    this.counter = new Counter();
    ConfigManager configManager = new ConfigManager();
    configManager.readConfig(counter, configFile);
    this.lines = configManager.getLines();
    this.activeVehicles = new ArrayList<Vehicle>();
    this.completedTripVehicles = new ArrayList<Vehicle>();
    this.vehicleStartTimings = new ArrayList<Integer>();
    this.timeSinceLastVehicle = new ArrayList<Integer>();
    this.storageFacility = configManager.getStorageFacility();
    this.busStrat = new BusStrategies();
    this.trainStrat = new TrainStrategies();
    if (this.storageFacility == null) {
      this.storageFacility = new StorageFacility(Integer.MAX_VALUE,
          Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    if (VisualTransitSimulator.LOGGING) {
      System.out.println("////Simulation Routes////");
      for (int i = 0; i < lines.size(); i++) {
        lines.get(i).getOutboundRoute().report(System.out);
        lines.get(i).getInboundRoute().report(System.out);
      }
    }
  }

  /**
   * Starts the simulation.
   *
   * @param vehicleStartTimings start timings of bus
   * @param numTimeSteps        number of time steps
   */
  public void start(List<Integer> vehicleStartTimings, int numTimeSteps) {
    this.vehicleStartTimings = vehicleStartTimings;
    this.numTimeSteps = numTimeSteps;
    for (int i = 0; i < vehicleStartTimings.size(); i++) {
      this.timeSinceLastVehicle.add(i, 0);
    }
    simulationTimeElapsed = 0;
  }

  /**
   * Toggles the pause state of the simulation.
   */
  public void togglePause() {
    paused = !paused;
  }

  /**
   * Updates the simulation at each step.
   */
  public void update() {
    if (!paused) {
      simulationTimeElapsed++;
      if (simulationTimeElapsed > numTimeSteps) {
        return;
      }
      System.out.println("~~~~The simulation time is now at time step "
          + simulationTimeElapsed + "~~~~");
      // generate vehicles
      for (int i = 0; i < timeSinceLastVehicle.size(); i++) {
        if (timeSinceLastVehicle.get(i) <= 0) {
          Line line = lines.get(i);
          Route outbound = line.getOutboundRoute();
          Route inbound = line.getInboundRoute();
          // bus line
          // feature 2
          if (line.getType().equals(Line.BUS_LINE)) {
            // day time
            if (busStrat.isDay()) {
              if (busStrat.getNextVehicleType().equals("large")) {
                // check if there are large buses left
                if (storageFacility.getLargeBusesNum() > 0) {
                  Vehicle currVehicle = new LargeBus(counter.getBusIdCounterAndIncrement(),
                      line.shallowCopy());
                  currVehicle.registerObserver(observer);
                  activeVehicles.add(currVehicle);
                  storageFacility.decrementLargeBusesNum();
                  busStrat.incrementDayCycle();
                } else {
                  busStrat.incrementDayCycle();
                }
              } else {
                if (storageFacility.getSmallBusesNum() > 0) {
                  // check if there are small buses left
                  Vehicle currVehicle = new SmallBus(counter.getBusIdCounterAndIncrement(),
                      line.shallowCopy());
                  currVehicle.registerObserver(observer);
                  activeVehicles.add(currVehicle);
                  this.storageFacility.decrementSmallBusesNum();
                  busStrat.incrementDayCycle();
                } else {
                  busStrat.incrementDayCycle();
                }
              }
            } else { // night
              if (busStrat.getNextVehicleType().equals("large")) {
                // check if there are large buses left
                if (storageFacility.getLargeBusesNum() > 0) {
                  Vehicle currVehicle = new LargeBus(counter.getBusIdCounterAndIncrement(),
                      line.shallowCopy());
                  currVehicle.registerObserver(observer);
                  activeVehicles
                      .add(currVehicle);
                  storageFacility.decrementLargeBusesNum();
                  busStrat.incrementNightCycle();
                } else {
                  busStrat.incrementNightCycle();
                }
              } else {
                if (storageFacility.getSmallBusesNum() > 0) {
                  // check if there are small buses left
                  Vehicle currVehicle = new SmallBus(counter.getBusIdCounterAndIncrement(),
                      line.shallowCopy());
                  currVehicle.registerObserver(observer);
                  activeVehicles
                      .add(currVehicle);
                  this.storageFacility.decrementSmallBusesNum();
                  busStrat.incrementNightCycle();
                } else {
                  busStrat.incrementNightCycle();
                }
              }
            }
            timeSinceLastVehicle.set(i, vehicleStartTimings.get(i));
            timeSinceLastVehicle.set(i, timeSinceLastVehicle.get(i) - 1);
          } else if (line.getType().equals(Line.TRAIN_LINE)) { // Feature 3
            // day time
            if (trainStrat.isDay()) {
              if (trainStrat.getNextVehicleType().equals("electric")) {
                // check if there are electric trains left
                if (storageFacility.getElectricTrainsNum() > 0) {
                  Vehicle currVehicle = new ElectricTrain(counter.getTrainIdCounterAndIncrement(),
                      line.shallowCopy());
                  currVehicle.registerObserver(observer);
                  activeVehicles.add(currVehicle);
                  storageFacility.decrementElectricTrainsNum();
                  trainStrat.incrementDayCycle();
                } else {
                  trainStrat.incrementDayCycle();
                }
              } else {
                if (storageFacility.getDieselTrainsNum() > 0) {
                  // check if there are diesel trains left
                  Vehicle currVehicle = new DieselTrain(counter.getTrainIdCounterAndIncrement(),
                      line.shallowCopy());
                  currVehicle.registerObserver(observer);
                  activeVehicles.add(currVehicle);
                  this.storageFacility.decrementDieselTrainsNum();
                  trainStrat.incrementDayCycle();
                } else {
                  trainStrat.incrementDayCycle();
                }
              }
            } else { // night
              if (trainStrat.getNextVehicleType().equals("electric")) {
                // check if there are electric trains left
                if (storageFacility.getElectricTrainsNum() > 0) {
                  Vehicle currVehicle = new ElectricTrain(counter.getTrainIdCounterAndIncrement(),
                      line.shallowCopy());
                  currVehicle.registerObserver(observer);
                  activeVehicles.add(currVehicle);
                  storageFacility.decrementElectricTrainsNum();
                  trainStrat.incrementNightCycle();
                } else {
                  trainStrat.incrementNightCycle();
                }
              } else {
                if (storageFacility.getDieselTrainsNum() > 0) {
                  // check if there are diesel trains left
                  Vehicle currVehicle = new DieselTrain(counter.getTrainIdCounterAndIncrement(),
                      line.shallowCopy());
                  currVehicle.registerObserver(observer);
                  activeVehicles.add(currVehicle);
                  this.storageFacility.decrementDieselTrainsNum();
                  trainStrat.incrementNightCycle();
                } else {
                  trainStrat.incrementNightCycle();
                }
              }
            }
            timeSinceLastVehicle.set(i, vehicleStartTimings.get(i));
            timeSinceLastVehicle.set(i, timeSinceLastVehicle.get(i) - 1);
          }
        } else {
          timeSinceLastVehicle.set(i, timeSinceLastVehicle.get(i) - 1);
        }
      }
      // update vehicles
      for (int i = activeVehicles.size() - 1; i >= 0; i--) {
        Vehicle currVehicle = activeVehicles.get(i);

        //Feature 5
        Line line = currVehicle.getLine();
        int lineId = line.getId();
        if (isUnderIssueLine(lineId)) {
          continue;
        }

        currVehicle.update();
        if (currVehicle.isTripComplete()) {
          currVehicle.removeObserver(observer);
          Vehicle completedTripVehicle = activeVehicles.remove(i);
          completedTripVehicles.add(completedTripVehicle);
          if (completedTripVehicle instanceof LargeBus) {
            this.storageFacility.incrementLargeBusesNum();
          } else if (completedTripVehicle instanceof SmallBus) {
            this.storageFacility.incrementSmallBusesNum();
          } else if (completedTripVehicle instanceof ElectricTrain) {
            this.storageFacility.incrementElectricTrainsNum();
          } else if (completedTripVehicle instanceof DieselTrain) {
            this.storageFacility.incrementDieselTrainsNum();
          }
        } else {
          if (VisualTransitSimulator.LOGGING) {
            currVehicle.report(System.out);
          }
        }
      }
      // update routes
      for (int i = 0; i < lines.size(); i++) {
        Line line = lines.get(i);
        Route currOutboundRoute = line.getOutboundRoute();
        Route currInboundRoute = line.getInboundRoute();
        currOutboundRoute.update();
        currInboundRoute.update();
        if (VisualTransitSimulator.LOGGING) {
          currOutboundRoute.report(System.out);
          currInboundRoute.report(System.out);
        }
      }
    }
  }

  /**
   * Method to find the line of a route.
   *
   * @param route route to search
   * @return Line containing route
   */
  public Line findLineBasedOnRoute(Route route) {
    for (Line line : lines) {
      if (line.getOutboundRoute().getId() == route.getId()
          || line.getInboundRoute().getId() == route.getId()) {
        return line;
      }
    }
    throw new RuntimeException("Could not find the line of the route");
  }

  /**
   * return lines of simulator.
   * @return lines of type list.
   */
  public List<Line> getLines() {
    return lines;
  }

  /**
   * return active vehicles of simulator.
   * @return activeVehicles as list
   */
  public List<Vehicle> getActiveVehicles() {
    return activeVehicles;
  }

  /**
   * return time elapsed during simulation.
   * @return simulationTimeElapsed as int.
   */
  public int getSimulationTimeElapsed() {
    return simulationTimeElapsed;
  }

  /**
   * add line issue to hashtable variable.
   * @param id id object.
   * @param endTime endTime object.
   */
  public void addLineIssue(Integer id, Integer endTime) {
    lineIssues.put(id, endTime);
  }

  /**
   * determines if the line is an issue line.
   * handles overflow of the hashtable.
   * @param lineId lineId object.
   * @return boolean.
   */
  public boolean isUnderIssueLine(int lineId) {
    Integer currTime = getSimulationTimeElapsed();
    if (lineIssues.containsKey(lineId) && lineIssues.get(lineId) > currTime) {
      return true;
    } else if (lineIssues.containsKey(lineId)) {
      //want to get rid of any lineId in hashtable that are past expired time
      lineIssues.remove(lineId);
      return false;
    } else {
      return false;
    }
  }
}
