import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class MainFrameButtonListener implements ActionListener {

    enum type {CREATE_FOLIO, OPEN_FOLIO, DELETE_FOLIO, CLOSE_FOLIO, SAVE}

    private type buttonPressed;
    private IMainFrame mainFrame;

    public MainFrameButtonListener(type buttonPressed, IMainFrame mainFrame) {
        this.buttonPressed = buttonPressed;
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (buttonPressed) {
            case CREATE_FOLIO:
                createPortfolio();
                break;
            case OPEN_FOLIO:
                openPortfolio();
                break;
            case DELETE_FOLIO:
                deletePortfolio();
                break;
            case CLOSE_FOLIO:
                closePortFolio();
                break;
            case SAVE:
                save();
                break;
        }

    }

    private void deletePortfolio() {
        System.out.println("Delete");
        mainFrame.removeFolioTab();
    }

    private void createPortfolio() {
        System.out.println("create portfolio");
        CreateFolioFrame createFolioFrame = new CreateFolioFrame();
        createFolioFrame.addConfirmationListener(a -> {
            String folioName = "New Folio";
            if (!createFolioFrame.getNameField().getText().isEmpty()){
                folioName = createFolioFrame.getNameField().getText();
            }
            Folio folio = new Folio(folioName);
            FolioPanel folioPanel = new FolioPanel(folio);
            folioPanel.addAddNewTickerListener( new FolioPanelButtonListener(FolioPanelButtonListener.type.ADD_TICKER, folioPanel) );
            folioPanel.addDeleteFolioListener( new MainFrameButtonListener(MainFrameButtonListener.type.DELETE_FOLIO, mainFrame) );
            folioPanel.addCloseFolioListener( new MainFrameButtonListener(MainFrameButtonListener.type.CLOSE_FOLIO, mainFrame) );
            folioPanel.addTableModelListener( new folioTableListener(folio) );

            mainFrame.addFolioTab(folioPanel);
            createFolioFrame.close();
        });
    }

    private void openPortfolio() {
        System.out.println("open portfolio");
    }

    private void closePortFolio() {
        System.out.println("close portfolio");
    }

    private void save() { System.out.println("save"); }
}
