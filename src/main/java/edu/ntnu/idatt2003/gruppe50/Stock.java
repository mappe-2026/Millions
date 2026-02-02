package edu.ntnu.idatt2003.gruppe50;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Stock {
    private final String symbol;
    private final String company;
    private final List<BigDecimal> prices;

    public Stock(String symbol, String company, BigDecimal salesPrice) {
        if (symbol == null || symbol.isBlank()) {
            throw new IllegalArgumentException("Symbol cannot be null or blank");
        }
        if (company == null || company.isBlank()) {
            throw new IllegalArgumentException("Company cannot be null or blank");
        }
        if (salesPrice == null || salesPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Sales price must be positive");
        }

        this.symbol = symbol;
        this.company = company;
        this.prices = new ArrayList<>();
        this.prices.add(salesPrice);
    }

    public String getSymbol() {
        return symbol;
    }

    public String getCompany() {
        return company;
    }

    public BigDecimal getSalesPrice() {
        return prices.getLast();
    }

    public void addNewSalesPrice(BigDecimal salesPrice) {
        if (salesPrice == null || salesPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Sales price must be positive");
        }
        prices.add(salesPrice);
    }
}
