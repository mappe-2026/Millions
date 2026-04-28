package edu.ntnu.idatt2003.gruppe50.ui.controller;

import edu.ntnu.idatt2003.gruppe50.domain.market.Exchange;
import edu.ntnu.idatt2003.gruppe50.domain.portfolio.Player;
import edu.ntnu.idatt2003.gruppe50.domain.market.Stock;

import java.util.ArrayList;
import java.util.List;

public class MarketController {
  private final Exchange exchange;
  private final Player player;

  public MarketController(Exchange exchange, Player player) {
    this.exchange = exchange;
    this.player = player;
  }

  public List<Stock> onSearch(String query) {
    return exchange.findStocks(query);
  }

  public List<Stock> getStocks() {
    return new ArrayList<>(exchange.getStocks());
  }
}
