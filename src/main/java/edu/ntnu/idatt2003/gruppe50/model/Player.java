package edu.ntnu.idatt2003.gruppe50.model;

import java.math.BigDecimal;

public class Player {
    private final String name;
    private final BigDecimal startingMoney;
    private BigDecimal money;
    private final Portfolio portfolio = null;
    private final TransactionArchive transactionArchive = null;

    public Player(String name, BigDecimal startingMoney) {
        this.name = name;
        this.startingMoney = startingMoney;
        this.money = startingMoney;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void addMoney(BigDecimal amount) {
        money = money.add(amount);
    }

    public void withdrawMoney(BigDecimal amount) {
        money = money.subtract(amount);
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public TransactionArchive getTransactionArchive() {
        return transactionArchive;
    }
}
