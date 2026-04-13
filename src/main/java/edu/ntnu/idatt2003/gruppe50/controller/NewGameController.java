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

public class NewGameController {

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
