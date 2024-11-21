package edu.umn.cs.csci3081w.project.webserver;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import edu.umn.cs.csci3081w.project.model.Bus;
import edu.umn.cs.csci3081w.project.model.Train;
import edu.umn.cs.csci3081w.project.model.Vehicle;
import java.util.List;

/**
 * RegisterVehicleCommand extends SimulatorCommand.
 */
public class RegisterVehicleCommand extends SimulatorCommand {
  private VisualTransitSimulator simulator;

  /**
   * constructor RegisterVehicleCommand.
   * @param simulator simulator of type VisualTransitSimulator.
   */
  public RegisterVehicleCommand(VisualTransitSimulator simulator) {
    this.simulator = simulator;
  }


  /**
   * execute web session using command.
   * @param session session of type WebServerSession.
   * @param command command of type JsonObject.
   */
  @Override
  public void execute(WebServerSession session, JsonObject command) {
    JsonObject data = new JsonObject();
    data.addProperty("command", "observedVehicle");

    JsonElement vehicleJson = command.get("id");
    int vehicleId = vehicleJson.getAsInt();


    Vehicle currVehicle = null;
    List<Vehicle> vehicles = simulator.getActiveVehicles();
    for (Vehicle v : vehicles) {
      if (v.getId() == vehicleId) {
        currVehicle = v;
        break;
      }
    }

    JsonArray c02Array = new JsonArray(5);
    List<Integer> prevC02 = currVehicle.getPrevCO2();
    for (Integer c02 : prevC02) {
      c02Array.add(c02);
    }

    String vehicleType = "";
    if (currVehicle instanceof Bus) {
      vehicleType = "BUS";
    } else if (currVehicle instanceof Train) {
      vehicleType = "TRAIN";
    }

    data.addProperty("text", "Vehicle  " + vehicleId + "\n"
        + "-----------------------------" + "\n" + "* Type: " + vehicleType + "\n"
        + "* Position: (" + currVehicle.getPosition().getLongitude() + ","
        + currVehicle.getPosition().getLatitude()
        + ")\n" + "* Passengers: " + currVehicle.getPassengers().size() + "\n" + "* C02: "
        + c02Array);

    session.sendJson(data);
  }

}
