import java.util.List;
import java.util.Observer;
import java.util.Vector;

public interface IFolio {

    public List<IStock> getStocks();

    public String getName();

    public void addStock(String ticker, int number);

    public void addObserver(Observer o);
}
