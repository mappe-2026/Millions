package edu.ntnu.idatt2003.gruppe50.transaction;

import edu.ntnu.idatt2003.gruppe50.calculator.SaleCalculator;
import edu.ntnu.idatt2003.gruppe50.model.Player;
import edu.ntnu.idatt2003.gruppe50.model.Share;

import java.math.BigDecimal;

/**
 * Represents a transaction where a player sells shares.
 * <p>
 *     A buy transaction adds the sale price to the player's balance
 *     and removes the shares from the player's portfolio when commited.
 * </p>
 */
public class Sale extends Transaction{

    public Sale(Share share, int week) {
        super(share, week, new SaleCalculator(share));
    }


    @Override
    public void commit(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        if (isCommitted()) {
            return;
        }
        if (!player.getPortfolio().contains(getShare())) {
            throw new IllegalArgumentException("Cannot sell a share you don't own");
        }

        BigDecimal total = getCalculator().calculateTotal();

        player.addMoney(total);
        player.getPortfolio().removeShare(getShare());
        player.getTransactionArchive().add(this);
        markCommitted();
    }
}
