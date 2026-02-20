package edu.ntnu.idatt2003.gruppe50.model;

import java.util.*;
import java.util.stream.Collectors;

public class Exchange {
    private final String name;
    private final int week;
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


}
