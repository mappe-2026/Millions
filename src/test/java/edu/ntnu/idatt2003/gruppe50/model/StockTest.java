package edu.ntnu.idatt2003.gruppe50.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;


class StockTest {
    private Stock stock;

    @BeforeEach
    void setUp() {
        stock = new Stock("KOG", "Kongsberg Gruppen", new BigDecimal("330"));
    }

    @Test
    void constructor_validArguments_createsStock() {
        Stock stock = new Stock("AAPL", "Apple", new BigDecimal("265"));

        assertEquals("AAPL", stock.getSymbol());
        assertEquals("Apple", stock.getCompany());
        assertEquals(new BigDecimal("265"), stock.getSalesPrice());
    }

    @Test
    void constructor_nullSymbol_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Stock(null, "Apple", new BigDecimal("265")));
    }

    @Test
    void constructor_blankSymbol_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Stock("   ", "Apple", new BigDecimal("265")));
    }


    @Test
    void constructor_nullCompany_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Stock("AAPL", null, new BigDecimal("265")));
    }

    @Test
    void constructor_blankCompany_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Stock("AAPL", "   ", new BigDecimal("265")));
    }


    @Test
    void constructor_nullSalesPrice_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Stock("AAPL", "Apple", null));
    }

    @Test
    void constructor_negativeSalesPrice_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Stock("AAPL", "Apple", new BigDecimal("-265")));
    }

    @Test
    void constructor_zeroSalesPrice_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Stock("AAPL", "Apple", new BigDecimal("0")));
    }


    @Test
    void getSymbol_returnsSymbol() {
        assertEquals("KOG", stock.getSymbol());
    }

    @Test
    void getCompany_returnsCompany() {
        assertEquals("Kongsberg Gruppen", stock.getCompany());
    }

    @Test
    void getSalesPrice_returnsLatestSalesPrice() {
        assertEquals(new BigDecimal("330"), stock.getSalesPrice());
    }

    @Test
    void addNewSalesPrice_updatesLatestSalesPrice() {
        stock.addNewSalesPrice(new BigDecimal("340"));
        assertEquals(new BigDecimal("340"), stock.getSalesPrice());
    }

    @Test
    void addNewSalesPrice_multipleUpdates_returnsMostRecent() {
        stock.addNewSalesPrice(new BigDecimal("340"));
        stock.addNewSalesPrice(new BigDecimal("350"));
        assertEquals(new BigDecimal("350"), stock.getSalesPrice());
    }

    @Test
    void addNewSalesPrice_null_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> stock.addNewSalesPrice(null));
    }

    @Test
    void addNewSalesPrice_zero_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> stock.addNewSalesPrice(BigDecimal.ZERO));
    }

    @Test
    void addNewSalesPrice_negative_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> stock.addNewSalesPrice(new BigDecimal("-1")));
    }
}