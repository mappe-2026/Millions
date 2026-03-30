package edu.ntnu.idatt2003.gruppe50.model;

import edu.ntnu.idatt2003.gruppe50.transaction.Transaction;
import edu.ntnu.idatt2003.gruppe50.transaction.TransactionFactory;
import edu.ntnu.idatt2003.gruppe50.util.Validate;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents the exchange for buying and selling shares.
 * <p>
 *   The exchange updates every week with new
 *   prices so the player can buy and sell unique stocks.
 * </p>
 */
public class Exchange {
    private final String name;
    private int week;
    private final Map<String, Stock> stockMap;
    private final Random random;
    private final TransactionFactory factory;

    /**
     * Creates a new {@code exchange} with a name and stocks represented by symbols.
     *
     * @param name The name of the exchange
     * @param stocks The stocks in the exchange
     * @throws IllegalArgumentException if any parameter is null or invalid
     */
    public Exchange(String name, List<Stock> stocks) {
        Validate.notBlank(name, "Name");
        Validate.notEmpty(stocks, "Stocks");

        Validate.notNull(factory, "Factory");

        this.name = name;
        stockMap = stocks.stream()
                .collect(Collectors.toMap(
                        Stock::getSymbol,
                        v -> v
                ));

        week = 1;
        random = new Random();
        this.factory = factory;
    }

    /**
     * Returns the exchanges name.
     *
     * @return name of the exchange
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the current week.
     *
     * @return the week
     */
    public int getWeek() {
        return week;
    }

    /**
     * Checks if the stock is present in the exchange.
     *
     * @param symbol the stock symbol
     * @return {@code true} if the exchange contains a stock with the given symbol,
     *         {@code false} otherwise
     * @throws IllegalArgumentException if {@code symbol} is null or blank
     */
    public boolean hasStock(String symbol) {
        Validate.notBlank(symbol, "Symbol");
        return stockMap.containsKey(symbol);
    }

    /**
     * Returns stock from exchange.
     *
     * @param symbol the stock symbol
     * @return stock from exchange
     * @throws IllegalArgumentException if {@code symbol} is null or blank
     * @throws NoSuchElementException if no stock with the given symbol exists
     */
    public Stock getStock(String symbol) {
        Validate.notBlank(symbol, "Symbol");
        if (!hasStock(symbol)) {
            throw new NoSuchElementException("No stock by that symbol");
        }
        return stockMap.get(symbol);
    }

    /**
     * Search stocks in the exchange.
     *
     * @param searchTerm name or symbol of stock
     * @return a list of stocks
     * @throws IllegalArgumentException if search term is null or blank
     */
    public List<Stock> findStocks(String searchTerm) {
        Validate.notBlank(searchTerm, "Searchterm");

        return stockMap.values().stream()
                .filter(stock ->
                        stock.getSymbol().toLowerCase().contains(searchTerm.toLowerCase()) ||
                        stock.getCompany().toLowerCase().contains(searchTerm.toLowerCase())
                ).toList();
    }

    /**
     * Buys a share from the exchange.
     *
     * @param symbol the stock symbol
     * @param quantity the number of stocks
     * @param player the player
     * @return a purchase
     * @throws IllegalArgumentException if {@code symbol} is null or blank, {@code quantity} is null
     *                                  or negative, or {@code player} is null
     * @throws NoSuchElementException if no stock with the given symbol exists
     */
    public Transaction buy(String symbol, BigDecimal quantity, Player player) {
        Validate.positive(quantity, "Quantity");
        Validate.notNull(player, "Player");

        Stock stock = getStock(symbol);
        Share share = new Share(stock, quantity, stock.getSalesPrice());

        Transaction t = factory.createPurchase(share, this.week);
        t.commit(player);
        return t;
    }

    /**
     * Sells a share the player holds.
     *
     * @param share the share to be sold
     * @param player the player
     * @return a sale
     * @throws IllegalArgumentException if {@code share} or {@code player} is null
     */
    public Transaction sell(Share share, Player player) {
        Validate.notNull(share, "Share");
        Validate.notNull(player, "Player");

        Transaction t = factory.createSale(share, this.week);
        t.commit(player);
        return t;
    }

    /**
     * Advances a week and randomizes stock prices.
     */
    public void advance() {
        this.week++;

        stockMap.replaceAll((_, stock) -> {
            double change = -0.2 + random.nextDouble()*0.4;
            BigDecimal multiplier = BigDecimal.valueOf(1 + change);

            BigDecimal newPrice = stock.getSalesPrice().multiply(multiplier);
            stock.addNewSalesPrice(newPrice);
            return stock;
        });
    }

    /**
     * Returns a list of the stocks with the most profit.
     *
     * @param limit how many stocks do you want in the list
     * @return list of stocks with the most profitable stocks
     */
    public List<Stock> getGainers(int limit) {
        return stockMap.values().stream()
              .sorted((a,b) -> b.getLatestPriceChange().compareTo(a.getLatestPriceChange()))
              .limit(limit)
              .toList();
    }

    /**
     * Returns a list of the stocks with the biggest loss.
     *
     * @param limit how many stocks do you want in the list
     * @return list of stocks with the least profitable stocks
     */
    public List<Stock> getLosers(int limit) {
        return stockMap.values().stream()
              .sorted((a, b) -> a.getLatestPriceChange().compareTo(b.getLatestPriceChange()))
              .limit(limit)
              .toList();
    }

}