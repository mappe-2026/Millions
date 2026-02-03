package edu.ntnu.idatt2003.gruppe50;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * Represents a stock traded on the market.<br>
 * A stock has a symbol, company and a history of sale prices.
 */
public class Stock {
    private final String symbol;
    private final String company;
    private final List<BigDecimal> prices;

    /**
     * Creates a new {@code Stock} with an initial sales price.
     *
     * @param symbol the stock symbol (e.g. AAPL)
     * @param company the company name (e.g. Apple)
     * @param salesPrice the initial sales price
     * @throws IllegalArgumentException if any argument is invalid
     */
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

    /**
     * Returns the symbol of the company for the stock.
     *
     * @return symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Returns the company name of the stock.
     *
     * @return company
     */
    public String getCompany() {
        return company;
    }

    /**
     * Returns the latest sales price of the stock.
     *
     * @return the most recent sales price
     */
    public BigDecimal getSalesPrice() {
        return prices.getLast();
    }

    /**
     * Adds a new sales price to the stock.
     *
     * @param salesPrice the new sales price
     * @throws IllegalArgumentException if the price is null, zero or negative
     */
    public void addNewSalesPrice(BigDecimal salesPrice) {
        if (salesPrice == null || salesPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Sales price must be positive");
        }
        prices.add(salesPrice);
    }
}
