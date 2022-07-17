package com.github.nicholasmoser.game;

public class ItemEncounter implements Encounter {

  private final Item item;
  private State state;

  public ItemEncounter(Item item) {
    this.item = item;
  }

  @Override
  public void setState(State state) {
    this.state = state;
  }
}
