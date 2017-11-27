package Controller;

import View.IFolioPanel;
import View.IInputFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class BuySellListener implements ActionListener {

    private boolean buy;
    private int value;
    private IFolioPanel folioPanel;
    private String tickerSymbol;
    private IInputFrame inputFrame;


    public BuySellListener(boolean buy, IFolioPanel folioPanel, IInputFrame inputFrame, String tickerSymbol) {
        this.buy = buy;
        this.value = 0;
        this.inputFrame = inputFrame;
        this.folioPanel = folioPanel;
        this.tickerSymbol = tickerSymbol;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(retrieveValue()) {
            if (buy)
                buyStocks();
            else
                sellStocks();
        }

    }

    private boolean retrieveValue() {
        try {
            String in = inputFrame.getInputField().getText();
            Scanner scan = new Scanner(in);
            if(!scan.hasNextInt()) {
                folioPanel.createAlert("Invalid input", in + " is not an integer. A valid positive integer must be entered to continue");
                return false;
            }
            else {
                int value = scan.nextInt();
                if(scan.hasNext()) {
                    folioPanel.createAlert("Invalid input", in + " starts as an integer, but is followed by invalid characters. A valid positive integer must be entered to continue");
                    return false;
                }
                else {
                    if(value < 1) {
                        folioPanel.createAlert("Negative number entered","You must enter a positive integer");
                        return false;
                    }
                    else {
                        this.value = value;
                       return true;
                    }
                }
            }
        }
        catch(NullPointerException e) {
            folioPanel.createAlert("No amount entered","You must enter an amount to continue");
            return false;
        }
    }

    private void buyStocks() {
        if(folioPanel.getFolio().buyStock(tickerSymbol, value)) inputFrame.dispose();
        else
            System.out.println("Shouldn't be here");
    }



    private void sellStocks() {
        if(folioPanel.getFolio().sellStock(tickerSymbol,value)) inputFrame.dispose();
        else
            folioPanel.createAlert("Too many stocks entered", "Cannot sell shares than the folio contains");

    }
}
