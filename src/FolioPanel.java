import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.*;

public class FolioPanel extends JPanel implements Observer {

    private DefaultTableModel stockTableModel;
    private IFolio folio;

    FolioPanel(IFolio folio) {
        this.folio = folio;

        setName(folio.getName());
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JPanel headerPanel = new JPanel();

        JLabel tickerSymbolLabel = new JLabel("Ticker Symbol");
        JTextField tickerSymbolTextField = new JTextField(20);

        JLabel numberOfSharesLabel = new JLabel("Number of Shares");
        JTextField numberOfSharesTextField = new JTextField(20);

        JButton addNewTickerButton = new JButton("Add");

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

        stockTableModel = new DefaultTableModel(new Vector<>(Arrays.asList(columns)), 0);

        JTable stockTable = new JTable(stockTableModel);

        updateTable();

        add(new JScrollPane(stockTable));

        JPanel footerPanel = new JPanel();

        JButton closeFolioButton = new JButton("Close");
        JButton deleteFolioButton = new JButton("Delete");

        deleteFolioButton.addActionListener(new ButtonListener(ButtonListener.type.DELETE_FOLIO));
        closeFolioButton.addActionListener(new ButtonListener(ButtonListener.type.CLOSE_FOLIO));

        footerPanel.add(closeFolioButton);
        footerPanel.add(deleteFolioButton);

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
            stockTableModel.setValueAt(stock.getSymbol(), 0, i);
            stockTableModel.setValueAt(stock.getName(), 1, i);
            stockTableModel.setValueAt(stock.getNumber(), 2, i);
            stockTableModel.setValueAt(stock.getPrice(), 3, i);
            stockTableModel.setValueAt(stock.getValue(), 4, i);
        }
    }
}
