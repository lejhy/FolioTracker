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

    private DefaultTableModel stockTableModel;


    FolioPanel(String name, Vector<Vector<String>> data) {
        setName(name);
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

        stockTableColumnNames = new Vector<>(Arrays.asList(columns));



        stockTableModel = new DefaultTableModel(data, stockTableColumnNames);

        stockTableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int index = e.getFirstRow();
                Controller.modifyFolio((Vector<String>)stockTableModel.getDataVector().elementAt(index),FolioPanel.this.getName(),index);
            }
        });


        JTable stockTable = new JTable(stockTableModel);


        add(new JScrollPane(stockTable));

        JPanel footerPanel = new JPanel();

        JButton closeFolioButton = new JButton("Close");
        JButton deleteFolioButton = new JButton("Delete");

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
