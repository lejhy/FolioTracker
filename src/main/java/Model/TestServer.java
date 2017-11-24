package Model;

import java.util.Random;

public class TestServer {
    private static String[] symbols = {"ABC","DEF","HIJ"};

    public static String getLastValue(String ticker) throws NoSuchTickerException, WebsiteDataException {
        Random rng = new Random();
        for(int i=0;i<symbols.length;i++) {
            if(ticker.equals(symbols[i]))
                return String.valueOf(rng.nextInt(1000));
        }
        throw new NoSuchTickerException();
    }
}
