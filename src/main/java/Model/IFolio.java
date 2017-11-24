package Model;

import java.io.Serializable;
import java.util.List;
import java.util.Observer;

public interface IFolio extends Serializable{

    enum ticker { EXISTS, VALID, INVALID, ERROR }

    List<IStock> getStocks();

    String getName();

    boolean addStock(String ticker, int number);

    void addObserver(Observer o);

    double getTotalStockValue();

    void refresh();

    ticker checkTicker(String ticker);

    boolean alreadyExists(String tickerSymbol);

    boolean buyStock(String tickerSymbol, int numberOfShares);

    void autoRefreshStart();

    void autoRefreshStop();

    boolean sellStocks(String name, int value);

    void delete();

}
