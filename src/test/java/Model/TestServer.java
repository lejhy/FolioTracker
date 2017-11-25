package Model;

import java.util.Random;

class TestServer implements IServer{
    private static String[] symbols = {"ABC","DEF","HIJ"};

    @Override
    public String getLastValue(String ticker)
            throws WebsiteDataException, NoSuchTickerException{
        if (ticker.equals("data_error")) throw new WebsiteDataException();
        Random rng = new Random();
        for (String symbol : symbols) {
            if (ticker.equals(symbol))
                return String.valueOf(rng.nextInt(1000));
        }
        throw new NoSuchTickerException();
    }
}
