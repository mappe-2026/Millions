package edu.ntnu.idatt2003.gruppe50.ui.view.pages;

import edu.ntnu.idatt2003.gruppe50.domain.market.Exchange;
import edu.ntnu.idatt2003.gruppe50.domain.portfolio.Player;
import edu.ntnu.idatt2003.gruppe50.ui.view.components.NavBar;
import edu.ntnu.idatt2003.gruppe50.ui.view.navigation.NavigationManager;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class GameView{
  private final Player player;
  private final Exchange exchange;

  public GameView(Player player, Exchange exchange) {
    this.player = player;
    this.exchange = exchange;
  }

  public Scene getScene() {
    NavigationManager navManager = new NavigationManager(player, exchange);
    NavBar navBar = new NavBar(navManager::navigateTo);

    BorderPane root = new BorderPane();
    root.setTop(navBar);
    root.setCenter(navManager.getContentArea());

    navManager.navigateTo("Dashboard");

    return new Scene(root, 600, 400);
  }
}
