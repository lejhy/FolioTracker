
import java.io.Serializable;
import java.util.List;
import java.util.Observer;
import java.util.Vector;

public interface IFolio extends Serializable{

    List<IStock> getStocks();

    String getName();

    boolean addStock(String ticker, int number);

    void addObserver(Observer o);

    void refresh();
}
