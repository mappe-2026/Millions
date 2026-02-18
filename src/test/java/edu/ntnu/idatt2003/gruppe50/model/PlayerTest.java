package edu.ntnu.idatt2003.gruppe50.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlayerTest {
    private Player player;

    @BeforeEach
    void setup() {
        player = new Player("test", new BigDecimal(100));
    }

    @Test
    void constructor_nullName_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new Player(null, new BigDecimal(100)));
    }

    @Test
    void constructor_blankName_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new Player(" ", new BigDecimal(100)));
    }

    @Test
    void constructor_negativeMoney_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new Player("test", new BigDecimal(-100)));
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
        assertEquals(new BigDecimal(100), player.getMoney());
    }

    @Test
    void addMoney_increasesBalanceByGivenAmount() {
        player.addMoney(new BigDecimal(10));
        assertEquals(new BigDecimal(110), player.getMoney());
    }

    @Test
    void withdrawMoney_decreasesBalanceByGivenAmount() {
        player.withdrawMoney(new BigDecimal(10));
        assertEquals(new BigDecimal(90), player.getMoney());
    }

    @Test
    void getPortfolio_returnsPlayerPortfolio() {
        Player player2 = new Player("test", new BigDecimal(100));
        assertEquals(player2.getPortfolio(), player.getPortfolio());
    }

    @Test
    void getTransactionArchive_returnsPlayerTransactionArchive() {
        Player player2 = new Player("test", new BigDecimal(100));
        assertEquals(player2.getTransactionArchive(), player.getTransactionArchive());
    }
}
