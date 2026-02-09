package edu.ntnu.idatt2003.gruppe50.model;


import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;


class PortfolioTest {
    private Portfolio portfolio;
    private Stock stock;
    private Stock stock2;
    private Share share1;
    private Share share2;
    private Share share3;
    private int sizeBefore;

    @BeforeEach
    void setUp() {
        portfolio = new Portfolio();
        stock = new Stock("KOG", "Kongsberg Gruppen", new BigDecimal("330"));
        stock2 = new Stock("AAPL", "Apple", new BigDecimal("400"));
        share1 = new Share(stock, new BigDecimal("5"), new BigDecimal("310"));
        share2 = new Share(stock, new BigDecimal("10"), new BigDecimal("320"));
        share3 = new Share(stock2, new BigDecimal("10"), new BigDecimal("400"));
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

    @Test
    void getShares_returnsUnmodifiableList() {
        List<Share> result = portfolio.getShares();

        assertThrows(UnsupportedOperationException.class, result::clear);
    }

    @Test
    void getShares_returnsNewListEachTime() {
        assertNotSame(portfolio.getShares(), portfolio.getShares());
    }

    @Test
    void getShares_throwsIfSymbolIsNull() {
        assertThrows(IllegalArgumentException.class , () -> portfolio.getShares(null));
    }

    @Test
    void getShares_filtersBySymbol() {
        List<Share> KOGShares = List.of(share2, share1);
        portfolio.addShare(share1);
        assertEquals(KOGShares, portfolio.getShares("KOG"));
    }

    @Test
    void getShares_whenNoSharesForSymbol_returnsEmptyList() {
        assertEquals(Collections.emptyList(), portfolio.getShares("AAPL"));
    }
    //Kan vurdere flere tester

    @Test
    void contains_findsShare_returnsTrue() {
        assertTrue(portfolio.contains(share2));
    }

    @Test
    void contains_findsDifferentShareWithSameStock_returnsTrue() {
        assertTrue(portfolio.contains(share1));
    }

    @Test
    void contains_cantFindShare_returnsFalse() {
        assertFalse(portfolio.contains(share3));
    }

    @Test
    void contains_onEmptyPortfolio_returnsFalse() {
        Portfolio empty = new Portfolio();

        assertFalse(empty.contains(share2));
    }

    @Test
    void contains_nullShare_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> portfolio.contains(null));
    }

}