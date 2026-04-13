package edu.ntnu.idatt2003.gruppe50.controller;

import edu.ntnu.idatt2003.gruppe50.io.CSVFileHandler;
import edu.ntnu.idatt2003.gruppe50.model.Exchange;
import edu.ntnu.idatt2003.gruppe50.model.Player;
import edu.ntnu.idatt2003.gruppe50.model.Stock;
import edu.ntnu.idatt2003.gruppe50.transaction.TransactionFactory;
import edu.ntnu.idatt2003.gruppe50.util.Parse;
import edu.ntnu.idatt2003.gruppe50.util.Validate;
import java.io.File;
import java.math.BigDecimal;
import java.util.List;

/**
 * Controller responsible for initializing a new game session.
 * Parses and validates user input before creating the necessary
 * game objects ({@link Player} and {@link Exchange}).
 */
public class NewGameController {

  /**
   * Initializes and starts a new game with the provided input.
   * Parses the capital string, validates all input, loads stocks
   * from file, and creates the player and exchange.
   *
   * @param playerName the name of the player
   * @param capital the starting capital as a string, e.g. "10000" or "10000kr"
   * @param stockFile the CSV file containing stock data
   * @throws IllegalArgumentException if any input is invalid
   */
  public void onStartGame(String playerName, String capital, File stockFile) {
    BigDecimal startingCapital = Parse.parseBigDecimal(capital);
    Validate.validateInput(playerName, startingCapital, stockFile);
    List<Stock> stocks = loadStocks(stockFile);
    Player player = createPlayer(playerName, startingCapital);
    Exchange exchange = createExchange(stocks);
  }

  private List<Stock> loadStocks(File stockFile) {
    return CSVFileHandler.readLines(stockFile.toPath());
  }

  private Player createPlayer(String name, BigDecimal startingCapital) {
    return new Player(name, startingCapital);
  }

  private Exchange createExchange(List<Stock> stocks) {
    return new Exchange("Stock exchange", stocks, new TransactionFactory());
  }
}
