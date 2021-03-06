package View;

import Controller.FolioPanelButtonListener;
import Model.IFolio;
import Model.IStock;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.*;
import java.util.List;

public class FolioPanel extends JPanel implements IFolioPanel {

    private final JButton refreshFolioButton;
    private JTable stockTable;
    private DefaultTableModel stockTableModel;
    private IFolio folio;
    private JButton saveFolioButton;
    private JButton addNewTickerButton;
    private JButton closeFolioButton;
    private JButton deleteFolioButton;
    private JToggleButton autoRefreshButton;
    private JTextField tickerSymbolTextField, numberOfSharesTextField;
    private JLabel totalValueLabel;

    public FolioPanel(IFolio folio) {
        this.folio = folio;
        folio.addObserver(this);

        setName(folio.getName());
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JPanel headerPanel = new JPanel();

        JLabel tickerSymbolLabel = new JLabel("Ticker Symbol");
        tickerSymbolTextField = new JTextField(20);

        JLabel numberOfSharesLabel = new JLabel("Number of Shares");
        numberOfSharesTextField = new JTextField(20);

        addNewTickerButton = new JButton("Add New Stock");


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
                "Value of Holding",
                "Price Change",
                "Stock High",
                "Stock Low",
                "Net profit"};

        stockTableModel = new FolioTableModel(new Vector<>(Arrays.asList(columns)), 0);

        stockTable = new JTable(stockTableModel);
        totalValueLabel = new JLabel("Total value of folio : " + folio.getTotalStockValue());
        updateTable();

        JButton buyButton = new JButton("Buy Existing Stock");
        JButton sellButton = new JButton("Sell Existing Stock");

        ActionListener a = new FolioPanelButtonListener(FolioPanelButtonListener.Type.BUY_STOCK, this);
        buyButton.addActionListener(a);
        a = new FolioPanelButtonListener(FolioPanelButtonListener.Type.SELL_STOCK, this);
        sellButton.addActionListener(a);


        JPanel buySellPanel = new JPanel();
        buySellPanel.add(buyButton);
        buySellPanel.add(sellButton);

        add(buySellPanel);


        add(new JScrollPane(stockTable));

        JPanel footerPanel = new JPanel();

        saveFolioButton = new JButton("Save");
        closeFolioButton = new JButton("Close");
        deleteFolioButton = new JButton("Delete");
        refreshFolioButton = new JButton("Refresh");
        autoRefreshButton = new JToggleButton("AutoRefresh");

        totalValueLabel.setVisible(true);

        footerPanel.add(saveFolioButton);
        footerPanel.add(closeFolioButton);
        footerPanel.add(deleteFolioButton);
        footerPanel.add(refreshFolioButton);
        footerPanel.add(autoRefreshButton);
        footerPanel.add(totalValueLabel, BorderLayout.EAST);

        add(footerPanel);

    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("FolioPanel update " + arg.toString());
        updateTable();
        stockTableModel.fireTableDataChanged();
    }

    private void updateTable() {
        List<IStock> stocks = folio.getStocks();
        System.out.println(folio.getStocks().size());

        int extraColumns = stocks.size() - stockTableModel.getRowCount();

        for (int i = 0; i < extraColumns; i++) {
            stockTableModel.addRow(new Vector<>(stockTableModel.getColumnCount()));
        }
        if(extraColumns==-1) {
            stockTableModel.removeRow(0);
        }
        for (int i = 0; i < stocks.size(); i++) {
            IStock stock = stocks.get(i);
            stockTableModel.setValueAt(stock.getSymbol(), i, 0);
            stockTableModel.setValueAt(stock.getName(), i, 1);
            stockTableModel.setValueAt(stock.getNumber(), i, 2);
            stockTableModel.setValueAt(stock.getPrice(), i, 3);
            stockTableModel.setValueAt(String.format("%.2f", stock.getValue()), i, 4);
            if(stock.getDifference() < 0) {
                stockTableModel.setValueAt("<html><font color=\"red\">" + String.format("%.1f", stock.getPercentageChange()) + "%</font></html>", i, 5);
            } else if (stock.getDifference() > 0) {
                stockTableModel.setValueAt("<html><font color=\"green\">+" + String.format("%.1f", stock.getPercentageChange()) + "%</font></html>", i, 5);
            } else {
                stockTableModel.setValueAt(String.format("%.1f", stock.getPercentageChange()) + "%", i, 5);
            }
            stockTableModel.setValueAt(stock.getStockHigh(), i , 6);
            stockTableModel.setValueAt(stock.getStockLow(), i , 7);
            stockTableModel.setValueAt(String.format("%.2f", stock.getValue() - stock.getInitialSpending()), i, 8);

        }

        String totalStockValue = String.format("%.2f", folio.getTotalStockValue());
        totalValueLabel.setText("Total value of folio : " + totalStockValue);
    }

    @Override
    public void addAddNewTickerListener(ActionListener a) { addNewTickerButton.addActionListener(a); }

    @Override
    public void addDeleteFolioListener(ActionListener a) { deleteFolioButton.addActionListener(a); }

    @Override
    public void addCloseFolioListener(ActionListener a) { closeFolioButton.addActionListener(a); }

    @Override
    public void addSaveFolioListener(ActionListener a) { saveFolioButton.addActionListener(a); }

    @Override
    public void addRefreshFolioListener(ActionListener a) { refreshFolioButton.addActionListener(a); }

    @Override
    public void addTableModelListener(TableModelListener t) { stockTableModel.addTableModelListener(t); }

    @Override
    public void addAutoRefreshFolioListener(ItemListener i) { autoRefreshButton.addItemListener(i); }

    @Override
    public void createAlert(String name, String message) {
        new AlertFrame(name, message);
    }

    @Override
    public int getSelectedRow() {
        return stockTable.getSelectedRow();

    }

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
