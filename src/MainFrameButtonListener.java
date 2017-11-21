import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class MainFrameButtonListener implements ActionListener {

    enum type {CREATE_FOLIO, OPEN_FOLIO, DELETE_FOLIO, CLOSE_FOLIO}

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
        }

    }

    private void deletePortfolio() {
        System.out.println("Delete");
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
            folioPanel.addDeleteFolioListener( new FolioPanelButtonListener(FolioPanelButtonListener.type.DELETE_FOLIO, folioPanel) );
            folioPanel.addCloseFolioListener( new FolioPanelButtonListener(FolioPanelButtonListener.type.CLOSE_FOLIO, folioPanel) );

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
}
