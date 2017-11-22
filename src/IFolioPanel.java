import javax.swing.*;
import javax.swing.event.TableModelListener;
import java.awt.event.ActionListener;

public interface IFolioPanel {


    IFolio getFolio();

    String getTickerSymbolInput();

    String getNumberOfSharesInput();

    JTable getStockTable();

    String getName();

    void addAddNewTickerListener(ActionListener folioPanelButtonListener);

    void addDeleteFolioListener(ActionListener mainFrameButtonListener);

    void addCloseFolioListener(ActionListener fileManipulationListener);

    void addTableModelListener(TableModelListener folioTableListener);
}
