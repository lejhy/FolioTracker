package java.Model;

import Model.Stock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StockTest {

    @Test
    void testPercentage() {
        Stock stock = new Stock("test", "test", 1, 100.0);
        stock.setPrice(90.0);
        assertEquals(-10, stock.getPercentageChange());
    }

}