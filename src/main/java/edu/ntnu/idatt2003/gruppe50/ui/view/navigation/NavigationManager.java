package edu.ntnu.idatt2003.gruppe50.ui.view.navigation;

import edu.ntnu.idatt2003.gruppe50.ui.view.pages.Page;
import javafx.scene.layout.StackPane;

import java.util.*;
import java.util.function.Supplier;

public final class NavigationManager {

  private final StackPane contentArea = new StackPane();
  private final Map<PageId, Supplier<Page>> pageFactories;
  private final Map<PageId, Page> pages = new EnumMap<>(PageId.class);

  public NavigationManager(Map<PageId, Supplier<Page>> pageFactories) {
    this.pageFactories = pageFactories;
  }

  public void navigateTo(PageId id) {
    Page page = pages.computeIfAbsent(id, k -> pageFactories.get(k).get());
    if (page != null) {
      contentArea.getChildren().setAll(page.getView());
    }
  }

  public StackPane getContentArea() {
    return contentArea;
  }

}
