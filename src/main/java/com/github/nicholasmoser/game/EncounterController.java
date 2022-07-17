package com.github.nicholasmoser.game;

import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

public class EncounterController {

  public HBox top;
  public ListView events;
  public HBox actionBar;
  private Encounter encounter;
  private State state;

  public void init(State state, Encounter encounter) {
    this.state = state;
    this.encounter = encounter;
  }
}
