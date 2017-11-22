import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.*;

public class FolioPanel extends JPanel implements Observer,IFolioPanel {

    private final JButton refreshFolioButton;
    private JTable stockTable;
    private DefaultTableModel stockTableModel;
    private IFolio folio;
    private JButton addNewTickerButton, closeFolioButton, deleteFolioButton;
    private JTextField tickerSymbolTextField, numberOfSharesTextField;
    private JLabel totalValueLabel;

    FolioPanel(IFolio folio) {
        this.folio = folio;
        folio.addObserver(this);

        setName(folio.getName());
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JPanel headerPanel = new JPanel();

        JLabel tickerSymbolLabel = new JLabel("Ticker Symbol");
        tickerSymbolTextField = new JTextField(20);

        JLabel numberOfSharesLabel = new JLabel("Number of Shares");
        numberOfSharesTextField = new JTextField(20);

        addNewTickerButton = new JButton("Add");

        headerPanel.add(tickerSymbolLabel);
        headerPanel.add(tickerSymbolTextField);
        headerPanel.add(numberOfSharesLabel);
        headerPanel.add(numberOfSharesTextField);
        headerPanel.add(addNewTickerButton);

        add(headerPanel);

        String[] columns = {
                "Ticker Symbol",
                "Stock Name",
                "Number of Shares",
                "Price per Share",
                "Value of Holding"};

        stockTableModel = new FolioTableModel(new Vector<>(Arrays.asList(columns)), 0);

        stockTable = new JTable(stockTableModel);
        totalValueLabel = new JLabel("Total value of folio : " + folio.getTotalStockValue());
        updateTable();

        add(new JScrollPane(stockTable));

        JPanel footerPanel = new JPanel();

        closeFolioButton = new JButton("Close");
        deleteFolioButton = new JButton("Delete");
        refreshFolioButton = new JButton("Refresh");


        totalValueLabel.setVisible(true);

        footerPanel.add(closeFolioButton);
        footerPanel.add(deleteFolioButton);
        footerPanel.add(refreshFolioButton);
        footerPanel.add(totalValueLabel);

        add(footerPanel);

    }

    @Override
    public void update(Observable o, Object arg) {
        updateTable();
        stockTableModel.fireTableDataChanged();
    }

    private void updateTable() {
        List<IStock> stocks = folio.getStocks();

        int extraColumns = stocks.size() - stockTableModel.getRowCount();
        for (int i = 0; i < extraColumns; i++) {
            stockTableModel.addRow(new Vector<>(stockTableModel.getColumnCount()));
        }

        for (int i = 0; i < stockTableModel.getRowCount(); i++){
            IStock stock = stocks.get(i);
            stockTableModel.setValueAt(stock.getSymbol(), i, 0);
            stockTableModel.setValueAt(stock.getName(), i, 1);
            stockTableModel.setValueAt(stock.getNumber(), i, 2);
            stockTableModel.setValueAt(stock.getPrice(), i, 3);
            stockTableModel.setValueAt(stock.getValue(), i, 4);
        }
        totalValueLabel.setText("Total value of folio : " + folio.getTotalStockValue());
    }

    @Override
    public void addAddNewTickerListener(ActionListener a) { addNewTickerButton.addActionListener(a); }

    @Override
    public void addDeleteFolioListener(ActionListener a) { deleteFolioButton.addActionListener(a); }

    @Override
    public void addCloseFolioListener(ActionListener a) { closeFolioButton.addActionListener(a); }

    @Override
    public void addRefreshFolioListener(ActionListener a) { refreshFolioButton.addActionListener(a); }

    @Override
    public void addTableModelListener(TableModelListener t) { stockTableModel.addTableModelListener(t); }

    public IFolio getFolio() {
        return folio;
    }

    public JTable getStockTable() { return stockTable; }

    public String getTickerSymbolInput() {
        return tickerSymbolTextField.getText();
    }

    public String getNumberOfSharesInput() {
        return numberOfSharesTextField.getText();
    }
}
