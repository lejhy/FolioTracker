import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

public class FolioPanel extends JPanel implements Observer {

    private final Vector<String> stockTableColumnNames;
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


    FolioPanel(String name, Vector<Vector<String>> data) {
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

        String[] columns = {
                "Ticker Symbol",
                "Stock Name",
                "Number of Shares",
                "Price per Share",
                "Value of Holding"};

        stockTableColumnNames = new Vector<>(Arrays.asList(columns));



        stockTableModel = new DefaultTableModel(data, stockTableColumnNames);

        stockTableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int index = e.getFirstRow();
                Controller.modifyFolio((Vector<String>)stockTableModel.getDataVector().elementAt(index),FolioPanel.this.getName(),index);
            }
        });


        stockTable = new JTable(stockTableModel);


        add(new JScrollPane(stockTable));

        footerPanel = new JPanel();

        closeFolioButton = new JButton("Close");
        deleteFolioButton = new JButton("Delete");

        deleteFolioButton.addActionListener(new ButtonListener("delete"));
        closeFolioButton.addActionListener(new ButtonListener("close"));

        footerPanel.add(closeFolioButton);
        footerPanel.add(deleteFolioButton);

        add(footerPanel);

    }


    @Override
    public void update(Observable o, Object arg) {
        stockTableModel = new DefaultTableModel(stockTableColumnNames,Controller.refreshVector(this.getName()));
    }


}
