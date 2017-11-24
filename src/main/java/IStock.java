import java.io.Serializable;

public interface IStock extends Serializable {

    double getDifference();

     String getSymbol();

     String getName();

     int getNumber();

     double getPrice();

     double getValue();

     void setName(String name);

     void setNumber(int number);

     void setPrice(double price);

     double getStockHigh();

     double getStockLow();


}
