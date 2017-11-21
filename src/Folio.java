import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Folio implements IFolio {

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

}
