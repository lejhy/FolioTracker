package Model;

import java.io.Serializable;

public interface IServer extends Serializable {

    /**
     *
     * retrieve the latest market value of a stock
     *
     * @requires: tickerSymbol != null
     * @effects: returns a current value for tickerSymbol as a dollar amount,
     *           with a period separating dollars and cents (eg, "120.50" for
     *           one hundred and twenty dollars and fifty cents) <BR>
     *           unless tickerSymbol is not a valid NYSE or NASDAQ symbol, when
     *           throws Model.NoSuchTickerException <br>
     *           or unless an error connecting to the website or some other
     *           error occurs, when throws Model.WebsiteDataException <BR>
     *           The amount returned may contain commas, for example, "2,243.87"
     *           <br>
     */
    String getLastValue(String ticker) throws WebsiteDataException, NoSuchTickerException;

}
