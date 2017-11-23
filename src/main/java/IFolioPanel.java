import javax.swing.*;
import javax.swing.event.TableModelListener;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.Observer;

public interface IFolioPanel extends Observer {


    IFolio getFolio();

    String getTickerSymbolInput();

    String getNumberOfSharesInput();

    JTable getStockTable();

    String getName();

    void addAddNewTickerListener(ActionListener actionListener);

    void addDeleteFolioListener(ActionListener actionListener);

    void addCloseFolioListener(ActionListener actionListener);

    void addSaveFolioListener(ActionListener actionListener);

    void addRefreshFolioListener(ActionListener actionListener);

    void addTableModelListener(TableModelListener tableModelListener);

    void addAutoRefreshFolioListener(ItemListener itemListener);

    void createAlert(String name, String message);

}
