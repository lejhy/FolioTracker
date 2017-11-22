import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Folio extends Observable implements IFolio {

    private String name;
    private List<IStock> stocks;
    private double totalStockValue;

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
    public boolean addStock(String ticker, int number) {
        try {
            double price = Double.parseDouble(StrathQuoteServer.getLastValue(ticker));
            Stock stock = new Stock(ticker, ticker, number, price);
            stocks.add(stock);
            totalStockValue += stock.getValue();
            setChanged();
            notifyObservers();
            return true;
        } catch (WebsiteDataException e) {
            e.printStackTrace();
            return false;
        } catch (NoSuchTickerException e) {
            e.printStackTrace();
            return false;
        }
    }


    public double getTotalStockValue() {
        return totalStockValue;
    }
}
