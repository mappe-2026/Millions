package edu.ntnu.idatt2003.gruppe50.observer;

import edu.ntnu.idatt2003.gruppe50.model.Exchange;
import edu.ntnu.idatt2003.gruppe50.model.Player;
import edu.ntnu.idatt2003.gruppe50.model.Stock;
import edu.ntnu.idatt2003.gruppe50.transaction.TransactionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExchangeObserverTest {

    private Exchange exchange;
    private Stock aapl;
    private Stock kog;
    private Stock eqnr;
    private TestObserver observer;
    private Player player;

    @BeforeEach
    void setUp() {
        aapl = new Stock("AAPL", "Apple", bd(100));
        kog = new Stock("KOG", "Kongsberg", bd(200));
        eqnr = new Stock("EQNR", "Equinor", bd(50));
        exchange = new Exchange("test", List.of(aapl, kog, eqnr), new TransactionFactory());

        observer = new TestObserver();
        exchange.addObserver(observer);

        player = new Player("Test", bd(2000));
    }

    @Test
    void advance_notifiesObservers() {
        exchange.advance();
        assertEquals(1, observer.count);
    }

    @Test
    void buy_notifiesObservers() {
        exchange.buy("AAPL", bd(1), player);
        assertEquals(1, observer.count);
    }

    @Test
    void sell_notifiesObservers() {
        exchange.buy("AAPL", bd(1), player);
        int countBeforeSell = observer.count;  // lagre count før sell

        exchange.sell(player.getPortfolio().getShares().getFirst(), player);

        assertEquals(countBeforeSell + 1, observer.count);  // kun sell sitt varsel
    }

    @Test
    void removeObserver_observerNotNotified() {
        exchange.removeObserver(observer);

        exchange.advance();

        assertEquals(0, observer.count);
    }


    private static class TestObserver implements Observer {
        int count = 0;

        @Override
        public void update() {
            count++;
        }
    }

    private BigDecimal bd(double num) {
        return BigDecimal.valueOf(num);
    }
}
