package com.github.nicholasmoser.game;

import com.github.nicholasmoser.gui.GUIUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;

public class EnemyEncounter implements Encounter {

  private final Enemy enemy;
  private final boolean endGame;
  private State state;

  public EnemyEncounter(Enemy enemy, boolean endGame) {
    this.enemy = enemy;
    this.endGame = endGame;
  }

  @Override
  public void setState(State state) {
    this.state = state;
  }

  @Override
  public List<String> getEntryMessages(int roll) {
    List<String> messages = new ArrayList<>();
    switch(enemy) {
      case DRAGON -> {
        int enemyPower = endGame ? 50 : 250;
        int gold = enemyPower + ((roll - 3) * 2);
        messages.add("You come across a monstrous looking dragon.");
        messages.addAll(fight(enemyPower, gold, roll));
      }
      case KNIGHT -> {
        int enemyPower = endGame ? 25 : 125;
        int gold = enemyPower + ((roll - 3) * 2);
        messages.add("You come across a strong looking knight.");
        messages.addAll(fight(enemyPower, gold, roll));
      }
      case SKELETON -> {
        int enemyPower = endGame ? 15 : 60;
        int gold = enemyPower + ((roll - 3) * 2);
        messages.add("You come across a scary looking skeleton.");
        messages.addAll(fight(enemyPower, gold, roll));
      }
      case SLIME -> {
        int enemyPower = endGame ? 5 : 25;
        int gold = enemyPower + roll - 3;
        messages.add("You come across a weak looking slime.");
        messages.addAll(fight(enemyPower, gold, roll));
      }
    }
    return messages;
  }

  private List<String> fight(int enemyPower, int gold, int roll) {
    boolean success = false;
    int playerPower = state.getPower();
    List<String> messages = new ArrayList<>();
    if (roll == 6) {
      // Crit success
      messages.add("You lift your weapon and take on good whack at it, knocking it out in one hit!");
      success = true;
    } else if (roll == 1) {
      // Crit fail
      if (playerPower > enemyPower) {
        // Powerful enough to resist the blow
        int damage = enemyPower / 2;
        messages.add("Despite your best efforts, the enemy is too strong and you have to escape.");
        messages.add(String.format("You take %d damage.", damage));
        state.removeHealth(damage);
      } else {
        // It's gonna hurt
        int damage = enemyPower;
        messages.add("The enemy overpowers you. You are forced to escape bloodied.");
        messages.add(String.format("You take %d damage.", damage));
        state.removeHealth(damage);
      }
    } else {
      messages.add("You and the enemy trade blows.");
      // Calculate normal damage
      // Possible rolls at this point in code are 2, 3, 4, and 5
      // 2 -> 1/5 enemy power
      // 3 -> 2/5 enemy power
      // 4 -> 3/5 enemy power
      // 5 -> 4/5 enemy power
      int damage = enemyPower - (((roll - 1) / 5) * enemyPower);
      if (playerPower > enemyPower) {
        damage = damage / 2;
      }
      messages.add(String.format("The enemy does %d damage to you.", damage));
      state.removeHealth(damage);
      success = true;
    }
    if (state.isDead()) {
      messages.add("You fall to the ground defeated. You are dead!");
    } else if (success) {
      int exp = enemyPower / 3;
      messages.add(String.format("You defeat the enemy and gain %d gold and %d power!", gold, exp));
      state.addGold(gold);
      state.addPower(exp);
    }
    return messages;
  }

  @Override
  public List<Node> getActions(ListView<String> events) {
    return Collections.emptyList();
  }

  @Override
  public Image getImage() {
    if (enemy == Enemy.DRAGON) {
      return GUIUtils.getImage("final-boss.png");
    } else if (enemy == Enemy.KNIGHT) {
      return GUIUtils.getImage("knight.png");
    } else if (enemy == Enemy.SKELETON) {
      return GUIUtils.getImage("skeleton.png");
    } else {
      return GUIUtils.getImage("slime.png");
    }
  }
}
