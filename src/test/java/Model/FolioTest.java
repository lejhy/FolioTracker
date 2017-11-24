package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FolioTest {

    Folio folio;

    @BeforeEach
    void setUp() {
        folio = new Folio("folio");
    }

    @Test
    void addStock() {
        assertFalse(folio.addStock("ABC",-1));
        assertFalse(folio.addStock("ABC",0));
        assertTrue(folio.addStock("ABC", 1));
        assertFalse(folio.addStock("123", 1));
    }

    @Test
    void checkTicker() {
        assertEquals(IFolio.ticker.VALID, folio.checkTicker("ABC"));
        folio.addStock("ABC", 1);
        assertEquals(IFolio.ticker.EXISTS, folio.checkTicker("ABC"));
        assertEquals(IFolio.ticker.INVALID, folio.checkTicker("123"));
        screwUpProxy();
        assertEquals(IFolio.ticker.ERROR, folio.checkTicker("123"));
        restoreProxy();
    }

    @Test
    void buyStock() {
        assertFalse(folio.buyStock("ABC", 1));
        folio.addStock("ABC", 1);
        assertTrue(folio.buyStock("ABC", 1));
    }

    @Test
    void sellStock() {
        assertFalse(folio.sellStock("ABC", 1));
        folio.addStock("ABC", 2);
        assertTrue(folio.sellStock("ABC", 1));
        assertTrue(folio.sellStock("ABC", 1));
        assertFalse(folio.sellStock("ABC", 1));
    }

    @Test
    void alreadyExists() {
        assertFalse(folio.alreadyExists("ABC"));
        folio.addStock("ABC", 1);
        assertTrue(folio.alreadyExists("ABC"));
    }

    @Test
    void refresh() {
        //TODO
    }

    @Test
    void getTotalStockValue() {
        folio.addStock("ABC",1);
        assertEquals(folio.getStocks().get(0).getValue(), folio.getTotalStockValue());
    }

    @Test
    void readWriteObject() throws ClassNotFoundException, IOException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(folio);
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(byteOut.toByteArray()));
        Object result = in.readObject();
        assertEquals(folio, result);
    }

    void screwUpProxy() {
        System.getProperties().put("proxySet", "true");
        System.getProperties().put("proxyHost", "www.example.com");
        System.getProperties().put("proxyPort", "123");
    }

    void restoreProxy() {
        System.getProperties().put("proxySet", "false");
    }
}