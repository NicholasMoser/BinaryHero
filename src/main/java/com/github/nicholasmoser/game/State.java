package com.github.nicholasmoser.game;

import java.nio.file.Path;
import java.util.HashSet;

public class State {

  private final HashSet<Path> completedPaths;
  private Path currentDirectory;
  private int health;
  private int power;

  public State(Path currentDirectory) {
    this.health = 100;
    this.power = 100;
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
}
