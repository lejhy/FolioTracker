package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StockTest {

    Stock stock;

    @BeforeEach
    void setUp() {
        stock = new Stock("test", "test", 1, 100.0);
    }

    @Test
    void testPercentage() {
        stock.setPrice(90.0);
        assertEquals(-10, stock.getPercentageChange());
    }

    @Test
    void testDifference() {
        stock.setPrice(90.0);
        assertEquals(-10, stock.getDifference());
    }

    @Test
    void testInitilaSpenfing() {
        stock.updateInitialSpending(100);
        assertEquals(100, stock.getInitialSpending());
        stock.updateInitialSpending(100);
        assertEquals(200, stock.getInitialSpending());
    }

    @Test
    void testName() {
        stock.setName("testName");
        assertEquals("testName", stock.getName());
    }

    @Test
    void testStockHigh() {
        stock.setPrice(90.0);
        stock.setPrice(110.0);
        stock.setPrice(100.0);
        assertEquals(110.0, stock.getStockHigh());
    }

    @Test
    void testStockLow() {
        stock.setPrice(90.0);
        stock.setPrice(110.0);
        stock.setPrice(100.0);
        assertEquals(90.0, stock.getStockLow());
    }

    @Test
    void testGetters() {

    }


    @Test
    void equalsNull() {
        assertFalse(stock.equals(null));
    }

}