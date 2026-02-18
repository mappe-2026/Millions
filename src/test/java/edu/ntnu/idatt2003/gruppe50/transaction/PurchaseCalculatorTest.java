package edu.ntnu.idatt2003.gruppe50.transaction;

import edu.ntnu.idatt2003.gruppe50.Share;
import edu.ntnu.idatt2003.gruppe50.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class PurchaseCalculatorTest {
    private Share share;
    private PurchaseCalculator calc;

    @BeforeEach
    void setup() {
        Stock stock = new Stock("KOG", "Kongsberg Gruppen", new BigDecimal("330"));
        share = new Share(stock, new BigDecimal("5"), new BigDecimal("100"));
        calc = new PurchaseCalculator(share);
    }

    @Test
    void constructor_nullShare_throwsException() {
       assertThrows(IllegalArgumentException.class, () -> new PurchaseCalculator(null));
    }

    @Test
    void constructor_validArgument_createsCalculator() {
        assertDoesNotThrow(() -> new PurchaseCalculator(share));
    }

    @Test
    void calculateGross_returnsCorrectValue() {
        assertEquals(new BigDecimal(500), calc.calculateGross());
    }

    @Test
    void calculateCommission_returnsFivePercentOfGross() {
        assertEquals(new BigDecimal(25), calc.calculateCommission().stripTrailingZeros());
    }

    @Test
    void calculateTax_returnsZero() {
        assertEquals(BigDecimal.ZERO, calc.calculateTax().stripTrailingZeros());
    }

    @Test
    void calculateTotal_returnsCorrectTotal() {
        assertEquals(new BigDecimal(525), calc.calculateTotal().stripTrailingZeros());
    }

}
