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
    private Share share1;
    private Share share2;
    private Share share3;

    @BeforeEach
    void setUp() {
        portfolio = new Portfolio();

        Stock stock = new Stock("KOG", "Kongsberg Gruppen", bd("330"));
        Stock stock2 = new Stock("AAPL", "Apple", bd("400"));

        share1 = new Share(stock, bd("5"), bd("310"));
        share2 = new Share(stock, bd("10"), bd("320"));
        share3 = new Share(stock2, bd("10"), bd("400"));
    }


    @Test
    void addShare_addsToList_returnsTrue() {
        int sizeBefore = portfolio.getShares().size();

        boolean result = portfolio.addShare(share1);

        assertTrue(result);
        assertEquals(sizeBefore + 1, portfolio.getShares().size());
    }

    @Test
    void addShare_wontAddNullShareToList_returnsFalse() {
        int sizeBefore = portfolio.getShares().size();

        boolean result = portfolio.addShare(null);

        assertFalse(result);
        assertEquals(sizeBefore, portfolio.getShares().size());
    }

    @Test
    void removeShare_removesFromList_returnsTrue() {
        int sizeBefore = portfolio.getShares().size();

        portfolio.addShare(share1);
        boolean result = portfolio.removeShare(share1);

        assertTrue(result);
        assertEquals(sizeBefore, portfolio.getShares().size());
    }

    @Test
    void removeNullShare_wontRemoveFromList_returnsFalse() {
        int sizeBefore = portfolio.getShares().size();

        boolean result = portfolio.removeShare(null);

        assertFalse(result);
        assertEquals(sizeBefore, portfolio.getShares().size());
    }

    @Test
    void removeShare_shareNotInPortfolio_returnsFalse() {
        int sizeBefore = portfolio.getShares().size();

        boolean result = portfolio.removeShare(share1);

        assertFalse(result);
        assertEquals(sizeBefore, portfolio.getShares().size());
    }

    @Test
    void getShares_returnsListWithSameElements() {
        portfolio.addShare(share1);

        List<Share> result = portfolio.getShares();

        assertEquals(1, result.size());
        assertSame(share1, result.getFirst());
    }

    @Test
    void getShares_returnsUnmodifiableList() {
        List<Share> result = portfolio.getShares();

        assertThrows(UnsupportedOperationException.class, () -> result.add(share1));
    }

    @Test
    void getShares_returnsNewListEachTime() {
        portfolio.addShare(share1);

        assertNotSame(portfolio.getShares(), portfolio.getShares());
    }

    @Test
    void getShares_throwsIfSymbolIsNull() {
        assertThrows(IllegalArgumentException.class , () -> portfolio.getShares(null));
    }

    @Test
    void getShares_filtersBySymbol() {
        portfolio.addShare(share1);
        portfolio.addShare(share2);

        List<Share> KOGShares = List.of(share1, share2);

        assertEquals(KOGShares, portfolio.getShares("KOG"));
    }

    @Test
    void getShares_whenNoSharesForSymbol_returnsEmptyList() {
        assertEquals(Collections.emptyList(), portfolio.getShares("AAPL"));
    }

    @Test
    void contains_findsShare_returnsTrue() {
        portfolio.addShare(share1);

        assertTrue(portfolio.contains(share1));
    }

    @Test
    void contains_findsDifferentShareWithSameStock_returnsTrue() {
        portfolio.addShare(share2);

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


    //Helper method
    private static BigDecimal bd(String value) {
        return new BigDecimal(value);
    }

}