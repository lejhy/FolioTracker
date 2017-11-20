public class Stock implements IStock {
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

    @Override
    public String getSymbol() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getAmount() {
        return 0;
    }

    public double getValue() {
        return price * number;
    }
}
