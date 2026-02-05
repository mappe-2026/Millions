package edu.ntnu.idatt2003.gruppe50.model;


import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.util.List;


class PortfolioTest {
    private Portfolio portfolio;
    private Stock stock;
    private Share share1;
    private Share share2;
    private int sizeBefore;

    @BeforeEach
    void setUp() {
        portfolio = new Portfolio();
        stock = new Stock("KOG", "Kongsberg Gruppen", new BigDecimal("330"));
        share1 = new Share(stock, new BigDecimal("5"), new BigDecimal("310"));
        share2 = new Share(stock, new BigDecimal("10"), new BigDecimal("320"));
        portfolio.addShare(share2);
        sizeBefore = portfolio.getShares().size();
    }


    @Test
    void addShare_addsToList_returnsTrue() {
        assertTrue(portfolio.addShare(share1));
        assertEquals(sizeBefore + 1, portfolio.getShares().size());
    }

    @Test
    void addShare_wontAddToList_returnsFalse() {
        assertFalse(portfolio.addShare(null));
        assertEquals(sizeBefore, portfolio.getShares().size());
    }

    @Test
    void removeShare_removesFromList_returnsTrue() {
        assertTrue(portfolio.removeShare(share2));
        assertEquals(sizeBefore - 1, portfolio.getShares().size());
    }

    @Test
    void removeNullShare_wontRemoveFromList_returnsFalse() {
        assertFalse(portfolio.removeShare(null));
        assertEquals(sizeBefore, portfolio.getShares().size());
    }

    @Test
    void removeShare_shareNotInPortfolio_returnsFalse() {
        assertFalse(portfolio.removeShare(share1));
        assertEquals(sizeBefore, portfolio.getShares().size());
    }

    @Test
    void getShares_returnsCopyWithSameElements() {
        List<Share> result = portfolio.getShares();

        assertEquals(1, result.size());
        assertSame(share2, result.getFirst());
    }
}