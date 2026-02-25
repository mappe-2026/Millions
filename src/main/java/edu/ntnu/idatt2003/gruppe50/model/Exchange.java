package edu.ntnu.idatt2003.gruppe50.model;

import edu.ntnu.idatt2003.gruppe50.transaction.Purchase;
import edu.ntnu.idatt2003.gruppe50.transaction.Sale;
import edu.ntnu.idatt2003.gruppe50.transaction.Transaction;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Exchange {
    private final String name;
    private int week;
    private final Map<String, Stock> stockMap;
    private final Random random;

    public Exchange(String name, List<Stock> stocks) {
        this.name = name;
        stockMap = stocks.stream()
                .collect(Collectors.toMap(
                        Stock::getSymbol,
                        v -> v
                ));

        week = 1;
        random = new Random();
    }

    public String getName() {
        return name;
    }

    public int getWeek() {
        return week;
    }

    public boolean hasStock(String symbol) {
        return stockMap.containsKey(symbol);
    }

    public Stock getStock(String symbol) {
        if (!hasStock(symbol)) {
            throw new NoSuchElementException("No stock by that symbol");
        }
        return stockMap.get(symbol);
    }

    public List<Stock> findStocks(String searchTerm) {

        return stockMap.values().stream()
                .filter(stock ->
                        stock.getSymbol().toLowerCase().contains(searchTerm.toLowerCase()) ||
                        stock.getCompany().toLowerCase().contains(searchTerm.toLowerCase())
                ).toList();
    }

    public Transaction buy(String symbol, BigDecimal quantity, Player player) {
        if (!hasStock(symbol)) {
            throw new NoSuchElementException("No stock by that symbol");
        }
        if (quantity.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("quantity cannot be less than 1");
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

    public void advance() {
        this.week++;

        stockMap.replaceAll((symbol, stock) -> {
            double change = random.nextDouble()*0.4;
            BigDecimal multiplier = BigDecimal.valueOf(1 + change);

            BigDecimal newPrice = stock.getSalesPrice().multiply(multiplier);
            stock.addNewSalesPrice(newPrice);
            return stock;
        });
    }

}