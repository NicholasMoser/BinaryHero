package com.github.nicholasmoser.game;

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
}
