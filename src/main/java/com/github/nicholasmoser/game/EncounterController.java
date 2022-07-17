package com.github.nicholasmoser.game;

import com.github.nicholasmoser.gui.GUIUtils;
import java.util.Random;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class EncounterController {

  public static final String EVENT_FONT_SIZE = "-fx-font-size: 20px;";
  public HBox top;
  public ListView<String> events;
  public HBox actionBar;
  public Label characterStatus;
  public HBox icons;
  private Encounter encounter;
  private State state;

  public void init(State state, Encounter encounter) {
    this.state = state;
    this.encounter = encounter;
    int roll = roll();
    Image rollImage = GUIUtils.getRollImage(roll);
    ImageView view = new ImageView(rollImage);
    view.setFitHeight(64);
    view.setFitWidth(64);
    icons.getChildren().add(view);
    events.getItems().addAll(encounter.getEntryMessages(roll));
    events.setStyle(EVENT_FONT_SIZE);
    actionBar.getChildren().addAll(encounter.getActions(events));
    Button exit = new Button("Exit");
    exit.setFont(new Font(20));
    exit.setOnAction(action -> {
      Stage stage = (Stage) exit.getScene().getWindow();
      stage.close();
    });
    actionBar.getChildren().add(exit);
    exit.requestFocus();
    updateCharacterStatus();
  }

  private void updateCharacterStatus() {
    int currHealth = state.getCurrHealth();
    int totalHealth = state.getTotalHealth();
    int power = state.getPower();
    String status = String.format("Health: %d / %d Power: %d", currHealth, totalHealth, power);
    characterStatus.setText(status);
  }

  private int roll() {
    GUIUtils.playRollSound();
    Random random = new Random();
    return random.nextInt(1, 7);
  }
}
