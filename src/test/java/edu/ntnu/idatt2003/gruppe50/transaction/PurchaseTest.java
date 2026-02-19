package edu.ntnu.idatt2003.gruppe50.transaction;

import edu.ntnu.idatt2003.gruppe50.calculator.PurchaseCalculator;
import edu.ntnu.idatt2003.gruppe50.model.Share;
import edu.ntnu.idatt2003.gruppe50.model.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class PurchaseTest {
    private Stock stock;
    private Share share;
    private Purchase purchase;

    @BeforeEach
    void setup() {
        stock = new Stock("KOG", "Kongsberg Gruppen", new BigDecimal("330"));
        share = new Share(stock, new BigDecimal("5"), new BigDecimal("310"));
        purchase = new Purchase(share, 12);
    }

    @Test
    void constructor_nullShare_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new Purchase(null ,12));
    }

    @Test
    void constructor_negativeWeek_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new Purchase(share, -12));
    }

    @Test
    void purchase_isCommitted_returnsFalseInitially() {
        assertFalse(purchase.isCommitted());
    }

    @Test
    void constructor_validArguments_createsShare() {
        Stock stock2 = new Stock("AAPL", "Apple", new BigDecimal("265"));
        Share share2 = new Share(stock, new BigDecimal("3"), new BigDecimal("250"));
        Purchase purchase2 = new Purchase(share2, 12);

        assertEquals(share2, purchase2.getShare());
        assertEquals(12, purchase2.getWeek());
        assertTrue(purchase2.getCalculator() instanceof PurchaseCalculator);
    }



}
