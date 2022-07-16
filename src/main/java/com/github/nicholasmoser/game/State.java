package com.github.nicholasmoser.game;

import java.nio.file.Path;
import java.util.HashSet;

public class State {

  private Path currentDirectory;
  private final HashSet<Path> completedPaths;
  private int health;

  public State(Path currentDirectory) {
    this.health = 100;
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
