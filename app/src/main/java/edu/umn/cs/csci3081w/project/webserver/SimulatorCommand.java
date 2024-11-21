package edu.umn.cs.csci3081w.project.webserver;

import com.google.gson.JsonObject;

/**
 * abstract class SimulatorCommand.
 * class handles multiple types of Command classes.
 */
public abstract class SimulatorCommand {

  /**
   * constructor for SimulatorCommand.
   */
  public SimulatorCommand() {
  }

  /**
   * abstract method execute.
   *
   * @param session session of type WebServerSession.
   * @param command command of type JsonObject.
   */
  public abstract void execute(WebServerSession session, JsonObject command);
}
