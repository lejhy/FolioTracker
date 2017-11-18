import javax.swing.*;

public class FolioPanel extends JPanel {

    JPanel headerPanel;

    JLabel tickerSymbolLabel;
    JTextField tickerSymbolTextField;

    JLabel numberOfSharesLabel;
    JTextField numberOfSharesTextField;

    JButton addNewTickerButton;

    JTable stockTable;

    JPanel footerPanel;

    JButton closeFolioButton;
    JButton deleteFolioButton;


    FolioPanel(String name) {
        setName(name);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        headerPanel = new JPanel();

        tickerSymbolLabel = new JLabel("Ticker Symbol");
        tickerSymbolTextField = new JTextField();

        numberOfSharesLabel = new JLabel("Number of Shares");
        numberOfSharesTextField = new JTextField();

        addNewTickerButton = new JButton("Add");

        headerPanel.add(tickerSymbolLabel);
        headerPanel.add(tickerSymbolTextField);
        headerPanel.add(numberOfSharesLabel);
        headerPanel.add(numberOfSharesTextField);
        headerPanel.add(addNewTickerButton);

        add(headerPanel);

        stockTable = new JTable();

        add(stockTable);

        footerPanel = new JPanel();

        closeFolioButton = new JButton("Close");
        deleteFolioButton = new JButton("Delete");

        footerPanel.add(closeFolioButton);
        footerPanel.add(deleteFolioButton);

        add(footerPanel);

    }

}
