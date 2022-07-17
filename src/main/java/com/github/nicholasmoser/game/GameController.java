package com.github.nicholasmoser.game;

import com.github.nicholasmoser.KaitaiUtil;
import com.github.nicholasmoser.Message;
import com.github.nicholasmoser.gui.GUIUtils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameController {

  private static final Logger LOGGER = Logger.getLogger(GameController.class.getName());
  public FlowPane flow;
  public Label info;
  public Label characterStatus;
  private State state;

  public void init(Path dir) throws IOException {
    state = new State(dir);
    flow.setPrefWrapLength(1024);
    cd(dir);
  }

  public void cd(Path dir) throws IOException {
    updateCharacterStatus();
    state.setCurrentDirectory(dir);
    info.setText(dir.toString());
    flow.getChildren().clear();
    flow.getChildren().add(getExit(dir));

    if (isLargeDirectory(dir)) {
      // If the directory is large (takes more than 1 second), create a loading window
      Task<Void> task = new Task<>() {
        @Override
        public Void call() throws Exception {
          Files.list(dir).forEach(path -> {
            updateMessage(path.toString());
            handlePath(path);
          });
          return null;
        }
      };
      Stage loading = GUIUtils.createLoadingWindow("Loading Directory", task);
      task.setOnSucceeded(event -> loading.close());
      task.setOnFailed(event -> {
        LOGGER.log(Level.SEVERE, "Failed to Load Directory", task.getException());
        Message.error("Failed to Load Directory", task.getException().getMessage());
        loading.close();
      });
      new Thread(task).start();
    } else {
      // Directory is small, process as-is
      try {
        Files.list(dir).forEach(this::handlePath);
      } catch (Exception e) {
        LOGGER.log(Level.SEVERE, "Failed to Load Directory", e);
        Message.error("Failed to Load Directory", e.getMessage());
      }
    }
  }

  private void updateCharacterStatus() {
    int currHealth = state.getCurrHealth();
    int totalHealth = state.getTotalHealth();
    int power = state.getPower();
    String status = String.format("Health: %d / %d Power: %d", currHealth, totalHealth, power);
    characterStatus.setText(status);
  }

  private void handlePath(Path path) {
    if (Files.isDirectory(path)) {
      if (Files.isReadable(path)) {
        Platform.runLater(() -> flow.getChildren().add(getDoor(path)));
      } else {
        Platform.runLater(() -> flow.getChildren().add(getLockedDoor(path)));
      }
    } else {
      if (Files.isReadable(path)) {
        Platform.runLater(() -> flow.getChildren().add(getFileImage(path)));
      } else {
        Platform.runLater(() -> flow.getChildren().add(getLockedDoor(path)));
      }
    }
  }

  private boolean isLargeDirectory(Path dir) throws IOException {
    return Files.list(dir).count() > 100;
  }

  private Node getExit(Path path) {
    Button button = new Button();
    ImageView view = new ImageView(GUIUtils.getImage("log-out.png"));
    view.setFitHeight(128);
    view.setFitWidth(128);
    button.setGraphic(view);
    button.setOnMouseEntered(e -> info.setText(path.toString()));
    button.setOnMouseExited(e -> info.setText(state.getCurrentDirectory().toString()));
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
    button.setOnMouseExited(e -> info.setText(state.getCurrentDirectory().toString()));
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
    button.setOnMouseExited(e -> info.setText(state.getCurrentDirectory().toString()));
    button.setDisable(true);
    return button;
  }

  private Button getFileImage(Path path) {
    String extension = com.google.common.io.Files.getFileExtension(path.toString()).toLowerCase(
        Locale.ROOT);
    String image;
    switch (extension) {
      case "txt", "log", "md5", "gitconfig", "gradle", "sha512", "gitignore", "properties",
          "settings", "config", "conf", "lua", "go", "c++", "java", "bat", "sh", "ps1", "php",
          "c", "js", "h", "cs", "py", "cpp", "css" -> image = "papyrus.png";
      case "class", "exe", "dll", "sys" -> image = "item.png";
      case "bmp", "gif", "ico", "png", "jpg", "jpeg", "mid", "midi", "smf", "ogg", "avi" -> image = "swords.png";
      default -> image = "question_mark.png";
    }
    Button button = new Button();
    ImageView view = new ImageView(GUIUtils.getImage(image));
    view.setFitHeight(128);
    view.setFitWidth(128);
    button.setGraphic(view);
    button.setOnMouseEntered(e -> info.setText(path.toString()));
    button.setOnMouseExited(e -> info.setText(state.getCurrentDirectory().toString()));
    if (state.hasCompleted(path)) {
      button.setDisable(true);
    } else {
      button.setOnAction(action -> {
            try {
              handleEncounter(path);
            } catch (Exception e) {
              LOGGER.log(Level.SEVERE, "Failed to Read File", e);
              Message.error("Failed to Read File", e.getMessage());
            }
            state.addCompleted(path);
            button.setDisable(true);
          }
      );
    }
    return button;
  }

  private void handleEncounter(Path path) throws IOException {
    Encounter encounter = KaitaiUtil.readFile(path);
    encounter.setState(state);
    Stage stage = new Stage();
    FXMLLoader loader = new FXMLLoader(GameController.class.getResource("encounter.fxml"));
    Scene scene = new Scene(loader.load());
    GUIUtils.initDarkMode(scene);
    EncounterController controller = loader.getController();
    controller.init(state, encounter);
    GUIUtils.setIcons(stage);
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.setScene(scene);
    stage.setTitle("Encounter");
    stage.centerOnScreen();
    stage.show();
  }
}
