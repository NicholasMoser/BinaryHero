package com.github.nicholasmoser.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.ListView;

public class ItemEncounter implements Encounter {

  private final Item item;
  private final int amount;
  private State state;

  public ItemEncounter(Item item, int amount) {
    this.item = item;
    this.amount = amount;
  }

  @Override
  public void setState(State state) {
    this.state = state;
  }

  @Override
  public List<String> getEntryMessages() {
    List<String> messages = new ArrayList<>();
    switch(item) {
      case POTION -> {
        messages.add("You find and drink a potion!");
        messages.add("You heal " + amount + " health.");
        state.addHealth(amount);
      }
      case POISON -> {
        messages.add("You find and drink poison...");
        messages.add("You lose " + amount + " health.");
        state.removeHealth(amount);
      }
      case BIGGER_SWORD -> {
        messages.add("You find a bigger sword!");
        messages.add("You gain " + amount + " power.");
        state.addPower(amount);
      }
      case SWORD_BREAKS -> {
        messages.add("Your sword breaks and now you have to use a smaller sword...");
        messages.add("You lose " + amount + " power.");
        state.removePower(amount);
      }
    }
    return messages;
  }

  @Override
  public List<Node> getActions(ListView<String> events) {
    return Collections.emptyList();
  }
}
