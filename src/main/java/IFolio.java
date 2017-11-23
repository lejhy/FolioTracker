
import java.io.Serializable;
import java.util.List;
import java.util.Observer;
import java.util.Vector;

public interface IFolio extends Serializable,Observer{

     List<IStock> getStocks();

     String getName();

     boolean addStock(String ticker, int number);

    void addObserver(Observer o);

    double getTotalStockValue();

    void refresh();
    
    boolean isStock(String ticker);

    boolean alreadyOwn(String tickerSymbol);

    boolean sameStockAdding(String tickerSymbol, int numberOfShares);
}
