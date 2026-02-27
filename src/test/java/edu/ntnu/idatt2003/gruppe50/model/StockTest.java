package edu.ntnu.idatt2003.gruppe50.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;


class StockTest {
    private Stock stock;

    @BeforeEach
    void setUp() {
        stock = new Stock("KOG", "Kongsberg Gruppen", bd("330"));
    }

    @Test
    void constructor_validArguments_createsStock() {
        Stock stock = new Stock("AAPL", "Apple", bd("265"));

        assertEquals("AAPL", stock.getSymbol());
        assertEquals("Apple", stock.getCompany());
        assertEquals(bd("265"), stock.getSalesPrice());
    }

    @Test
    void constructor_nullSymbol_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Stock(null, "Apple", bd("265")));
    }

    @Test
    void constructor_blankSymbol_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Stock("   ", "Apple", bd("265")));
    }


    @Test
    void constructor_nullCompany_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Stock("AAPL", null, bd("265")));
    }

    @Test
    void constructor_blankCompany_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Stock("AAPL", "   ", bd("265")));
    }


    @Test
    void constructor_nullSalesPrice_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Stock("AAPL", "Apple", null));
    }

    @Test
    void constructor_negativeSalesPrice_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Stock("AAPL", "Apple", bd("-265")));
    }

    @Test
    void constructor_zeroSalesPrice_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Stock("AAPL", "Apple", bd("0")));
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
        assertEquals(bd("330"), stock.getSalesPrice());
    }

    @Test
    void addNewSalesPrice_updatesLatestSalesPrice() {
        stock.addNewSalesPrice(bd("340"));
        assertEquals(bd("340"), stock.getSalesPrice());
    }

    @Test
    void addNewSalesPrice_multipleUpdates_returnsMostRecent() {
        stock.addNewSalesPrice(bd("340"));
        stock.addNewSalesPrice(bd("350"));
        assertEquals(bd("350"), stock.getSalesPrice());
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
                () -> stock.addNewSalesPrice(bd("-1")));
    }

    //Helper method
    private static BigDecimal bd(String value) {
        return new BigDecimal(value);
    }
}