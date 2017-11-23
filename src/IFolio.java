
import java.io.Serializable;
import java.util.List;
import java.util.Observer;
import java.util.Vector;

public interface IFolio extends Serializable,Observer{

    public List<IStock> getStocks();

    public String getName();

    public boolean addStock(String ticker, int number);

    void addObserver(Observer o);

    public double getTotalStockValue();

    void refresh();
}
