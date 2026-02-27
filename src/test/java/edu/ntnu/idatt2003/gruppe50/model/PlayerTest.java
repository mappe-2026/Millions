package edu.ntnu.idatt2003.gruppe50.model;

import edu.ntnu.idatt2003.gruppe50.transaction.TransactionArchive;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private Player player;

    @BeforeEach
    void setup() {
        player = new Player("test", bd("100"));
    }

    @Test
    void constructor_nullName_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new Player(null, bd("100")));
    }

    @Test
    void constructor_blankName_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new Player(" ", bd("100")));
    }

    @Test
    void constructor_negativeMoney_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new Player("test", bd("-100")));
    }

    @Test
    void constructor_nullMoney_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new Player("test", null));
    }

    @Test
    void getName_returnsPlayerName() {
        assertEquals("test", player.getName());
    }

    @Test
    void getMoney_returnsCurrentBalance() {
        assertEquals(bd("100"), player.getMoney());
    }

    @Test
    void addMoney_increasesBalanceByGivenAmount() {
        player.addMoney(bd("10"));
        assertEquals(bd("110"), player.getMoney());
    }

    @Test
    void withdrawMoney_decreasesBalanceByGivenAmount() {
        player.withdrawMoney(bd("10"));
        assertEquals(bd("90"), player.getMoney());
    }

    @Test
    void getPortfolio_returnsPortfolio() {
        assertInstanceOf(Portfolio.class, player.getPortfolio());
    }

    @Test
    void getTransactionArchive_returnsTransactionArchive() {
        assertInstanceOf(TransactionArchive.class, player.getTransactionArchive());
    }

    //Helper method
    private static BigDecimal bd(String value) {
        return new BigDecimal(value);
    }

}
