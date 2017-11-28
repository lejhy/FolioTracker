package Model;

import sun.jvm.hotspot.utilities.Assert;

public class Stock implements IStock {
    private String symbol;
    private String name;
    private int number;
    private double price;
    private double difference;
    private double percentageChange;
    private double high;
    private double low;
    private double initialSpending;

    /*
    Assertion checking for precondition
     */
    Stock(String symbol, String name, int number, double price) {
        assert(symbol != null);
        assert(name != null);

        this.symbol = symbol;
        this.name = name;
        this.number = number;
        this.price = price;
        this.high = price;
        this.low = price;
    }

    @Override
    public void updateInitialSpending(double money){
        initialSpending += money;
    }

    @Override
    public double getInitialSpending(){
        return initialSpending;
    }

    @Override
    public double getDifference() {
        return difference;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public double getPrice() { return price; }

    @Override
    public double getValue() {
        return price * number;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setNumber(int number) {
        this.number = number;
    }

    /*
    Assertion checking that price is positive
     */
    @Override
    public void setPrice(double price) {
        assert (price > 0) : price;

        if(price != this.price) {
            difference = price - this.price;
            percentageChange = difference / this.price * 100;
        }
        this.price = price;
        if(price > high)
            high = price;
        else if(price < low)
            low = price;
    }

    @Override
    public double getPercentageChange() { return percentageChange; }

    @Override
    public double getStockHigh() {
        return high;
    }

    @Override
    public double getStockLow() {
        return low;
    }

    /*
    Assertion checking for reflective equals contract
     */
    @Override
    public boolean equals(Object o) {
        if (o != null) {
            if (o instanceof Stock) {
                Stock stock = (Stock) o;
                if (this.symbol.equals(stock.name) &&
                        this.name.equals(stock.name) &&
                        this.number == stock.number &&
                        this.price == stock.price &&
                        this.difference == stock.difference &&
                        this.percentageChange == stock.percentageChange &&
                        this.high == stock.high &&
                        this.low == stock.low &&
                        this.initialSpending == stock.initialSpending) {

                    assert o.equals(this): "this: " + this + " o: " + o;
                    return true;
                }
            }
        }
        return false;
    }
}
