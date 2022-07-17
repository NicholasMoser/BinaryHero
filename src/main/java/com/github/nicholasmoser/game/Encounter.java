package com.github.nicholasmoser.game;

import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;

public interface Encounter {

  void setState(State state);

  List<String> getEntryMessages(int roll);

  List<Node> getActions(ListView<String> events);

  Image getImage();
}
