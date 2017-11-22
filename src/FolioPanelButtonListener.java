import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FolioPanelButtonListener implements ActionListener{

    enum type {ADD_TICKER, CLOSE_FOLIO, DELETE_FOLIO}

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
        }
    }

    private void addTicker() {
        String tickerSymbol = folioPanel.getTickerSymbolInput();
        String numberOfSharesInput = folioPanel.getNumberOfSharesInput();
        int numberOfShares = Integer.parseInt(numberOfSharesInput);
        if (folioPanel.getFolio().addStock(tickerSymbol, numberOfShares)) {
            System.out.println("ticker added");
        } else {
            System.out.println("ticker error");
        }
    }

}
