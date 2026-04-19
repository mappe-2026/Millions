package edu.ntnu.idatt2003.gruppe50.ui.view.navigation;

import edu.ntnu.idatt2003.gruppe50.domain.market.Exchange;
import edu.ntnu.idatt2003.gruppe50.domain.portfolio.Player;
import edu.ntnu.idatt2003.gruppe50.ui.view.pages.DashBoardView;
import edu.ntnu.idatt2003.gruppe50.ui.view.pages.Page;
import edu.ntnu.idatt2003.gruppe50.ui.view.pages.PortfolioView;
import javafx.scene.layout.StackPane;

import java.util.HashMap;
import java.util.Map;

public class NavigationManager {

  private final StackPane contentArea;
  private final Map<String, Page> pages = new HashMap<>();
  private String currentPage;

  public NavigationManager(Player player, Exchange exchange) {
    contentArea = new StackPane();
    contentArea.getStyleClass().add("content-area");

    // register the different pages
    registerPage(new DashBoardView());
    registerPage(new PortfolioView(player));

  }

  private void registerPage(Page page) {
    pages.put(page.getTitle(), page);
  }

  public void navigateTo(String title) {
    if (title.equals(currentPage)) return;
    currentPage = title;

    Page page = pages.get(title);
    if (page != null) {
      contentArea.getChildren().setAll(page.getView());
    }
  }

  public StackPane getContentArea() {
    return contentArea;
  }

}
