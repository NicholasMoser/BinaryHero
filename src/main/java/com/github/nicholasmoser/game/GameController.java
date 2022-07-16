package com.github.nicholasmoser.game;

import com.github.nicholasmoser.gui.GUIUtils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

public class GameController {

  public FlowPane flow;
  public Label info;
  private Path currDir;

  private Set<Path> completedPaths = new HashSet<>();

  public void init(Path dir) throws IOException {
    flow.setPrefWrapLength(1024);
    cd(dir);
  }

  public void cd(Path dir) throws IOException {
    this.currDir = dir;
    info.setText(dir.toString());
    flow.getChildren().clear();
    flow.getChildren().add(getExit(dir));
    Files.list(dir).forEach(path -> {
      if (Files.isDirectory(path)) {
        if (Files.isReadable(path)) {
          flow.getChildren().add(getDoor(path));
        } else {
          flow.getChildren().add(getLockedDoor(path));
        }
      } else {
        if (Files.isReadable(path)) {
          flow.getChildren().add(getChest(path));
        } else {
          flow.getChildren().add(getLockedDoor(path));
        }
      }
    });

  }

  private Node getExit(Path path) {
    Button button = new Button();
    ImageView view = new ImageView(GUIUtils.getImage("log-out.png"));
    view.setFitHeight(128);
    view.setFitWidth(128);
    button.setGraphic(view);
    button.setOnMouseEntered(e -> info.setText(path.toString()));
    button.setOnMouseExited(e -> info.setText(currDir.toString()));
    Path parent = path.getParent();
    if (parent != null && Files.isReadable(parent)) {
      button.setOnAction(action -> {
        try {
          cd(path.getParent());
        } catch (IOException e) {
          e.printStackTrace();
        }
      });
    } else {
      button.setDisable(true);
    }
    return button;
  }

  private Node getDoor(Path path) {
    Button button = new Button();
    ImageView view = new ImageView(GUIUtils.getImage("dungeon.png"));
    view.setFitHeight(128);
    view.setFitWidth(128);
    button.setGraphic(view);
    button.setOnMouseEntered(e -> info.setText(path.toString()));
    button.setOnMouseExited(e -> info.setText(currDir.toString()));
    button.setOnAction(action -> {
      try {
        cd(path);
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
    return button;
  }

  private Node getLockedDoor(Path path) {
    Button button = new Button();
    ImageView view = new ImageView(GUIUtils.getImage("locked-door.png"));
    view.setFitHeight(128);
    view.setFitWidth(128);
    button.setGraphic(view);
    button.setOnMouseEntered(e -> info.setText(path.toString()));
    button.setOnMouseExited(e -> info.setText(currDir.toString()));
    button.setDisable(true);
    return button;
  }

  private Button getChest(Path path) {
    Button button = new Button();
    ImageView view = new ImageView(GUIUtils.getImage("chest.png"));
    view.setFitHeight(128);
    view.setFitWidth(128);
    button.setGraphic(view);
    button.setOnMouseEntered(e -> info.setText(path.toString()));
    button.setOnMouseExited(e -> info.setText(currDir.toString()));
    if (completedPaths.contains(path)) {
      button.setDisable(true);
    } else {
      button.setOnAction(action -> {
            completedPaths.add(path);
            button.setDisable(true);
          }
      );
    }
    return button;
  }
}
