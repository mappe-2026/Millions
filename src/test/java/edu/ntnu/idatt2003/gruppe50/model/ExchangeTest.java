package edu.ntnu.idatt2003.gruppe50.model;

import edu.ntnu.idatt2003.gruppe50.transaction.Purchase;
import edu.ntnu.idatt2003.gruppe50.transaction.Sale;
import edu.ntnu.idatt2003.gruppe50.transaction.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ExchangeTest {
  private Stock stock;
  private Exchange exchange;
  private Player player;

  @BeforeEach
  void setup() {
    stock = new Stock("AAPL", "Apple", bd(10));
    exchange = new Exchange("test", List.of(stock));
    player = new Player("test", bd(1000));
  }

  @Test
  void constructor_nullName_throwsException() {
    assertThrows(IllegalArgumentException.class, () -> new Exchange(null, List.of(stock)));
  }

  @Test
  void constructor_blankName_throwsException() {
     assertThrows(IllegalArgumentException.class, () -> new Exchange("", List.of(stock)));
  }

  @Test
  void constructor_nullStocks_throwsException() {
     assertThrows(IllegalArgumentException.class, () -> new Exchange("Test", null));
  }

  @Test
  void constructor_emptyList_throwsException() {
     assertThrows(IllegalArgumentException.class, () -> new Exchange("Test", new ArrayList<>()));
  }

  @Test
  void getName_returnsName() {
     assertEquals("test", exchange.getName());
  }

  @Test
  void getWeek_returnsWeek() {
    assertEquals(1, exchange.getWeek());
  }

  @Test
  void hasStock_returnsTrue() {
    assertTrue(exchange.hasStock("AAPL"));
  }

  @Test
  void hasStock_returnsFalse() {
    assertFalse(exchange.hasStock("KOG"));
  }

  @Test
  void hasStock_blankSymbol_throwsException() {
    assertThrows(IllegalArgumentException.class, () -> exchange.hasStock(""));
  }

  @Test
  void hasStock_nullSymbol_throwsException() {
    assertThrows(IllegalArgumentException.class, () -> exchange.hasStock(null));
  }

  @Test
  void getStock_returnsStock() {
    assertEquals(stock, exchange.getStock("AAPL"));
  }

  @Test
  void getStock_wrongSymbol_throwsException() {
    assertThrows(NoSuchElementException.class, () -> exchange.getStock("KOG"));
  }

  @Test
  void getStock_nullSymbol_throwsHasStockException() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> exchange.getStock(null));
    assertEquals("Symbol cannot be null or blank", exception.getMessage());
  }

  @Test
  void findStocks_findsStock() {
    assertEquals(stock, exchange.findStocks("A").getFirst());
  }

  @Test
  void findStock_noStockFound() {
    assertTrue(exchange.findStocks("C").isEmpty());
  }

  @Test
  void buy_nullSymbol_throwsException() {
    assertThrows(IllegalArgumentException.class, () -> exchange.buy(null, bd(2), player));
  }

  @Test
  void buy_nonExistingSymbol_throwsException() {
    assertThrows(NoSuchElementException.class, () -> exchange.buy("MSFT", new BigDecimal("1"), player));
  }

  @Test
  void buy_negativeQuantity_throwsException() {
    assertThrows(IllegalArgumentException.class, () -> exchange.buy("AAPL", new BigDecimal("-5"), player));
  }

  @Test
  void buy_nullPlayer_throwsException() {
    assertThrows(IllegalArgumentException.class, () -> exchange.buy("AAPL", new BigDecimal("1"), null));
  }

  @Test
  void buy_validPurchase_returnsTransaction() {
    Transaction t = exchange.buy("AAPL", new BigDecimal("2"), player);
    assertNotNull(t);
    assertInstanceOf(Purchase.class, t);
  }

  @Test
  void sell_nullShare_throwsException() {
    assertThrows(IllegalArgumentException.class, () -> exchange.sell(null, player));
  }

  @Test
  void sell_nullPlayer_throwsException() {
    Share share = new Share(exchange.getStock("AAPL"), new BigDecimal("1"), new BigDecimal("100"));
    assertThrows(IllegalArgumentException.class, () -> exchange.sell(share, null));
  }

  @Test
  void sell_validSale_returnsTransaction() {
    exchange.buy("AAPL", bd(1), player);
    Transaction t = exchange.sell(player.getPortfolio().getShares("AAPL").getFirst(), player);
    assertNotNull(t);
    assertInstanceOf(Sale.class, t);
  }

  @Test
  void advance_incrementsWeek() {
    exchange.advance();
    assertEquals(2, exchange.getWeek());
  }

  @Test
  void advance_updatesStockPrices() {
    Stock stock = exchange.getStock("AAPL");
    BigDecimal oldPrice = stock.getSalesPrice();

    exchange.advance();

    BigDecimal newPrice = stock.getSalesPrice();
    assertTrue(newPrice.compareTo(oldPrice) >= 0);
  }

  // helper method
  private BigDecimal bd(double num) {
    return BigDecimal.valueOf(num);
  }
}

