package edu.ntnu.idatt2003.gruppe50.transaction;

import java.math.BigDecimal;

public interface TransactionCalculator {
    public BigDecimal calculateGross();
    public BigDecimal calculateCommission();
    public BigDecimal calculateTax();
    public BigDecimal calculateTotal();
}
