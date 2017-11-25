package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class FolioTest {

    Folio folioTest;
    Folio folioStrath;

    @BeforeEach
    void setUp() {
        folioTest = new Folio("folioTest", new TestServer());
        folioStrath = new Folio("folioStrath");
    }

    @Test
    void getName() {
        assertEquals("folioTest", folioTest.getName());
    }

    @Test
    void addStock() {
        assertFalse(folioTest.addStock("ABC",-1));
        assertFalse(folioTest.addStock("ABC",0));
        assertTrue(folioTest.addStock("ABC", 1));
        assertFalse(folioTest.addStock("123", 1));
    }

    @Test
    void checkTicker() {
        assertEquals(IFolio.ticker.VALID, folioTest.checkTicker("ABC"));
        folioTest.addStock("ABC", 1);
        assertEquals(IFolio.ticker.EXISTS, folioTest.checkTicker("ABC"));
        assertEquals(IFolio.ticker.INVALID, folioTest.checkTicker("123"));
        assertEquals(IFolio.ticker.ERROR, folioTest.checkTicker("data_error"));
    }

    @Test
    void buyStock() {
        assertFalse(folioTest.buyStock("ABC", 1));
        folioTest.addStock("ABC", 1);
        assertTrue(folioTest.buyStock("ABC", 1));
        assertFalse(folioTest.buyStock("123", 1));
    }

    @Test
    void sellStock() {
        assertFalse(folioTest.sellStock("ABC", 1));
        folioTest.addStock("ABC", 2);
        assertFalse(folioTest.sellStock("123", 1));
        assertTrue(folioTest.sellStock("ABC", 1));
        assertTrue(folioTest.sellStock("ABC", 1));
        assertFalse(folioTest.sellStock("ABC", 1));
    }

    @Test
    void refresh() {
        folioTest.addStock("ABC", 1);
        double initialPrice = folioTest.getStocks().get(0).getPrice();
        folioTest.refresh();
        assertNotEquals(initialPrice, folioTest.getStocks().get(0).getPrice());
        folioTest.getStocks().add(new Stock("123", "123", 1, 1));
        initialPrice = folioTest.getStocks().get(1).getPrice();
        folioTest.refresh();
        assertEquals(initialPrice, folioTest.getStocks().get(1).getPrice());
    }

    @Test
    void autoRefresh() {
        folioTest.autoRefreshStart();
        assertTrue(folioTest.isAutoUpdating());
        folioTest.autoRefreshStop();
        assertFalse(folioTest.isAutoUpdating());
    }

    @Test
    void delete() {
        folioTest.autoRefreshStart();
        folioTest.delete();
        assertFalse(folioTest.isAutoUpdating());
    }

    @Test
    void getTotalStockValue() {
        folioTest.addStock("ABC",1);
        assertEquals(folioTest.getStocks().get(0).getValue(), folioTest.getTotalStockValue());
    }



    @Test
    void readWriteObject() throws ClassNotFoundException, IOException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(folioTest);
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(byteOut.toByteArray()));
        Object result = in.readObject();
        assertEquals(folioTest, result);
    }

    @Test
    void equalsNull () {
        assertFalse(folioTest.equals(null));
    }
}