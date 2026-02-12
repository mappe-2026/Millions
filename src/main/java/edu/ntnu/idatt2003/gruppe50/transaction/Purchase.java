package edu.ntnu.idatt2003.gruppe50.transaction;

import edu.ntnu.idatt2003.gruppe50.model.Share;

/**
 * Represents a transaction where a player buys shares.
 * <p>
 *     A buy transaction deducts the purchase cost from the player's balance
 *     and adds the shares to the player's portfolio when commited.
 * </p>
 */
public class Purchase extends Transaction{

    public Purchase(Share share, int week) {
        super(share, week, new PurchaseCalculator);
    }

    @Override
    public void commit(Player player) {
        //kommer senere
    }
}
