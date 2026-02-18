package edu.ntnu.idatt2003.gruppe50.calculator;

import edu.ntnu.idatt2003.gruppe50.model.Share;

import java.math.BigDecimal;

/**
 * Calculator for determining the price of purchasing shares.
 */
public class PurchaseCalculator implements TransactionCalculator {
    private final BigDecimal purchasePrice;
    private final BigDecimal quantity;

    /**
     * Creates a calculator for determining the price of a share.
     * <p>
     *     Validates that the share is not {@code null}.
     *     If null an {@link IllegalArgumentException} is thrown.
     * </p>
     *
     * @param share being purchased, the quantity and
     *              purchase price is used to determine the cost.
     */
    public PurchaseCalculator(Share share) {
        if (share == null) {
            throw new IllegalArgumentException("Share cannot be null");
        }

        purchasePrice = share.getPurchasePrice();
        quantity = share.getQuantity();
    }

    /**
     * Calculates the gross amount of the purchase before any fees.
     *
     * @return the gross amount as a {@link BigDecimal}
     */
    @Override
    public BigDecimal calculateGross() {
        return purchasePrice.multiply(quantity);
    }

    /**
     * Calculates the commission charged for the purchase.
     *
     * @return the commission amount as a {@link BigDecimal}
     */
    @Override
    public BigDecimal calculateCommission() {
        return calculateGross().multiply(BigDecimal.valueOf(0.05));
    }

    /**
     * Calculates the tax applied to the purchase.
     * <p>
     *     There is no tax on purchasing shares, so it will return 0.
     * </p>
     * @return the tax amount as a {@link BigDecimal}
     */
    @Override
    public BigDecimal calculateTax() {
        return BigDecimal.valueOf(0);
    }

    /**
     * Calculates the total amount of the purchase of the share.
     *
     * @return the total cost of a purchase as a {@link BigDecimal}
     */
    @Override
    public BigDecimal calculateTotal() {
        return calculateGross().add(calculateCommission()).add(calculateTax());
    }


}
