import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class MainFrameButtonListener implements ActionListener {

    enum type {CREATE_FOLIO, OPEN_FOLIO, DELETE_FOLIO, SAVE}

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
            case SAVE:
                save();
                break;
        }

    }

    private void deletePortfolio() {
        System.out.println("Delete");
        BinaryDialogFrame binaryDialogFrame = new BinaryDialogFrame("Delete", "Are you sure you want to delete this folio?");
        binaryDialogFrame.addYesListener(a -> {
            binaryDialogFrame.close();
            mainFrame.removeFolioTab();
        });
        binaryDialogFrame.addNoListener(a -> {
            binaryDialogFrame.close();
        });
    }

    private void createPortfolio() {
        System.out.println("create portfolio");
        CreateFolioFrame createFolioFrame = new CreateFolioFrame();
        createFolioFrame.addConfirmationListener(a -> {
            String folioName = "New Folio";
            if (!createFolioFrame.getNameField().getText().isEmpty()){
                folioName = createFolioFrame.getNameField().getText();
            }
            IFolio folio = new Folio(folioName);
            createFolioPanel(folio);
            createFolioFrame.close();
        });
    }

    private void openPortfolio() {
        System.out.println("open portfolio");
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter folioExtension = new FileNameExtensionFilter("Folio files (*.fol)", "fol");
        fileChooser.addChoosableFileFilter(folioExtension);
        fileChooser.setFileFilter(folioExtension);
        int response = fileChooser.showOpenDialog((Component) mainFrame);
        if (response == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                Object input = objectInputStream.readObject();
                if (input instanceof IFolio) {
                    createFolioPanel((IFolio) input);
                }
            } catch (FileNotFoundException e) {
                new AlertFrame("Error", "File not found");
                e.printStackTrace();
            } catch (IOException e) {
                new AlertFrame("Error", "Problem reading from a file");
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                new AlertFrame("Error", "The file appears to be corrupt");
                e.printStackTrace();
            }

        }
    }

    private void createFolioPanel(IFolio folio) {
        IFolioPanel folioPanel = new FolioPanel(folio);
        folioPanel.addAddNewTickerListener( new FolioPanelButtonListener(FolioPanelButtonListener.type.ADD_TICKER, folioPanel) );
        folioPanel.addRefreshFolioListener( new FolioPanelButtonListener(FolioPanelButtonListener.type.REFRESH_FOLIO, folioPanel));
        folioPanel.addDeleteFolioListener( new MainFrameButtonListener(MainFrameButtonListener.type.DELETE_FOLIO, mainFrame) );
        folioPanel.addCloseFolioListener( new FileManipulationListener(FileManipulationListener.type.CLOSE_FOLIO, mainFrame, folioPanel) );
        folioPanel.addTableModelListener( new FolioTableListener(folioPanel) );
        mainFrame.addFolioTab((Component) folioPanel);
    }

    private void save() { System.out.println("save"); }
}
