import javax.swing.*;
import javax.swing.event.TableModelListener;
import java.awt.event.ActionListener;

public interface IFolioPanel {


    IFolio getFolio();

    String getTickerSymbolInput();

    String getNumberOfSharesInput();

    JTable getStockTable();

    String getName();

    void addAddNewTickerListener(ActionListener actionListener);

    void addDeleteFolioListener(ActionListener actionListener);

    void addCloseFolioListener(ActionListener actionListener);

    void addRefreshFolioListener(ActionListener actionListener);

    void addTableModelListener(TableModelListener tableModelListener);
}
