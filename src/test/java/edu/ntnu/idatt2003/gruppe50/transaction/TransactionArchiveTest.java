package edu.ntnu.idatt2003.gruppe50.transaction;

import edu.ntnu.idatt2003.gruppe50.model.Share;
import edu.ntnu.idatt2003.gruppe50.model.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionArchiveTest {
    private Share share;
    private TransactionArchive archive;
    private Purchase p;
    private Sale s;

    @BeforeEach
    void setup() {
        Stock stock = new Stock("KOG", "Kongsberg Gruppen", bd("330"));
        share = new Share(stock, bd("5"), bd("310"));
        archive = new TransactionArchive();
        p = new Purchase(share, 1);
        s = new Sale(share, 1);
    }

    @Test
    void newTransactionArchive_isEmpty() {
        assertTrue(new TransactionArchive().isEmpty());
    }

    @Test
    void addingNullTransaction_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> archive.add(null));
    }

    @Test
    void add_returnsFalse_whenSameInstanceAlreadyExists() {
        Purchase purchase = new Purchase(share, 1);
        assertTrue(archive.add(purchase));
        assertFalse(archive.add(purchase));
        assertTrue(archive.add(new Purchase(share, 1)));
    }

    @Test
    void getTransaction_returnsTransaction() {
        Purchase purchase = new Purchase(share, 1);
        archive.add(purchase);
        assertSame(purchase, archive.getTransactions(1).getFirst());
    }

    @Test
    void getTransaction_throwsWhenWeekIsBelowOne() {
        assertThrows(IllegalArgumentException.class, () -> archive.getTransactions(0));
        assertThrows(IllegalArgumentException.class, () -> archive.getTransactions(-1));
    }

    @Test
    void getPurchase_returnsPurchase() {
        Purchase purchase = new Purchase(share, 1);
        archive.add(purchase);
        assertSame(purchase, archive.getPurchases(1).getFirst());
    }

    @Test
    void getPurchases_ignoresNonPurchasesInSameWeek() {
        archive.add(p);
        archive.add(s);

        List<Purchase> purchases = archive.getPurchases(1);

        assertEquals(1, purchases.size());
        assertSame(p, purchases.getFirst());
    }

    @Test
    void getSales_returnsSale() {
        Sale sale = new Sale(share, 1);
        archive.add(sale);
        assertSame(sale, archive.getSales(1).getFirst());
    }

    @Test
    void getSales_ignoresNonSaleInSameWeek() {
        archive.add(p);
        archive.add(s);

        List<Sale> sales = archive.getSales(1);

        assertEquals(1, sales.size());
        assertSame(s, sales.getFirst());
    }

    @Test
    void countDistinctWeeks_returnsOne_whenAllTransactionsAreInSameWeek() {
        archive.add(new Purchase(share, 1));
        archive.add(new Sale(share, 1));
        archive.add(new Purchase(share, 1));

        assertEquals(1, archive.countDistinctWeeks());
    }

    @Test
    void countDistinctWeeks_countsTwo_whenTransactionsExistInTwoWeeks() {
        archive.add(new Purchase(share, 1));
        archive.add(new Sale(share, 2));

        assertEquals(2, archive.countDistinctWeeks());
    }

    //Helper method
    private static BigDecimal bd(String value) {
        return new BigDecimal(value);
    }
}
