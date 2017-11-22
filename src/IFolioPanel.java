import javax.swing.*;

public interface IFolioPanel {


    IFolio getFolio();

    String getTickerSymbolInput();

    String getNumberOfSharesInput();

    JTable getStockTable();

    String getName();

    void addAddNewTickerListener(FolioPanelButtonListener folioPanelButtonListener);

    void addDeleteFolioListener(MainFrameButtonListener mainFrameButtonListener);

    void addCloseFolioListener(FileManipulationListener fileManipulationListener);

    void addTableModelListener(folioTableListener folioTableListener);
}
