package edu.ntnu.idatt2003.gruppe50.transaction;

import edu.ntnu.idatt2003.gruppe50.calculator.PurchaseCalculator;
import edu.ntnu.idatt2003.gruppe50.model.Player;
import edu.ntnu.idatt2003.gruppe50.model.Share;
import edu.ntnu.idatt2003.gruppe50.model.Stock;

import java.math.BigDecimal;

/**
 * Represents a transaction where a player buys shares.
 * <p>
 *     A buy transaction deducts the purchase cost from the player's balance
 *     and adds the shares to the player's portfolio when commited.
 * </p>
 */
public class Purchase extends Transaction{

    public Purchase(Share share, int week) {
        super(share, week, new PurchaseCalculator(share));
    }

    @Override
    public void commit(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        if (isCommitted()) {
            return;
        }

        BigDecimal total = getCalculator().calculateTotal();

        if (player.getMoney().compareTo(total) < 0) {
            throw new IllegalArgumentException("Player does not have enough money");
        }

        player.withdrawMoney(total);
        player.getPortfolio().addShare(getShare());
        player.getTransactionArchive().add(this);
        markCommitted();
    }
}
