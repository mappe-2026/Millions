package edu.ntnu.idatt2003.gruppe50.calculator;

import edu.ntnu.idatt2003.gruppe50.model.Share;
import edu.ntnu.idatt2003.gruppe50.model.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SaleCalculatorTest {
    private Share share;
    private SaleCalculator calc;

    @BeforeEach
    void setup() {
        Stock stock = new Stock("KOG", "Kongsberg Gruppen", new BigDecimal("100"));
        share = new Share(stock, new BigDecimal("5"), new BigDecimal("100"));
        calc = new SaleCalculator(share);
    }

    @Test
    void constructor_nullShare_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new SaleCalculator(null));
    }


    @Test
    void constructor_validArgument_createsCalculator() {
        assertDoesNotThrow(() -> new SaleCalculator(share));
    }

    @Test
    void calculateGross_returnsCorrectValue() {
        assertEquals(new BigDecimal(500), calc.calculateGross());
    }

    @Test
    void calculateCommission_returnsFivePercentOfGross() {
        assertEquals(new BigDecimal(5), calc.calculateCommission().stripTrailingZeros());
    }

    @Test
    void calculateTax_returnsZero() {
        assertEquals(BigDecimal.ZERO, calc.calculateTax().stripTrailingZeros());
    }

    @Test
    void calculateTotal_returnsCorrectTotal() {
        assertEquals(new BigDecimal(495), calc.calculateTotal().stripTrailingZeros());
    }
}
