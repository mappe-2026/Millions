package edu.ntnu.idatt2003.gruppe50;

import edu.ntnu.idatt2003.gruppe50.controller.NewGameController;
import edu.ntnu.idatt2003.gruppe50.view.pages.NewGameView;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application {

  private BorderPane mainLayout;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    NewGameController controller = new NewGameController();
    NewGameView newGame = new NewGameView(stage, controller);
    stage.setScene(newGame.getScene());
    stage.show();
  }
}
