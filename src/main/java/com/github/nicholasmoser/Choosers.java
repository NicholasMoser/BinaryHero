package com.github.nicholasmoser;

import java.io.File;
import java.nio.file.Path;
import java.util.Optional;
import javafx.stage.DirectoryChooser;

public class Choosers {

  /**
   * Asks the user to select an input directory.
   *
   * @param initialDirectory The location to set the directory chooser to start at.
   * @return An optional input directory. Empty if none is chosen.
   */
  public static Optional<Path> getStartingDirectory(File initialDirectory) {
    DirectoryChooser directoryChooser = new DirectoryChooser();
    directoryChooser.setTitle("Select Starting Directory");
    directoryChooser.setInitialDirectory(initialDirectory);
    File selection = directoryChooser.showDialog(null);
    return selection != null ? Optional.of(selection.toPath()) : Optional.empty();
  }
}
