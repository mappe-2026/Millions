package edu.ntnu.idatt2003.gruppe50;

import edu.ntnu.idatt2003.gruppe50.controller.NewGameController;
import edu.ntnu.idatt2003.gruppe50.domain.market.Exchange;
import edu.ntnu.idatt2003.gruppe50.domain.portfolio.Player;
import edu.ntnu.idatt2003.gruppe50.ui.view.pages.GameView;
import edu.ntnu.idatt2003.gruppe50.ui.view.pages.NewGameView;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application {

  private BorderPane mainLayout;
  private Stage stage;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    this.stage = stage;
    NewGameController controller = new NewGameController(this);
    NewGameView newGame = new NewGameView(stage, controller);
    stage.setScene(newGame.getScene());
    stage.show();
  }

  public void switchToGame(Player player, Exchange exchange) {
    GameView gameView = new GameView(player, exchange);
    stage.setScene(gameView.getScene());
  }
}
