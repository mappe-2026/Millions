package edu.ntnu.idatt2003.gruppe50.transaction;

import edu.ntnu.idatt2003.gruppe50.calculator.PurchaseCalculator;
import edu.ntnu.idatt2003.gruppe50.model.Player;
import edu.ntnu.idatt2003.gruppe50.model.Share;
import edu.ntnu.idatt2003.gruppe50.model.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class PurchaseTest {
    private Share share;
    private Purchase purchase;
    private Player richPlayer;
    private Player poorPlayer;

    @BeforeEach
    void setup() {
        Stock stock = new Stock("KOG", "Kongsberg Gruppen", bd("330"));
        share = new Share(stock, new BigDecimal("5"), bd("310"));
        purchase = new Purchase(share, 12);
        richPlayer = new Player("Test", bd("20000"));
        poorPlayer = new Player("Test2", bd("1"));
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
    void commit_setsCommittedTrue_whenSuccessful() {
        purchase.commit(richPlayer);
        assertTrue(purchase.isCommitted());
    }

    @Test
    void constructor_validArguments_createsShare() {
        Stock stock2 = new Stock("AAPL", "Apple", bd("265"));
        Share share2 = new Share(stock2, bd("3"), bd("250"));
        Purchase purchase2 = new Purchase(share2, 12);

        assertEquals(share2, purchase2.getShare());
        assertEquals(12, purchase2.getWeek());
        assertInstanceOf(PurchaseCalculator.class, purchase2.getCalculator());
    }

    @Test
    void commit_nullPlayer_throwsException () {
        assertThrows(IllegalArgumentException.class, () -> purchase.commit(null));
    }


    @Test
    void isCommitted_remainsFalse_whenCommitFails_dueToInsufficientFunds () {
        assertThrows(IllegalArgumentException.class, () -> purchase.commit(poorPlayer));
        assertFalse(purchase.isCommitted());
    }

    @Test
    void commit_doesNothing_whenAlreadyCommitted() {
        purchase.commit(richPlayer);
        BigDecimal moneyBefore = richPlayer.getMoney();
        int shareBefore = richPlayer.getPortfolio().getShares().size();
        int transactionArchiveBefore = richPlayer.getTransactionArchive().getTransactions(12).size();

        purchase.commit(richPlayer);

        assertEquals(moneyBefore, richPlayer.getMoney());
        assertEquals(shareBefore, richPlayer.getPortfolio().getShares().size());
        assertEquals(transactionArchiveBefore, richPlayer.getTransactionArchive().getTransactions(12).size());
    }

    @Test
    void commit_updatesPlayerState_whenSuccessful() {
        BigDecimal moneyBefore = richPlayer.getMoney();
        int sharesBefore = richPlayer.getPortfolio().getShares().size();
        int archiveBefore = richPlayer.getTransactionArchive().getTransactions(12).size();

        purchase.commit(richPlayer);

        BigDecimal expectedMoney = moneyBefore.subtract(purchase.getCalculator().calculateTotal());

        assertAll(
                () -> assertEquals(expectedMoney, richPlayer.getMoney(), "Money should decrease by total cost"),
                () -> assertEquals(sharesBefore + 1, richPlayer.getPortfolio().getShares().size(), "Portfolio should gain 1 share"),
                () -> assertTrue(richPlayer.getPortfolio().contains(share), "Portfolio should contain the purchased share"),
                () -> assertEquals(archiveBefore + 1, richPlayer.getTransactionArchive().getTransactions(12).size(), "Archive should gain 1 transaction")
        );
    }

    //Helper method
    private static BigDecimal bd(String value) {
        return new BigDecimal(value);
    }
}
