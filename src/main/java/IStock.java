import java.io.Serializable;

public interface IStock extends Serializable {

    double getDifference();

    public String getSymbol();

    public String getName();

    public int getNumber();

    public double getPrice();

    public double getValue();

    public void setName(String name);

    public void setNumber(int number);

    public void setPrice(double price);
}