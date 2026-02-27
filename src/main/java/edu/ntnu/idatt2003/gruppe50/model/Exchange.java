package edu.ntnu.idatt2003.gruppe50.model;

import edu.ntnu.idatt2003.gruppe50.transaction.Purchase;
import edu.ntnu.idatt2003.gruppe50.transaction.Sale;
import edu.ntnu.idatt2003.gruppe50.transaction.Transaction;

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

    /**
     * Creates a new {@code exchange} with a name and stocks represented by symbols.
     *
     * @param name The name of the exchange
     * @param stocks The stocks in the exchange
     * @throws IllegalArgumentException if any parameter is null or invalid
     */
    public Exchange(String name, List<Stock> stocks) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if (stocks == null || stocks.isEmpty()) {
            throw new IllegalArgumentException("Stocks cannot be null or empty");
        }

        this.name = name;
        stockMap = stocks.stream()
                .collect(Collectors.toMap(
                        Stock::getSymbol,
                        v -> v
                ));

        week = 1;
        random = new Random();
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
     */
    public boolean hasStock(String symbol) {
        if (symbol == null || symbol.isBlank()) {
            throw new IllegalArgumentException("Symbol cannot be null or blank");
        }

        return stockMap.containsKey(symbol);
    }

    /**
     * Returns stock from exchange.
     *
     * @param symbol the stock symbol
     * @return stock from exchange
     */
    public Stock getStock(String symbol) {
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
        if (searchTerm == null || searchTerm.isBlank()) {
            throw new IllegalArgumentException("Search term cannot be null or blank");
        }

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
     */
    public Transaction buy(String symbol, BigDecimal quantity, Player player) {
        if (!hasStock(symbol)) {
            throw new NoSuchElementException("No stock by that symbol");
        }
        if (quantity == null) {
            throw new IllegalArgumentException("Quantity cannot be null");
        }
        if (quantity.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Quantity cannot be less than or equal to 0");
        }
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }

        Stock stock = getStock(symbol);
        Share share = new Share(stock, quantity, stock.getSalesPrice());
        Purchase purchase = new Purchase(share, this.week);
        purchase.commit(player);

        return purchase;
    }

    /**
     * Sells a share the player holds.
     *
     * @param share the share to be sold
     * @param player the player
     * @return a sale
     */
    public Transaction sell(Share share, Player player) {
        if (share == null) {
            throw new IllegalArgumentException("Share cannot be null");
        }
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }

        Sale sale = new Sale(share, this.week);
        sale.commit(player);

        return sale;
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

}