package edu.ntnu.idatt2003.gruppe50.transaction;

import edu.ntnu.idatt2003.gruppe50.calculator.TransactionCalculator;
import edu.ntnu.idatt2003.gruppe50.model.Share;


/**
 * Represents a financial transaction in the game.
 * <p>
 * A transaction is associated with a specific share and week.
 * Subclasses define how the transaction is committed.
 * A transaction can only be committed once.
 * </p>
 */
public abstract class Transaction {
    private final Share share;
    private final int week;
    private final TransactionCalculator calculator;
    private boolean committed;

    /**
     * Creates a new {@code Transaction}.
     *
     * @param share the share involved in the transaction
     * @param week the week number when the transaction occurred
     * @param calculator the calculator used in the transaction
     * @throws IllegalArgumentException if {@code share} or {@code calculator}
     * is {@code null}
     */
    protected Transaction(Share share, int week, TransactionCalculator calculator) {
        if (share == null) {
            throw new IllegalArgumentException("Share cannot be null");
        }
        if (calculator == null) {
            throw new IllegalArgumentException("Calculator cannot be null");
        }
        this.share = share;
        this.week = week;
        this.calculator = calculator;
        this.committed = false;
    }

    /**
     * Returns the share associated with this transaction.
     *
     * @return the share involved in the transaction
     */
    public Share getShare() {
        return share;
    }

    /**
     * Returns the week associated with this transaction.
     *
     * @return the week involved in the transaction
     */
    public int getWeek() {
        return week;
    }

    /**
     * Returns the calculator associated with this transaction.
     *
     * @return the calculator involved in the transaction.
     */
    public TransactionCalculator getCalculator() {
        return calculator;
    }

    /**
     * Returns whether this transaction has been committed.
     *
     * @return {@code true} if the transaction has been committed,
     *         {@code false} otherwise
     */
    public boolean isCommitted() {
        return committed;
    }

    /**
     * Commits (executes) this transaction for the given player.
     * <p>
     * Subclasses must implement this method and apply the transaction's
     * effects to the given player.
     *
     * @param player the player for whom the transaction is committed
     */
    public abstract void commit(Player player);
}
