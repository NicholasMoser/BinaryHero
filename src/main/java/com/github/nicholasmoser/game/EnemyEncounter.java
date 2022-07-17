package com.github.nicholasmoser.game;

public class EnemyEncounter implements Encounter {

  private final Enemy enemy;
  private State state;

  public EnemyEncounter(Enemy enemy) {
    this.enemy = enemy;
  }

  @Override
  public void setState(State state) {
    this.state = state;
  }
}
