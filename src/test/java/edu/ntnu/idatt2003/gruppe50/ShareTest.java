package edu.ntnu.idatt2003.gruppe50;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ShareTest {
    private Stock stock;
    private Share share;

    @BeforeEach
    void setUp() {
        stock = new Stock("KOG", "Kongsberg Gruppen", new BigDecimal("330"));
        share = new Share(stock, new BigDecimal("5"), new BigDecimal("310"));
    }

    @Test
    void constructor_validArguments_createsShare() {
        Stock stock = new Stock("AAPL", "Apple", new BigDecimal("265"));
        Share share = new Share(stock, new BigDecimal("3"), new BigDecimal("250"));

        assertEquals(stock, share.getStock());
        assertEquals(new BigDecimal("3"), share.getQuantity());
        assertEquals(new BigDecimal("250"), share.getPurchasePrice());
    }

    @Test
    void constructor_nullStock_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Share(null,
                new BigDecimal("10"), new BigDecimal("6")));
    }

    @Test
    void constructor_negativeQuantity_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Share(stock, new BigDecimal("-7"), new BigDecimal("20")));
    }

    @Test
    void constructor_nullQuantity_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Share(stock, null, new BigDecimal("20")));
    }

    @Test
    void constructor_zeroQuantity_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Share(stock, BigDecimal.ZERO, new BigDecimal("20")));
    }

    @Test
    void constructor_negativePurchasePrice_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Share(stock, new BigDecimal("7"), new BigDecimal("-1")));
    }

    @Test
    void constructor_nullPurchasePrice_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Share(stock, new BigDecimal("7"), null));
    }

    @Test
    void constructor_zeroPurchasePrice_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Share(stock, new BigDecimal("7"), BigDecimal.ZERO));
    }

    @Test
    void getStock_returnsStock() {
        assertEquals(stock, share.getStock());
    }

    @Test
    void getQuantity_returnsQuantity() {
        assertEquals(new BigDecimal("5"), share.getQuantity());
    }

    @Test
    void getPurchasePrice_returnsPurchasePrice() {
        assertEquals(new BigDecimal("310"), share.getPurchasePrice());
    }
}