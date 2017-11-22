import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FolioTest {

    Folio folio ;
    String name ;
    List<IStock> stocks ;
    String ticker ;

    @BeforeEach
    void setUp() {
        folio = new Folio("folio");
        name = "folio";
        stocks = new ArrayList<>();
        ticker = "APPL";
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getStocks() {

    }

    @Test
    void getName() {
    }

    @Test
    void addStock() {
        assertTrue(folio.addStock(ticker,5));

        //assertTrue(folio.getStocks().contains());
        //String symbol, String name, int number, double price
        int checkNumber=-1;
        List<IStock> stock = folio.getStocks();
        for(IStock currentStock:stock){
            if(currentStock.getSymbol().equals("APPL"))
                checkNumber= currentStock.getNumber();
        }
        assert(checkNumber==5);
        assertFalse(folio.addStock("faketicker", 3));
    }


}