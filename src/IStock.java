import java.io.Serializable;

public interface IStock extends Serializable {

    String getSymbol();

    String getName();

    int getNumber();

    double getPrice();

    double getValue();
}
