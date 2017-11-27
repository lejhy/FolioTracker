package Controller;

import View.IBuySellFrame;
import View.IFolioPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class BuySellListener implements ActionListener {
    private boolean buy;
    private JTextField input;
    private IFolioPanel folioPanel;
    private String tickerSymbol;
    private int value;
    private IBuySellFrame frame;

    public BuySellListener(boolean buy, JTextField input, IFolioPanel folioPanel, IBuySellFrame frame, String tickerSymbol) {
        this.buy=buy;
        this.input=input;
        this.folioPanel =folioPanel;
        this.tickerSymbol = tickerSymbol;
        this.frame= frame;
        value = 0;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(setStock()) {
            if (buy)
                buyStocks();
            else
                sellStocks();
        }

    }

    private boolean setStock() {
        try {
            String in = input.getText();
            Scanner scan = new Scanner(in);
            if(!scan.hasNextInt()) {
                invalidStringEntered();
                return false;
            }
            else {
                int value = scan.nextInt();
                if(scan.hasNext()) {
                    invalidStringEntered();
                    return false;
                }
                else {
                    if(value<1) {
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
        if(folioPanel.getFolio().buyStock(tickerSymbol, value)) frame.dispose();
        else
            System.out.println("Shouldn't be here");
    }



    private void sellStocks() {
        if(folioPanel.getFolio().sellStock(tickerSymbol,value)) frame.dispose();
        else
            folioPanel.createAlert("Too many stocks entered", "Cannot sell shares than the folio contains");

    }

    private void invalidStringEntered() {
        folioPanel.createAlert("No valid integer", "A valid positive integer must be entered to continue");
    }
}
