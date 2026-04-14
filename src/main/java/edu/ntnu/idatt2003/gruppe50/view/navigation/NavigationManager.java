package edu.ntnu.idatt2003.gruppe50.view.navigation;

import edu.ntnu.idatt2003.gruppe50.view.pages.Page;
import javafx.scene.layout.StackPane;

import java.util.HashMap;
import java.util.Map;

public class NavigationManager {

  private final StackPane contentArea;
  private final Map<String, Page> pages = new HashMap<>();
  private String currentPage;

  public NavigationManager() {
    contentArea = new StackPane();
    contentArea.getStyleClass().add("content-area");

    // register the different pages

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
