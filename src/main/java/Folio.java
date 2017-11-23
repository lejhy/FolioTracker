import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Folio extends Observable implements IFolio {

    private String name;
    private List<IStock> stocks;
    private double totalStockValue = 0.0;

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

    private double getSharePrice(String ticker) throws NoSuchTickerException, WebsiteDataException {
        return Double.parseDouble(StrathQuoteServer.getLastValue(ticker));
    }

    @Override
    public boolean addStock(String ticker, int number) {
        try {
            double price = getSharePrice(ticker);
            Stock stock = new Stock(ticker, ticker, number, price);
            stocks.add(stock);
            totalStockValue += (number*price);
            setChanged();
            notifyObservers("Add");
            return true;
        } catch (WebsiteDataException | NoSuchTickerException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean isStock(String ticker) {
        try {
            StrathQuoteServer.getLastValue(ticker);
            return true;
        } catch (WebsiteDataException | NoSuchTickerException e) {
            e.printStackTrace();
            return false;
        }
    }


    // TODO: Should probably be changed to use indexes instead of looping through the list
    public boolean sameStockAdding(String ticker, int amount) {
        for(IStock s : stocks) {
            if(s.getSymbol().equals(ticker)) {
                s.setNumber(s.getNumber()+amount);
                totalStockValue+= amount*s.getValue();
                setChanged();
                notifyObservers("Manual");
                return true;
            }
        }
        return false;
    }

    // TODO: Same as sameStockAdding
    @Override
    public boolean removeStocks(String ticker, int value) {
        for(IStock s : stocks) {
            if(s.getSymbol().equals(ticker)) {
                if(value>s.getNumber()) return false;
                else {
                    if(value==s.getNumber()) {
                        stocks.remove(s);
                        calcTotalStockValue();
                        setChanged();
                        notifyObservers("Manual");
                        return true;
                    }
                    else {
                        s.setNumber(s.getNumber()-value);
                        totalStockValue -= value*s.getValue();
                        setChanged();
                        notifyObservers("Manual");
                        return true;
                    }
                }
            }

        }
        System.out.println("Shouldn't be here");
        return false;
    }

    @Override
    public boolean alreadyOwn(String ticker) {
        for(IStock s : stocks) {
            if(s.getSymbol().equals(ticker)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void refresh() {
        for(IStock stock : stocks) {
            try {
                double newPrice = Double.parseDouble(StrathQuoteServer.getLastValue(stock.getSymbol()));
                stock.setPrice(newPrice);
            } catch (WebsiteDataException e) {
                e.printStackTrace();
            } catch (NoSuchTickerException e) {
                e.printStackTrace();
            }
        }
        setChanged();
        notifyObservers("Manual");
    }


    public double getTotalStockValue() {
        return totalStockValue;
    }

    private void calcTotalStockValue() {
        totalStockValue=0;
        for(IStock s : stocks) {
            totalStockValue+= s.getValue()*s.getNumber();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        refresh();
    }
}
