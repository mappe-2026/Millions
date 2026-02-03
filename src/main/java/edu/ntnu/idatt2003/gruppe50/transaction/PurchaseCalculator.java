package edu.ntnu.idatt2003.gruppe50.transaction;

import edu.ntnu.idatt2003.gruppe50.Share;

import java.math.BigDecimal;

public class PurchaseCalculator implements TransactionCalculator {
    private final BigDecimal purchasePrice;
    private final BigDecimal quantity;

    public PurchaseCalculator(Share share) {
        if (share == null) {
            throw new IllegalArgumentException("Share cannot be null");
        }

        purchasePrice = share.getPurchasePrice();
        quantity = share.getQuantity();
    }

    @Override
    public BigDecimal calculateGross() {
        return purchasePrice.multiply(quantity);
    }

    @Override
    public BigDecimal calculateCommission() {
        return calculateGross().multiply(BigDecimal.valueOf(0.05));
    }

    @Override
    public BigDecimal calculateTax() {
        return BigDecimal.valueOf(0);
    }

    @Override
    public BigDecimal calculateTotal() {
        return calculateGross().add(calculateCommission()).add(calculateTax());
    }


}
