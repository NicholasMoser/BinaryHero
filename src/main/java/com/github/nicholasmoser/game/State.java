package com.github.nicholasmoser.game;

import java.nio.file.Path;
import java.util.HashSet;

public class State {

  private final HashSet<Path> completedPaths;
  private Path currentDirectory;
  private int totalHealth;
  private int currHealth;
  private int power;
  private boolean isDead;

  public State(Path currentDirectory) {
    this.totalHealth = 100;
    this.currHealth = 100;
    this.power = 5;
    this.currentDirectory = currentDirectory;
    this.completedPaths = new HashSet<>();
    this.isDead = false;
  }

  public boolean hasCompleted(Path path) {
    return completedPaths.contains(path);
  }

  public boolean addCompleted(Path path) {
    return completedPaths.add(path);
  }

  public void setCurrentDirectory(Path currentDirectory) {
    this.currentDirectory = currentDirectory;
  }

  public Path getCurrentDirectory() {
    return currentDirectory;
  }

  public int getTotalHealth() {
    return totalHealth;
  }

  public void addTotalHealth(int amount) {
    currHealth += amount;
    totalHealth += amount;
  }

  public void removeTotalHealth(int amount) {
    totalHealth -= amount;
    if (currHealth > totalHealth) {
      currHealth = totalHealth;
    }
  }

  public int getCurrHealth() {
    return currHealth;
  }

  public void addHealth(int amount) {
    currHealth += amount;
    if (currHealth > totalHealth) {
      currHealth = totalHealth;
    }
  }

  public void removeHealth(int amount) {
    currHealth -= amount;
    if (currHealth <= 0) {
      isDead = true;
    }
  }

  public boolean isDead() {
    return isDead;
  }

  public int getPower() {
    return power;
  }

  public void addPower(int amount) {
    power += amount;
  }

  public void removePower(int amount) {
    power -= amount;
  }
}
