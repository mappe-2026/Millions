package edu.ntnu.idatt2003.gruppe50.ui.view.pages;

import edu.ntnu.idatt2003.gruppe50.ui.controller.GameController;
import edu.ntnu.idatt2003.gruppe50.ui.controller.PortfolioQueryController;
import edu.ntnu.idatt2003.gruppe50.ui.view.components.NavBar;
import edu.ntnu.idatt2003.gruppe50.ui.view.navigation.NavigationManager;
import edu.ntnu.idatt2003.gruppe50.ui.view.navigation.PageId;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public class GameView{
  private final GameController gameController;
  private final PortfolioQueryController portfolioQueryController;

  public GameView(GameController gameController, PortfolioQueryController portfolioQueryController) {
    this.gameController = gameController;
    this.portfolioQueryController = portfolioQueryController;
  }

  public Scene getScene() {
    Map<PageId, Supplier<Page>> factories = new EnumMap<>(PageId.class);

    factories.put(PageId.DASHBOARD, DashBoardView::new);
    factories.put(PageId.PORTFOLIO, () -> new PortfolioView(portfolioQueryController, gameController));
    // need factories for market and transactions too

    NavigationManager navManager = new NavigationManager(factories);
    NavBar navBar = new NavBar(navManager::navigateTo);

    BorderPane root = new BorderPane();
    root.setTop(navBar);
    root.setCenter(navManager.getContentArea());

    navManager.navigateTo(PageId.DASHBOARD);

    return new Scene(root, 600, 400);
  }
}
