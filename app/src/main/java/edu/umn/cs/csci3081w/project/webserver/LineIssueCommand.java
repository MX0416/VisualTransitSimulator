package edu.umn.cs.csci3081w.project.webserver;

import com.google.gson.JsonObject;
import edu.umn.cs.csci3081w.project.model.Line;
import java.time.LocalTime; //import LocalTimeClass


/**
 * LineIssueCommand extends SimulatorCommand.
 */
public class LineIssueCommand extends SimulatorCommand {
  private VisualTransitSimulator visSim;

  /**
   * constructor for LinesIssueCommand.
   * @param visSim visSim of type VisualTransitSimulator.
   */
  public LineIssueCommand(VisualTransitSimulator visSim) {
    this.visSim = visSim;
  }

  /**
   * execute the web session by adding lines issue using command.
   * @param session session of type WebServerSession.
   * @param command command of type JsonObject.
   */
  @Override
  public void execute(WebServerSession session, JsonObject command) {
    int currentTime = visSim.getSimulationTimeElapsed();
    int endTime = currentTime + 10;
    Integer issueLineID = command.get("id").getAsInt();

    visSim.addLineIssue(issueLineID, endTime);

  }

}
