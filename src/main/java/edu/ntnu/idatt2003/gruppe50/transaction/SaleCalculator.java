package edu.ntnu.idatt2003.gruppe50.transaction;

import edu.ntnu.idatt2003.gruppe50.Share;

import java.math.BigDecimal;

public class SaleCalculator implements TransactionCalculator {
    private final BigDecimal purchasePrice;
    private final BigDecimal salesPrice;
    private final BigDecimal quantity;

    public SaleCalculator(Share share, BigDecimal salesPrice) {
        purchasePrice = share.getPurchasePrice();
        this.salesPrice = salesPrice;
        quantity = share.getQuantity();
    }

    @Override
    public BigDecimal calculateGross() {
        return salesPrice.multiply(quantity);
    }

    @Override
    public BigDecimal calculateCommission() {
        return calculateGross().multiply(BigDecimal.valueOf(0.01));
    }

    @Override
    public BigDecimal calculateTax() {
        BigDecimal profit = calculateGross().subtract(calculateCommission()).subtract(purchasePrice.multiply(quantity));

        if (profit.compareTo(BigDecimal.ZERO) > 0) {
            return profit.multiply(BigDecimal.valueOf(0.3));
        } else {
            return BigDecimal.ZERO;
        }
    }

    @Override
    public BigDecimal calculateTotal() {
        return calculateGross().subtract(calculateCommission()).subtract(calculateTax());
    }

}
