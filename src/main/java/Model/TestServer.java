package Model;

import java.util.Random;

class TestServer {
    private static String[] symbols = {"ABC","DEF","HIJ"};

    static String getLastValue(String ticker) throws NoSuchTickerException, WebsiteDataException {
        Random rng = new Random();
        for (String symbol : symbols) {
            if (ticker.equals(symbol))
                return String.valueOf(rng.nextInt(1000));
        }
        throw new NoSuchTickerException();
    }
}
