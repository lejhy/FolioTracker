public class Stock implements IStock {
    private String symbol;
    private String name;
    private int number;
    private double price;

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
    public int getNumber() {
        return number;
    }

    @Override
    public double getPrice() { return price; }

    @Override
    public double getValue() {
        return price * number;
    }
}
