package Model;

import java.io.Serializable;

public interface IStock extends Serializable {


    /*
    modifies: this
    effects: adds the amount of money to the amount initially spent
             when adding the stock
     */
    void updateInitialSpending(double money);

    /*
    effects: returns the amount initially spent on this stock
     */
    double getInitialSpending();


    /*
    effects: returns the difference between the price and the price
             of the stock when first bought
     */
    double getDifference();


    /*
    effects: returns percentage increase/decrease from when the stock
             was initially bought
     */
    double getPercentageChange();


    /*
    effects: returns the unique symbol of the stock
     */
    String getSymbol();


    /*
    effects: returns the set name of the stock
     */
    String getName();


    /*
    effects: returns the amount of shares in the stock
     */
    int getNumber();


    /*
    effects: returns the value per share of the stock
     */
    double getPrice();


    /*
    effects: returns the overall value of the stocks,
             the price*(amount of shares)
     */
    double getValue();


    /*
    requires: name!=null
    modifies: this
    effects: set the name of the stock to x
     */
    void setName(String x);


    /*
    requires: number>0
    modifies:this
    effects: sets the amount of shares in the stock to number
     */
    void setNumber(int number);


    /*
    requires: price!=null
    modifies:this
    effects: sets the price per share of the stock to v
     */
    void setPrice(double v);


    /*
    effects: returns the highest value the stock has ever been
     */
    double getStockHigh();

    /*
    effects: returns the lowest value the stock has ever been
     */
    double getStockLow();

}
