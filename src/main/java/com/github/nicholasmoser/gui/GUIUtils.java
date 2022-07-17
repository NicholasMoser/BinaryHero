package com.github.nicholasmoser.gui;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * GUI utilities.
 */
public class GUIUtils {
  private static final Logger LOGGER = Logger.getLogger(GUIUtils.class.getName());

  public static final String FONT_SIZE_CSS = "-fx-font-size: 26px;";

  public static final String BORDER = "-fx-effect: innershadow(gaussian, #039ed3, 2, 1.0, 0, 0);";

  private static final Path DARK_MODE_DISABLED = Paths.get("DARK_MODE_DISABLED");

  private static final Map<String, Image> imageCache = new HashMap<>();

  public static void playRollSound() {
    URL url = GUIUtils.class.getResource("boardgamepack/Bonus/dieThrow1.wav");
    Media sound = new Media(url.toString());
    MediaPlayer mediaPlayer = new MediaPlayer(sound);
    mediaPlayer.play();
  }

  public static Image getImage(String path) {
    Image image = imageCache.get(path);
    if (image == null) {
      image = new Image(GUIUtils.class.getResourceAsStream(path));
      imageCache.put(path, image);
    }
    return image;
  }

  public static Image getRollImage(int roll) {
    return getImage(String.format("boardgamepack/PNG/Dice/dieWhite%d.png", roll));
  }

  /**
   * Creates a new loading window for a specified task with the default window size.
   * 
   * @param title The title of the window.
   * @param task The task to perform.
   * @return The loading window.
   */
  public static Stage createLoadingWindow(String title, Task<?> task) {
    return createLoadingWindow(title, task, 450, 200);
  }

  /**
   * Creates a new loading window for a specified task with the given size.
   *
   * @param title The title of the window.
   * @param task The task to perform.
   * @param width The width of the window.
   * @param height The height of the window.
   * @return The loading window.
   */
  public static Stage createLoadingWindow(String title, Task<?> task, double width, double height) {
    Stage loadingWindow = new Stage();
    loadingWindow.initModality(Modality.APPLICATION_MODAL);
    loadingWindow.initStyle(StageStyle.UNDECORATED);
    loadingWindow.setTitle(title);
    GUIUtils.setIcons(loadingWindow);

    GridPane flow = new GridPane();
    flow.setAlignment(Pos.CENTER);
    flow.setVgap(20);

    Text text = new Text();
    text.setStyle(FONT_SIZE_CSS);

    ProgressIndicator progressIndicator = new ProgressIndicator(-1.0f);

    GridPane.setHalignment(text, HPos.CENTER);
    GridPane.setHalignment(progressIndicator, HPos.CENTER);
    flow.add(text, 0, 0);
    flow.add(progressIndicator, 0, 1);
    flow.setStyle(BORDER);

    Scene dialogScene = new Scene(flow, width, height);
    loadingWindow.setScene(dialogScene);
    loadingWindow.show();

    progressIndicator.progressProperty().bind(task.progressProperty());
    text.textProperty().bind(task.messageProperty());

    return loadingWindow;
  }

  /**
   * Creates a new loading window with the given size.
   *
   * @param title The title of the window.
   * @param width The width of the window.
   * @param height The height of the window.
   * @return The loading window.
   */
  public static Stage createLoadingWindow(String title, double width, double height) {
    Stage loadingWindow = new Stage();
    loadingWindow.initModality(Modality.APPLICATION_MODAL);
    loadingWindow.initStyle(StageStyle.UNDECORATED);
    loadingWindow.setTitle(title);
    GUIUtils.setIcons(loadingWindow);

    GridPane flow = new GridPane();
    flow.setAlignment(Pos.CENTER);
    flow.setVgap(20);

    Text text = new Text();
    text.setStyle(FONT_SIZE_CSS);

    ProgressIndicator progressIndicator = new ProgressIndicator(-1.0f);

    GridPane.setHalignment(text, HPos.CENTER);
    GridPane.setHalignment(progressIndicator, HPos.CENTER);
    flow.add(text, 0, 0);
    flow.add(progressIndicator, 0, 1);
    flow.setStyle(BORDER);

    Scene dialogScene = new Scene(flow, width, height);
    loadingWindow.setScene(dialogScene);
    loadingWindow.show();

    return loadingWindow;
  }

  /**
   * Sets the application icons on the stage.
   * 
   * @param primaryStage The primary stage to set the icons for.
   */
  public static void setIcons(Stage primaryStage) {
    ObservableList<Image> icons = primaryStage.getIcons();
    icons.add(getImage("boardgamepack/PNG/Pieces (Red)/pieceRed_single02.png"));
  }

  /**
   * Sets the application icons on the alert.
   *
   * @param alert The alert to set the icons for.
   */
  public static void setIcons(Alert alert) {
    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
    ObservableList<Image> icons = stage.getIcons();
    icons.add(getImage("boardgamepack/PNG/Pieces (Red)/pieceRed_single02.png"));
  }

  public static void initDarkMode(Scene scene) {
    if (!Files.exists(DARK_MODE_DISABLED)) {
      toggleDarkMode(scene);
    }
  }

  /**
   * Sets dark theme on the scene.
   *
   * @param scene The scene to make dark themed.
   */
  public static void toggleDarkMode(Scene scene) {
    List<String> stylesheets = scene.getStylesheets();
    if (stylesheets.isEmpty()) {
      scene.getStylesheets().add(GUIUtils.class.getResource("stylesheet.css").toExternalForm());
      try {
        Files.deleteIfExists(DARK_MODE_DISABLED);
      } catch (IOException e) {
        LOGGER.log(Level.SEVERE, "Failed to delete dark mode config file", e);
      }
    } else {
      stylesheets.clear();
      if (!Files.exists(DARK_MODE_DISABLED)) {
        try {
          Files.createFile(DARK_MODE_DISABLED);
        } catch (IOException e) {
          LOGGER.log(Level.SEVERE, "Failed to create dark mode config file", e);
        }
      }
    }
  }

  /**
   * @return If the platform this is running on is Windows.
   */
  public static boolean isWindows() {
    return System.getProperty("os.name").startsWith("Windows");
  }
}
