package edu.ntnu.idatt2003.gruppe50.ui.view.components;

import edu.ntnu.idatt2003.gruppe50.ui.view.navigation.PageId;
import javafx.css.PseudoClass;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class NavBar extends HBox {
  private static final PseudoClass ACTIVE = PseudoClass.getPseudoClass("active-nav");
  private static final PageId[] NAV_ITEMS = {PageId.DASHBOARD, PageId.MARKET, PageId.PORTFOLIO, PageId.TRANSACTIONS};

  /**
   * Inner interface to listen for nav-bar clicks.
   */
  public interface NavListener {
    void onNavSelectedItem(PageId item);
  }

  // I don't know how the wireframe looks yet but this is a good starter layout.
  public NavBar(NavListener listener) {
    this.getStyleClass().add("navbar");

    Logo logo = new Logo();

    Region spacer = new Region();
    HBox.setHgrow(spacer, Priority.ALWAYS);

    HBox navLinks = new HBox(4);
    navLinks.getStyleClass().add("nav-links");

    for (PageId item : NAV_ITEMS) {
      Button btn = createNavButton(item, navLinks, listener);
      navLinks.getChildren().add(btn);
    }

    this.getChildren().addAll(logo, spacer, navLinks);
  }

  private Button createNavButton(PageId pageId, HBox navLinks, NavListener listener) {
    Button btn = new Button(pageId.getLabel());
    btn.getStyleClass().add("nav-button");

    btn.setOnAction(e -> {
      // Clear active state from all nav-buttons
      navLinks.getChildren().forEach(node -> {
        if (node instanceof Button b) {
          b.pseudoClassStateChanged(ACTIVE, false);
        }
      });
      // Mark the clicked button as active
      btn.pseudoClassStateChanged(ACTIVE, true);
      // Notify the nav manager
      listener.onNavSelectedItem(pageId);
    });

    return btn;
  }
}
