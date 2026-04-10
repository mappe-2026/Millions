package edu.ntnu.idatt2003.gruppe50.view;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class NavigationManager {
  private static final NavigationManager instance = new NavigationManager();
  private StackPane root;

  private NavigationManager() {}

  public static NavigationManager getInstance() {
    return instance;
  }

  public void setRoot(StackPane root) {
    this.root = root;
  }

  public void navigate(String route) {
    try {
      Node view = switch (route) {
        case "dashboard" -> new DashboardView().build();
        case "market" -> new MarketView().build();
        case "profile" -> new ProfileView().build();
        default -> throw new IllegalArgumentException("Unknown route: " + route);
      };

      root.getChildren().setAll(view);

    } catch (Exception e) {
      throw new RuntimeException("Navigation failed for: " + route, e);
    }
  }
}
