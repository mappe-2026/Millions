package edu.ntnu.idatt2003.gruppe50.transaction;

import edu.ntnu.idatt2003.gruppe50.calculator.SaleCalculator;
import edu.ntnu.idatt2003.gruppe50.model.Share;

/**
 * Represents a transaction where a player sells shares.
 * <p>
 *     A buy transaction adds the sale price to the player's balance
 *     and removes the shares from the player's portfolio when commited.
 * </p>
 */
public class Sale extends Transaction{

    public Sale(Share share, int week) {
        if (week < 0) {
            throw new IllegalArgumentException("Week cannot be negative");
        }

        super(share, week, new SaleCalculator(share, share.getStock().getSalesPrice()));
    }


    @Override
    public void commit(Player player) {
        //kommer senere
    }
}
