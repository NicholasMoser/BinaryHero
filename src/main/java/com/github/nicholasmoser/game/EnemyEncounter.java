package com.github.nicholasmoser.game;

import com.github.nicholasmoser.gui.GUIUtils;
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
    return null;
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
