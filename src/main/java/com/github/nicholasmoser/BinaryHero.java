package com.github.nicholasmoser;

import com.github.nicholasmoser.game.GameController;
import com.github.nicholasmoser.gui.GUIUtils;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * A tool that allows you to modify files in a Naruto GNT ISO file.
 * 
 * @author Nicholas Moser
 */
public class BinaryHero extends Application {

  public static final File USER_HOME = new File(System.getProperty("user.home"));

  private static final Logger LOGGER = Logger.getLogger(BinaryHero.class.getName());

  private static final String FONT_SIZE_CSS = "-fx-font-size: 60px;";

  private Stage stage;

  @Override
  public void start(Stage primaryStage) {
    LOGGER.info("Application has started.");
    this.stage = primaryStage;
    setLoggingProperties();
    createGUI(primaryStage);
  }

  /**
   * Creates the GUI for the application.
   * 
   * @param primaryStage The stage to use.
   */
  private void createGUI(Stage primaryStage) {
    GUIUtils.setIcons(primaryStage);
    GridPane buttonPane = createButtonGrid();
    Scene scene = new Scene(buttonPane);
    GUIUtils.initDarkMode(scene);
    primaryStage.setTitle("Binary Hero");
    primaryStage.setScene(scene);
    primaryStage.centerOnScreen();
    primaryStage.setResizable(false);
    primaryStage.show();
  }

  /**
   * Creates the grid of buttons for the application.
   * 
   * @return The created button grid.
   */
  private GridPane createButtonGrid() {
    Button start = new Button();
    start.setText("Start");
    start.setStyle(FONT_SIZE_CSS);
    start.setTooltip(new Tooltip("Begin the game!"));
    start.setMinSize(800, 400);
    start.setOnAction(event -> {
      Optional<Path> dir = Choosers.getStartingDirectory(USER_HOME);
      if (dir.isEmpty()) {
        return;
      }
      begin(dir.get());
    });

    Button help = new Button();
    help.setText("Help");
    help.setStyle(FONT_SIZE_CSS);
    help.setTooltip(new Tooltip("Instructions and info on the game!"));
    help.setMinSize(800, 400);
    help.setOnAction(event -> help());

    GridPane buttonPane = new GridPane();
    buttonPane.setAlignment(Pos.CENTER);
    buttonPane.setVgap(10);
    buttonPane.setPadding(new Insets(12, 12, 12, 12));
    buttonPane.add(start, 0, 1);
    buttonPane.add(help, 0, 2);

    return buttonPane;
  }

  private void begin(Path path) {
    try {
      FXMLLoader loader = new FXMLLoader(GameController.class.getResource("game.fxml"));
      Scene scene = new Scene(loader.load());
      GUIUtils.initDarkMode(scene);
      GameController controller = loader.getController();
      controller.init(path);
      stage.setScene(scene);
      stage.setTitle(path.toString());
      stage.centerOnScreen();
      stage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void help() {
    try {
      Desktop.getDesktop().browse(new URI("https://github.com/NicholasMoser/BinaryHero#binaryhero"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Sets the custom logging properties from the logging.properties included resource file.
   */
  private void setLoggingProperties() {
    try (InputStream properties = getClass().getResourceAsStream("logging.properties")) {
      LogManager.getLogManager().readConfiguration(properties);
    } catch (SecurityException | IOException e) {
      LOGGER.log(Level.SEVERE, "Unable to load logging.properties", e);
      Message.error("Logging Error", "Unable to load logging.properties");
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
