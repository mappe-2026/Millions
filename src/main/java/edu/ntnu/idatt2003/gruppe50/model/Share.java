package edu.ntnu.idatt2003.gruppe50.model;

import java.math.BigDecimal;

/**
 * Represents a holding of a specific stock.
 * <p>
 * A share consists of a stock, the quantity owned
 * and the purchase price per unit.
 * </p>
 */
public class Share {
    private final Stock stock;
    private final BigDecimal quantity;
    private final BigDecimal purchasePrice;

    /**
     * Creates a new {@code Share} with the given stock, quantity and purchase price.
     *
     * @param stock the stock that is owned
     * @param quantity the number of units owned
     * @param purchasePrice the purchase price per unit
     * @throws IllegalArgumentException if any argument is null, zero or negative.
     */
    public Share(Stock stock, BigDecimal quantity, BigDecimal purchasePrice) {
        if (stock == null) {
            throw new IllegalArgumentException("Stock cannot be null");
        }
        if (quantity == null || quantity.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Quantity cannot be null and must be positive");
        }
        if (purchasePrice == null || purchasePrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Purchase price cannot be null and must be positive");
        }

        this.stock = stock;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
    }

    /**
     * Returns the stock associated with this share.
     *
     * @return the stock
     */
    public Stock getStock() {
        return stock;
    }

    /**
     * Returns the quantity owned of this stock.
     *
     * @return quantity
     */
    public BigDecimal getQuantity() {
        return quantity;
    }

    /**
     * Returns the purchase price per unit for this stock
     *
     * @return purchase price
     */
    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }
}

