package com.github.nicholasmoser.game;

import com.github.nicholasmoser.gui.GUIUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;

public class ItemEncounter implements Encounter {

  private final Item item;
  private final boolean endGame;
  private State state;

  public ItemEncounter(Item item, boolean endGame) {
    this.item = item;
    this.endGame = endGame;
  }

  @Override
  public void setState(State state) {
    this.state = state;
  }

  @Override
  public List<String> getEntryMessages(int roll) {
    int base = endGame ? 5 : 50;
    base += ((roll - 3) * 2);
    List<String> messages = new ArrayList<>();
    switch(item) {
      case POTION -> {
        messages.add("You find and drink a potion!");
        messages.add("You heal " + base + " health.");
        state.addHealth(base);
      }
      case POISON -> {
        messages.add("You find and drink poison...");
        messages.add("You lose " + base + " health.");
        state.removeHealth(base);
      }
      case BIGGER_SWORD -> {
        messages.add("You find a bigger sword!");
        messages.add("You gain " + base + " power.");
        state.addPower(base);
      }
      case SWORD_BREAKS -> {
        messages.add("Your sword breaks and now you have to use a smaller sword...");
        messages.add("You lose " + base + " power.");
        state.removePower(base);
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
    if (item == Item.SWORD_BREAKS) {
      return GUIUtils.getImage("nerf.png");
    } else if (item == Item.BIGGER_SWORD) {
      return GUIUtils.getImage("buff.png");
    } else {
      return GUIUtils.getImage("item.png");
    }
  }
}
