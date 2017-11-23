import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class FolioPanelButtonListener implements ActionListener{

    enum type {ADD_TICKER, CLOSE_FOLIO, DELETE_FOLIO, REFRESH_FOLIO}

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
        }
    }

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
                    if (folioPanel.getFolio().addStock(tickerSymbol, numberOfShares)) {
                        System.out.println("ticker added");
                    } else {
                        folioPanel.createAlert("Wrong Ticker", "The ticker symbol entered does not match any valid ticker");
                        System.out.println("ticker error");
                    }
                }
            }
        }
    }

    private void refreshFolio() {
        folioPanel.getFolio().refresh();
    }

}
