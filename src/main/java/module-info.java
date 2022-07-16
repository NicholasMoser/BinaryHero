module com.github.nicholasmoser {
  exports com.github.nicholasmoser;

  opens com.github.nicholasmoser to javafx.fxml;
  opens com.github.nicholasmoser.game to javafx.fxml;

  requires com.google.common;

  requires java.desktop;
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.graphics;
  requires java.logging;
}
