import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FolioPanel extends JPanel {

    JPanel headerPanel;

    JLabel tickerSymbolLabel;
    JTextField tickerSymbolTextField;

    JLabel numberOfSharesLabel;
    JTextField numberOfSharesTextField;

    JButton addNewTickerButton;

    JTable stockTable;

    DefaultTableModel stockTableModel;

    JPanel footerPanel;

    JButton closeFolioButton;
    JButton deleteFolioButton;


    FolioPanel(String name) {
        setName(name);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        headerPanel = new JPanel();

        tickerSymbolLabel = new JLabel("Ticker Symbol");
        tickerSymbolTextField = new JTextField(20);

        numberOfSharesLabel = new JLabel("Number of Shares");
        numberOfSharesTextField = new JTextField(20);

        addNewTickerButton = new JButton("Add");

        headerPanel.add(tickerSymbolLabel);
        headerPanel.add(tickerSymbolTextField);
        headerPanel.add(numberOfSharesLabel);
        headerPanel.add(numberOfSharesTextField);
        headerPanel.add(addNewTickerButton);

        add(headerPanel);

        String[] stockTableColumnNames = {
                "Ticker Symbol",
                "Stock Name",
                "Number of Shares",
                "Price per Share",
                "Value of Holding"};

        // Just sample data
        Object[][] data = {
                {"Kathy", "Smith",
                        "Snowboarding", new Integer(5), new Boolean(false)},
                {"John", "Doe",
                        "Rowing", new Integer(3), new Boolean(true)},
                {"Sue", "Black",
                        "Knitting", new Integer(2), new Boolean(false)},
                {"Jane", "White",
                        "Speed reading", new Integer(20), new Boolean(true)},
                {"Joe", "Brown",
                        "Pool", new Integer(10), new Boolean(false)}
        };

        stockTableModel = new DefaultTableModel(data, stockTableColumnNames);

        stockTable = new JTable(stockTableModel);
        add(new JScrollPane(stockTable));

        footerPanel = new JPanel();

        closeFolioButton = new JButton("Close");
        deleteFolioButton = new JButton("Delete");

        footerPanel.add(closeFolioButton);
        footerPanel.add(deleteFolioButton);

        add(footerPanel);

    }

}
