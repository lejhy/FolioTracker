package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Folio extends Observable implements IFolio {

    private String name;
    private List<IStock> stocks;
    private IAutoUpdate autoUpdate;


    public Folio(String name) {
        this.name = name;
        stocks = new ArrayList<>();
        autoUpdate = new AutoUpdate(this::refresh, 5000);
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
        return Double.parseDouble(TestServer.getLastValue(ticker));
        //return Double.parseDouble(Model.StrathQuoteServer.getLastValue(ticker));
    }

    @Override
    public boolean addStock(String ticker, int number) {
        try {
            double price = getSharePrice(ticker);
            IStock stock = new Stock(ticker, ticker, number, price);
            stocks.add(stock);
            stock.updateInitialSpending(price * number);
            setChanged();
            notifyObservers("Add");
            return true;
        } catch (WebsiteDataException | NoSuchTickerException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public IFolio.ticker checkTicker(String ticker) {
        if (alreadyExists(ticker)) {
            return IFolio.ticker.EXISTS;
        } else {
            try {
                getSharePrice(ticker);
                return IFolio.ticker.VALID;
            } catch (NoSuchTickerException e) {
                return IFolio.ticker.INVALID;
            } catch (WebsiteDataException e) {
                return IFolio.ticker.ERROR;
            }
        }
    }



    @Override
    public boolean buyStock(String ticker, int number) {
        for(IStock s : stocks) {
            if(s.getSymbol().equals(ticker)) {
                s.setNumber( s.getNumber() + number );
                s.updateInitialSpending(s.getPrice() * number);
                setChanged();
                notifyObservers("Buy");
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean sellStocks(String ticker, int value) {
        for(IStock s : stocks) {
            if(s.getSymbol().equals(ticker)) {
                if(value>s.getNumber()) return false;
                else {
                    if(value==s.getNumber()) {
                        stocks.remove(s);
                        s.updateInitialSpending(- s.getPrice() * value);
                        setChanged();
                        notifyObservers("Manual");
                        return true;
                    }
                    else {
                        s.setNumber(s.getNumber()-value);
                        s.updateInitialSpending(- s.getPrice() * value);
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
    public boolean sellStock(String ticker, int number) {
        return false;
    }

    @Override
    public boolean alreadyExists(String ticker) {
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
                double newPrice = getSharePrice(stock.getSymbol());
                stock.setPrice(newPrice);
            } catch (WebsiteDataException | NoSuchTickerException e) {
                e.printStackTrace();
            }
        }
        setChanged();
        notifyObservers("Refresh");
    }

    @Override
    public double getTotalStockValue() {

        double totalStockValue = 0.0;
        for(IStock stock : stocks) {
            totalStockValue += stock.getValue();
        }
        return totalStockValue;
    }



    public void autoRefreshStart() { autoUpdate.start(); }

    public void autoRefreshStop() { autoUpdate.stop(); }

    @Override
    public void delete() {
        autoUpdate.stop();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(name);
        out.writeObject(stocks);
        out.writeBoolean(autoUpdate.isRunning());
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        name = (String)in.readObject();
        stocks = (List<IStock>) in.readObject();
        autoUpdate = new AutoUpdate(this::refresh, 5000);
        if (in.readBoolean()) autoUpdate.start();
    }
}
