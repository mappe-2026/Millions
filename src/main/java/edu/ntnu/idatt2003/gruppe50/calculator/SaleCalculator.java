package edu.ntnu.idatt2003.gruppe50.calculator;

import edu.ntnu.idatt2003.gruppe50.model.Share;

import java.math.BigDecimal;

/**
 * Represents a calculator for determining
 * the relevant fees when selling shares.
 */
public class SaleCalculator implements TransactionCalculator {
    private final BigDecimal purchasePrice;
    private final BigDecimal salesPrice;
    private final BigDecimal quantity;

    /**
     * Creates a calculator for the sale of a specific
     * share which can calculate the belonging fees.
     * <p>
     *     Validates that the share is not {@code null}
     *     or that the sales price is less than 0.
     *     If it is an {@link IllegalArgumentException} is thrown.
     * </p>
     *
     * @param share the share being sold, the purchase price and
     *              quantity is used to determine profit.
     */
    public SaleCalculator(Share share) {
        if (share == null) {
            throw new IllegalArgumentException("Share cannot be null");
        }

        this.purchasePrice = share.getPurchasePrice();
        this.quantity = share.getQuantity();
        this.salesPrice = share.getStock().getSalesPrice();
    }

    /**
     * Calculates the gross amount of the sale before any deductions.
     *
     * @return the gross amount as a {@link BigDecimal}
     */
    @Override
    public BigDecimal calculateGross() {
        return salesPrice.multiply(quantity);
    }

    /**
     * Calculates the commission amount charged by the sale.
     *
     * @return the commission amount as a {@link BigDecimal}
     */
    @Override
    public BigDecimal calculateCommission() {
        return calculateGross().multiply(BigDecimal.valueOf(0.01));
    }

    /**
     * Calculates the tax applied to the sale of the shares.
     * <p>
     *     Validates that the transaction is profitable, and
     *     will not tax the player if the player lost money.
     * </p>
     *
     * @return the tax amount as a {@link BigDecimal}
     */
    @Override
    public BigDecimal calculateTax() {
        BigDecimal profit = calculateGross().subtract(calculateCommission()).subtract(purchasePrice.multiply(quantity));

        if (profit.compareTo(BigDecimal.ZERO) > 0) {
            return profit.multiply(BigDecimal.valueOf(0.3));
        } else {
            return BigDecimal.ZERO;
        }
    }

    /**
     * Calculates the total amount won or lost by the sale
     * of the share.
     * <p>
     *     The amount the player received by the transaction
     *     after deductions such as commission and tax.
     * </p>
     *
     * @return the net amount from the sale as {@link BigDecimal}
     */
    @Override
    public BigDecimal calculateTotal() {
        return calculateGross().subtract(calculateCommission()).subtract(calculateTax());
    }

}
