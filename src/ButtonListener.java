import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class ButtonListener implements ActionListener {

    enum type {CREATE_FOLIO, OPEN_FOLIO, DELETE_FOLIO, CLOSE_FOLIO}

    private type buttonPressed;
    private IMainFrame mainFrame;

    public ButtonListener(type buttonPressed, IMainFrame mainFrame) {
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
            mainFrame.addFolioTab(folioPanel);
        });
    }

    private void openPortfolio() {
        System.out.println("open portfolio");
    }

    private void closePortFolio() {
        System.out.println("close portfolio");
    }
}
