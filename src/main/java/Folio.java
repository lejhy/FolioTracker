import java.util.ArrayList;
import java.util.List;

public class Folio implements IFolio {

    String name;
    List<IStock> stocks;

    public Folio(String name) {
        this.name = name;
        stocks = new ArrayList<>();
    }

    @Override
    public List<IStock> getStock() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }
}
