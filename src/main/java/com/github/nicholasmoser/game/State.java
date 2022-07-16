package com.github.nicholasmoser.game;

import java.nio.file.Path;
import java.util.HashSet;

public class State {

  private final HashSet<Path> completedPaths;
  private Path currentDirectory;
  private int totalHealth;
  private int currHealth;
  private int power;

  public State(Path currentDirectory) {
    this.totalHealth = 100;
    this.currHealth = 100;
    this.power = 5;
    this.currentDirectory = currentDirectory;
    this.completedPaths = new HashSet<>();
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

  public int addTotalHealth(int amount) {
    return totalHealth += amount;
  }

  public int removeTotalHealth(int amount) {
    return totalHealth -= amount;
  }

  public int getCurrHealth() {
    return currHealth;
  }

  public int addHealth(int amount) {
    return currHealth += amount;
  }

  public int removeHealth(int amount) {
    return currHealth -= amount;
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
