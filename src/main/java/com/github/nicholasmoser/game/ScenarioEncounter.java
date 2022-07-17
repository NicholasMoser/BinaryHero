package com.github.nicholasmoser.game;

import com.github.nicholasmoser.gui.GUIUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;

public class ScenarioEncounter implements Encounter {

  private final Scenario scenario;
  private final boolean endGame;
  private State state;

  public ScenarioEncounter(Scenario scenario, boolean endGame) {
    this.scenario = scenario;
    this.endGame = endGame;
  }
  
  @Override
  public void setState(State state) {
    this.state = state;
  }

  @Override
  public List<String> getEntryMessages(int roll) {
    int base = endGame ? 4 : 20;
    base += (roll - 3);
    List<String> messages = new ArrayList<>();
    switch(scenario) {
      case NOTHING -> {
        messages.add("Oh, there doesn't appear to be anything here at all...");
        messages.add("Probably best to move on.");
        state.addHealth(base);
      }
      case INJURY -> {
        messages.add("You trip and fall, injuring yourself quite badly.");
        messages.add("You lose " + base + " total health.");
        state.removeTotalHealth(base);
      }
      case TRAINING -> {
        messages.add("You find a good place and decide to do some combat training.");
        messages.add("You gain " + base + " total health.");
        state.addTotalHealth(base);
      }
    }
    return messages;
  }

  @Override
  public List<Node> getActions(ListView<String> events) {
    return Collections.emptyList();
  }

  @Override
  public Image getImage() {
    return GUIUtils.getImage("papyrus.png");
  }
}
