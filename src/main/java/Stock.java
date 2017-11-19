package main.java;

public class Stock {
    String symbol;
    String name;
    int number;
    double price;

    public Stock(String symbol, String name, int number, double price) {
        this.symbol = symbol;
        this.name = name;
        this.number = number;
        this.price = price;
    }

    public double getValue() {
        return price * number;
    }
}
