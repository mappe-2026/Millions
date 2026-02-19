package edu.ntnu.idatt2003.gruppe50.transaction;

import edu.ntnu.idatt2003.gruppe50.calculator.SaleCalculator;
import edu.ntnu.idatt2003.gruppe50.model.Share;
import edu.ntnu.idatt2003.gruppe50.model.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SaleTest {
    private Stock stock;
    private Share share;
    private Sale sale;

    @BeforeEach
    void setup() {
        stock = new Stock("KOG", "Kongsberg Gruppen", new BigDecimal("330"));
        share = new Share(stock, new BigDecimal("5"), new BigDecimal("310"));
        sale = new Sale(share, 12);
    }

    @Test
    void constructor_nullShare_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new Sale(null ,12));
    }

    @Test
    void constructor_negativeWeek_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new Sale(share, -12));
    }

    @Test
    void sale_isCommitted_returnsFalseInitially() {
        assertFalse(sale.isCommitted());
    }

    @Test
    void constructor_validArguments_createsShare() {
        Stock stock2 = new Stock("AAPL", "Apple", new BigDecimal("265"));
        Share share2 = new Share(stock, new BigDecimal("3"), new BigDecimal("250"));
        Sale sale2 = new Sale(share2, 12);

        assertEquals(share2, sale2.getShare());
        assertEquals(12, sale2.getWeek());
        assertTrue(sale2.getCalculator() instanceof SaleCalculator);
    }
}
