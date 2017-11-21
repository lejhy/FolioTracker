import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Vector;

public class Folio extends Observable implements IFolio {

    private String name;
    private List<IStock> stocks;

    public Folio(String name) {
        this.name = name;
        stocks = new ArrayList<>();
    }

    @Override
    public List<IStock> getStocks() {
        return stocks;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addStock(String ticker, int number) {
        Stock stock = new Stock(ticker, ticker, number, 0.0);
        stocks.add(stock);
        setChanged();
        notifyObservers();
    }

}
