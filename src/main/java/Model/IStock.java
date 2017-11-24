package Model;

import java.io.Serializable;

public interface IStock extends Serializable {

    void updateInitialSpending(double money);

 double getInitialSpending();

 double getDifference();

     double getPercentageChange();

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
