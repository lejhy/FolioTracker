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
            folioPanel.createAlert("Invalid Number", "No share amount was added");
        } else {
            Scanner scan = new Scanner(numberOfSharesInput.trim());
            if (!scan.hasNextInt()) {
                folioPanel.createAlert("Invalid Number", "The value entered for share amount is not a valid integer");
            } else {
                int numberOfShares = scan.nextInt();
                if (scan.hasNext()) {
                    folioPanel.createAlert("Invalid Number", "The value entered for share amount is not a valid integer");
                } else {
                    addTicker(tickerSymbol, numberOfShares);
                }
            }
        }
    }

    private void addTicker(String tickerSymbol, int numberOfShares) {
        switch(folioPanel.getFolio().checkTicker(tickerSymbol)) {
            case EXISTS:
                addExistingTicker(tickerSymbol, numberOfShares);
                break;
            case VALID:
                folioPanel.getFolio().addStock(tickerSymbol, numberOfShares);
                break;
            case INVALID:
                folioPanel.createAlert("Incorrect Ticker", "The ticker entered does not match a real ticker");
                break;
            case ERROR:
                folioPanel.createAlert("Error", "Something has gone wrong with the request, probably connection issues.");
                break;
        }
    }

    private void addExistingTicker (String tickerSymbol, int numberOfShares) {
        BinaryDialogFrame binary = new BinaryDialogFrame("Already have stock", "You already have this" +
                " in your folio, would you like to add these stocks to the ones you already have?");
        binary.addYesListener(e -> {
            folioPanel.getFolio().buyStock(tickerSymbol, numberOfShares);
            binary.dispose();
        });
        binary.addNoListener(e -> binary.dispose());
    }

    private void refreshFolio() {
        folioPanel.getFolio().refresh();
    }
}
