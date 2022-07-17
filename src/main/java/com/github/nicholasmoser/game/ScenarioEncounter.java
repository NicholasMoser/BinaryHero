package com.github.nicholasmoser.game;

import java.util.Collections;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;

public class ScenarioEncounter implements Encounter {

  private final Scenario scenario;
  private State state;

  public ScenarioEncounter(Scenario scenario) {
    this.scenario = scenario;
  }
  
  @Override
  public void setState(State state) {
    this.state = state;
  }

  @Override
  public List<String> getEntryMessages(int roll) {
    return null;
  }

  @Override
  public List<Node> getActions(ListView<String> events) {
    return Collections.emptyList();
  }

  @Override
  public Image getImage() {
    return null;
  }
}
