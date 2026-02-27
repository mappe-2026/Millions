package edu.ntnu.idatt2003.gruppe50.transaction;

import edu.ntnu.idatt2003.gruppe50.calculator.PurchaseCalculator;
import edu.ntnu.idatt2003.gruppe50.calculator.TransactionCalculator;
import edu.ntnu.idatt2003.gruppe50.model.Player;
import edu.ntnu.idatt2003.gruppe50.model.Share;
import edu.ntnu.idatt2003.gruppe50.model.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    private Share share;
    private TransactionCalculator calc;

    private static class TestTransaction extends Transaction {

        public TestTransaction(Share share, int week, TransactionCalculator calculator) {
            super(share, week, calculator);
        }

        @Override
        public void commit(Player player) {
        }
    }

    @BeforeEach
    void setup() {
        Stock stock = new Stock("KOG", "Kongsberg Gruppen", bd("310"));
        share = new Share(stock, bd("5"), bd("310"));
        calc = new PurchaseCalculator(share);
    }

    @Test
    void constructor_throwsException_whenShareIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new TestTransaction(null, 1, calc));
    }

    @Test
    void constructor_throwsException_whenCalculatorIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new TestTransaction(share, 1, null));
    }

    private static BigDecimal bd(String value) {
        return new BigDecimal(value);
    }
}