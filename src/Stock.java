import java.util.Observable;
import java.util.Observer;

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

    public void update() {
        try {
            String value = StrathQuoteServer.getLastValue(symbol);
            price = Double.parseDouble(value);
        } catch (WebsiteDataException e) {
            e.printStackTrace();
        } catch (NoSuchTickerException e) {
            e.printStackTrace();
        }
    }

}
