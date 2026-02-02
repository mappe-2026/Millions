package edu.ntnu.idatt2003.gruppe50;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Stock {
    private final String symbol;
    private final String company;
    private final List<BigDecimal> prices;

    public Stock(String symbol, String company, BigDecimal salesPrice) {
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
        prices.add(salesPrice);
    }
}
