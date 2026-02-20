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
    private Stock stock;
    private Share share;
    private Purchase purchase;
    private Player player;
    private Player poorPlayer;

    @BeforeEach
    void setup() {
        stock = new Stock("KOG", "Kongsberg Gruppen", new BigDecimal("330"));
        share = new Share(stock, new BigDecimal("5"), new BigDecimal("310"));
        purchase = new Purchase(share, 12);
        player = new Player("Test", new BigDecimal("20000"));
        poorPlayer = new Player("Test2", new BigDecimal("1"));
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
    void isCommitted_returnsTrue_afterPurchase_isComplete() {
        purchase.commit(player);
        assertTrue(purchase.isCommitted());
    }

    @Test
    void constructor_validArguments_createsShare() {
        Stock stock2 = new Stock("AAPL", "Apple", new BigDecimal("265"));
        Share share2 = new Share(stock2, new BigDecimal("3"), new BigDecimal("250"));
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
        purchase.commit(player);
        BigDecimal moneyBefore = player.getMoney();
        int shareBefore = player.getPortfolio().getShares().size();
        int transactionArchiveBefore = player.getTransactionArchive().getTransactions(12).size();

        purchase.commit(player);

        assertEquals(moneyBefore, player.getMoney());
        assertEquals(shareBefore, player.getPortfolio().getShares().size());
        assertEquals(transactionArchiveBefore, player.getTransactionArchive().getTransactions(12).size());
    }

    @Test
    void commit_updatesPlayerState_whenSuccessful() {
        BigDecimal moneyBefore = player.getMoney();
        int shareBefore = player.getPortfolio().getShares().size();
        int transactionArchiveBefore = player.getTransactionArchive().getTransactions(12).size();

        purchase.commit(player);

        BigDecimal moneyAfter = moneyBefore.subtract(purchase.getCalculator().calculateTotal());

        assertEquals(moneyAfter, player.getMoney());
        assertEquals(shareBefore + 1, player.getPortfolio().getShares().size());
        assertEquals(transactionArchiveBefore + 1, player.getTransactionArchive().getTransactions(12).size());
    }
}
