import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FolioPanelButtonListener implements ActionListener{

    enum type {ADD_TICKER, CLOSE_FOLIO, DELETE_FOLIO}

    private FolioPanel folioPanel;
    private type buttonPressed;


    public FolioPanelButtonListener (type buttonPressed, FolioPanel folioPanel) {
        this.buttonPressed = buttonPressed;
        this.folioPanel = folioPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (buttonPressed) {
            case ADD_TICKER:
                addTicker();
                break;
        }
    }

    private void addTicker() {
        String tickerSymbol = folioPanel.getTickerSymbolInput();
        String numberOfSharesInput = folioPanel.getNumberOfSharesInput();
        int numberOfShares = Integer.parseInt(numberOfSharesInput);
        if (isValidTicker(tickerSymbol) && numberOfShares > 0) {
            folioPanel.getFolio().addStock(tickerSymbol, numberOfShares);
        }
    }

    private boolean isValidTicker(String tickerSymbol) {
        return true;
    }
}
