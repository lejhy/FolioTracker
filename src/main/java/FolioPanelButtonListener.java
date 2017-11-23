import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class FolioPanelButtonListener implements ActionListener{

    enum type {ADD_TICKER, REFRESH_FOLIO, BUY_STOCK, SELL_STOCK }

    private IFolioPanel folioPanel;
    private type buttonPressed;


    public FolioPanelButtonListener (type buttonPressed, IFolioPanel folioPanel) {
        this.buttonPressed = buttonPressed;
        this.folioPanel = folioPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (buttonPressed) {
            case ADD_TICKER:
                addTicker();
                break;
            case REFRESH_FOLIO:
                refreshFolio();
                break;
            case BUY_STOCK:
                buyStock();
                break;
            case SELL_STOCK:
                sellStock();
                break;
        }
    }

    private void sellStock() {
        int i = folioPanel.getSelectedRow();
        if(i==-1) {
            sendNotSelectedAlert("sell");
        } else {
            new BuySellFrame("Sell", false, folioPanel, i);
        }

    }

    private void buyStock() {
        int i = folioPanel.getSelectedRow();
        if(i==-1) {
            sendNotSelectedAlert("buy");
        } else {
            new BuySellFrame("Buy", true, folioPanel, i);
        }
    }

    private void sendNotSelectedAlert(String string) {
        folioPanel.createAlert("No row selected", "You must select a row of the stock you wish to " + string);
    }

    //TODO: Needs be be cleaned up
    private void addTicker() {
        String tickerSymbol = folioPanel.getTickerSymbolInput();
        String numberOfSharesInput = folioPanel.getNumberOfSharesInput();
        if (tickerSymbol.equals("")) {
            folioPanel.createAlert("Invalid Ticker", "A valid ticker symbol must be added");
        } else if (numberOfSharesInput.equals("")) {
            folioPanel.createAlert("Invalid Ticker", "No share amount was added");
        } else {
            Scanner scan = new Scanner(numberOfSharesInput.trim());
            if (!scan.hasNextInt()) {
                folioPanel.createAlert("Invalid Number", "The value entered for share amount is not a valid integer");
            } else {
                int numberOfShares = scan.nextInt();
                if (scan.hasNext()) {
                    folioPanel.createAlert("Invalid Number", "The value entered for share amount is not a valid integer");
                } else {
                    if(!folioPanel.getFolio().isStock(tickerSymbol)) {
                        folioPanel.createAlert("Incorrect Ticker", "The ticker entered does not match a real ticker");
                    }
                    else {
                        if (folioPanel.getFolio().alreadyOwn(tickerSymbol)) {
                            folioPanel.newStockOrAdd(tickerSymbol, numberOfShares);
                        }
                        else
                            folioPanel.getFolio().addStock(tickerSymbol,numberOfShares);
                    }
                }
            }
        }
    }

    private void refreshFolio() {
        folioPanel.getFolio().refresh();
    }

}
