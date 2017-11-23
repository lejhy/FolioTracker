
public class Stock implements IStock {
    private String symbol;
    private String name;
    private int number;
    private double price;
    private double difference;

    public Stock(String symbol, String name, int number, double price) {
        this.symbol = symbol;
        this.name = name;
        this.number = number;
        this.price = price;
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

    @Override
    public void setPrice(double price) {
        if(price != this.price)
            difference = price - this.price;
        this.price = price;
    }
}
