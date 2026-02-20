package edu.ntnu.idatt2003.gruppe50.model;

import edu.ntnu.idatt2003.gruppe50.transaction.TransactionArchive;

import java.math.BigDecimal;

/**
 * Represents a player in the game, holding information about the player's progress.
 * <p>
 *      The Player holds a given amount of money and has a
 *      portfolio {@link Portfolio} of shares with a record
 *      of transactions {@link TransactionArchive} of transactions.
 * </p>
 */
public class Player {
    private final String name;
    private final BigDecimal startingMoney;
    private BigDecimal money;
    private final Portfolio portfolio;
    private final TransactionArchive transactionArchive;

    /**
     * Creates a new {@code Player} with the given name and starting money of given amount.
     *
     * @param name The player's name
     * @param startingMoney The amount of money the player starts with
     * @throws IllegalArgumentException If any argument is null or invalid
     */
    public Player(String name, BigDecimal startingMoney) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if (startingMoney == null) {
            throw new IllegalArgumentException("Starting money cannot be null");
        }
        if (startingMoney.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Starting money cannot be negative");
        }
        this.name = name;
        this.startingMoney = startingMoney;
        this.money = startingMoney;
        this.portfolio = new Portfolio();
        this.transactionArchive = new TransactionArchive();
    }

    /**
     * Returns the player's name.
     *
     * @return the player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the player's current balance.
     *
     * @return the players amount of money as a {@link BigDecimal}
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * Adds the given amount to the player's balance.
     *
     * @param amount the amount to add
     */
    public void addMoney(BigDecimal amount) {
        money = money.add(amount);
    }

    /**
     * Subtracts the given amount from the player's balance.
     *
     * @param amount the amount to subtract
     */
    public void withdrawMoney(BigDecimal amount) {
        money = money.subtract(amount);
    }

    /**
     * Returns the player's portfolio, containing the owned shares.
     *
     * @return the player's portfolio.
     */
    public Portfolio getPortfolio() {
        return portfolio;
    }

    /**
     * Returns the player's transaction archive, containing all previous transactions.
     *
     * @return the player's transaction archive
     */
    public TransactionArchive getTransactionArchive() {
        return transactionArchive;
    }
}
