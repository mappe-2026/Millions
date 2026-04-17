package edu.ntnu.idatt2003.gruppe50.view.pages;

import edu.ntnu.idatt2003.gruppe50.view.components.NavBar;
import edu.ntnu.idatt2003.gruppe50.view.navigation.NavigationManager;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class GameView{

  public Scene getScene() {
    NavigationManager navManager = new NavigationManager();
    NavBar navBar = new NavBar(navManager::navigateTo);

    BorderPane root = new BorderPane();
    root.setTop(navBar);
    root.setCenter(navManager.getContentArea());

    navManager.navigateTo("Dashboard");

    return new Scene(root, 600, 400);
  }
}
