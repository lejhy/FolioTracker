package Model;

import java.io.Serializable;
import java.util.List;
import java.util.Observer;

public interface IFolio extends Serializable{

    enum ticker { EXISTS, VALID, INVALID, ERROR }

    /**
     *
     * @effects: returns a list of of the stocks stored in the folio
     *
     */
    List<IStock> getStocks();


    /**
     *
     * @effects: returns the name of the folio
     *
     */
    String getName();


    /**
     *
     * @requires: ticker != null && number > 0
     * @modifies: this
     * @effects: if ticker is not a valid ticker then return false
     *           else add a new IStock to the list of stocks
     *
     */
    boolean addStock(String ticker, int number);


    /**
     *
     * @requires: o != null
     * @modifies: this
     * @effects: add o to list of observers
     *
     */
    void addObserver(Observer o);


    /**
     *
     * @effects: returns a double value that represents the total stock value of the folio
     *
     */
    double getTotalStockValue();


    /**
     *
     * @modifies: this
     * @effects: informs the IFolio to refresh itself
     *
     */
    void refresh();


    /**
     *
     * @requires: ticker!=null
     * @effects: if the ticker already exists in the IFolio return EXISTS
     *           else if the ticker is invalid return INVALID
     *           else if connecting to the webPage for stocks failed return ERROR
     *           else if ticker is valid return VALID
     *
     */
    ticker checkTicker(String ticker);


    /**
     *
     * @requires: tickerSymbol != null && numberOfShares > 0
     * @modifies: this
     * @effects: if tickerSymbol is not a symbol for one of the
     *           stocks owned in the folio then return false
     *
     *           else add more shares to the existing stock corresponding to
     *           tickerSymbol and return true
     *
     */
    boolean buyStock(String tickerSymbol, int numberOfShares);


    /**
     *
     * @requires: tickerSymbol != null
     * @modifies: this
     * @effects: if tickerSymbol is not a symbol for one of the
     *           stocks owned in the folio then return false
     *
     *           else if numberOfShares is greater than the amount
     *           of shares in the stock corresponding to the tickerSymbol
     *           then return false
     *
     *           else if numberOfShares is equal to shares in the
     *           stock then remove the stock in the folio and return true
     *
     *           else if numberOfShares is less than the number of shares in stock
     *           reduce the number of shares by numberOfShares and return true
     *
     */
    boolean sellStock(String tickerSymbol, int numberOfShares);


    /**
     *
     * @modifies: this
     * @effects: triggers the auto refresh to begin
     *
     */
    void autoRefreshStart();

    /**
     *
     * @modifies: this
     * @effects: triggers the auto refresh to end
     *
     */
    void autoRefreshStop();


    /**
     *
     * @modifies: this
     * @effects: terminate all processes owned by this folio, such as auto update
     *
     */
    void delete();

}
