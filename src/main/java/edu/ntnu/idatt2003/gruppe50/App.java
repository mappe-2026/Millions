package edu.ntnu.idatt2003.gruppe50;

import edu.ntnu.idatt2003.gruppe50.application.GameSessionNotFoundException;
import edu.ntnu.idatt2003.gruppe50.application.LoadGameSessionUseCase;
import edu.ntnu.idatt2003.gruppe50.application.StartGameSessionUseCase;
import edu.ntnu.idatt2003.gruppe50.domain.game.GameSession;
import edu.ntnu.idatt2003.gruppe50.domain.repository.GameSessionRepository;
import edu.ntnu.idatt2003.gruppe50.infrastructure.repository.InMemoryGameSessionRepository;
import edu.ntnu.idatt2003.gruppe50.ui.controller.NewGameController;
import edu.ntnu.idatt2003.gruppe50.ui.view.pages.GameView;
import edu.ntnu.idatt2003.gruppe50.ui.view.pages.NewGameView;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.UUID;

/**
 * Hello world!
 *
 */
public class App extends Application {

  private BorderPane mainLayout;
  private Stage stage;
  private final GameSessionRepository sessions = new InMemoryGameSessionRepository();
  private final StartGameSessionUseCase startGameSession = new StartGameSessionUseCase(sessions);
  private final LoadGameSessionUseCase loadGameSession = new LoadGameSessionUseCase(sessions);

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    this.stage = stage;
    NewGameController controller = new NewGameController(startGameSession, this::switchToGame);
    NewGameView newGame = new NewGameView(stage, controller);
    stage.setScene(newGame.getScene());
    stage.show();
  }

  public void switchToGame(UUID gameId) {
    loadGameSession.execute(new LoadGameSessionUseCase.Request(gameId));
    GameSession session = sessions.findById(gameId).orElseThrow(GameSessionNotFoundException::new);

    GameView gameView = new GameView(session.getPlayer(), session.getExchange());
    stage.setScene(gameView.getScene());
  }
}
