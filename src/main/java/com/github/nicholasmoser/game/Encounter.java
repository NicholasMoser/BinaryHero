package com.github.nicholasmoser.game;

import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.ListView;

public interface Encounter {

  void setState(State state);

  List<String> getEntryMessages();

  List<Node> getActions(ListView<String> events);
}
