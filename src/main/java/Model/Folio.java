package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;

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
        //return Double.parseDouble(TestServer.getLastValue(ticker));
        return Double.parseDouble(Model.StrathQuoteServer.getLastValue(ticker));
    }


    /*
    Assertion here for checking preconditions and checking list excluding new row is still the same
     */
    @Override
    public boolean addStock(String ticker, int number) {
        if (number > 0) {
            try {
                assert(ticker!=null);
                List<IStock> clone= new ArrayList<>(stocks);


                double price = getSharePrice(ticker);
                IStock stock = new Stock(ticker, ticker, number, price);
                stocks.add(stock);
                stock.updateInitialSpending(price * number);
                setChanged();
                notifyObservers("Add");
                assert(postCheck(clone));
                return true;
            } catch (WebsiteDataException | NoSuchTickerException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    /*
    Assertion check for add
     */
    private boolean postCheck(List<IStock> clone) {
        for(int i=0;i<clone.size();i++) {
            if(clone.get(i)!=stocks.get(i)) {
                return false;
            }
        }
        if(clone.size()<stocks.size()) {
            return true;
        }
        else
            return false;
    }


    /*
    Assertion here to check precondition
     */
    @Override
    public IFolio.ticker checkTicker(String ticker) {
        assert(ticker!=null);
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



    /*
    Assertion here to check that size of list doesn't change
     */
    @Override
    public boolean buyStock(String ticker, int number) {
        assert(ticker!=null && number>0);
        int i = stocks.size();
        for(IStock s : stocks) {
            if(s.getSymbol().equals(ticker)) {
                s.setNumber( s.getNumber() + number );
                s.updateInitialSpending(s.getPrice() * number);
                setChanged();
                notifyObservers("Buy");
                assert(stocks.size()==i);
                return true;
            }
        }
        return false;
    }

    /*
    Assertion here to check that the list isn't altered incorrectly
     */

    @Override
    public boolean sellStock(String ticker, int value) {
        assert(ticker!=null && value>0);
        int i=stocks.size();
        for(IStock s : stocks) {
            if(s.getSymbol().equals(ticker)) {
                if(value>s.getNumber()) return false;
                else {
                    if(value==s.getNumber()) {
                        stocks.remove(s);
                        s.updateInitialSpending(- s.getPrice() * value);
                        setChanged();
                        notifyObservers("Manual");
                        assert(checkSellPost(i,s));
                        return true;
                    }
                    else {
                        s.setNumber(s.getNumber()-value);
                        s.updateInitialSpending(- s.getPrice() * value);
                        setChanged();
                        notifyObservers("Manual");
                        assert(stocks.size()==i);
                        return true;
                    }

                }

            }
        }
        System.out.println("Shouldn't be here,must not be a valid ticker");
        return false;
    }

    /*
    Assertion check for sellStock
     */
    private boolean checkSellPost(int i, IStock s) {
        if(stocks.size()>=i || stocks.contains(s))
            return false;
        else
            return true;
    }

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

    @Override
    public boolean equals(Object o) {
        if (o != null) {
            if (o instanceof Folio) {
                Folio folio = (Folio) o;
                if (this.name.equals(folio.name) && this.stocks.equals(folio.stocks) && this.autoUpdate.equals(folio.autoUpdate)) {
                    return true;
                }
            }
        }
        return false;
    }
}
