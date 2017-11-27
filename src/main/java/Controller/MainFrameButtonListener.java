package Controller;

import Model.Folio;
import Model.IFolio;
import View.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class MainFrameButtonListener implements ActionListener {

    public enum Type {CREATE_FOLIO, OPEN_FOLIO, SAVE}

    private Type buttonPressed;
    private IMainFrame mainFrame;

    public MainFrameButtonListener(Type buttonPressed, IMainFrame mainFrame) {
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
            case SAVE:
                save();
                break;
        }

    }

    private void createPortfolio() {
        System.out.println("create portfolio");
        InputFrame inputFrame = new InputFrame("Create Folio", "Enter name of Portfolio ");
        inputFrame.addConfirmationListener(a -> {
            String folioName = "New Folio";
            if (!inputFrame.getInputField().getText().isEmpty()){
                folioName = inputFrame.getInputField().getText();
            }
            IFolio folio = new Folio(folioName);
            createFolioPanel(folio);
            inputFrame.close();
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
        folioPanel.addAddNewTickerListener( new FolioPanelButtonListener(FolioPanelButtonListener.Type.ADD_TICKER, folioPanel) );
        folioPanel.addRefreshFolioListener( new FolioPanelButtonListener(FolioPanelButtonListener.Type.REFRESH_FOLIO, folioPanel) );
        folioPanel.addAutoRefreshFolioListener( new FolioPanelItemListener(FolioPanelItemListener.Type.AUTO_REFRESH, folio) );

        folioPanel.addDeleteFolioListener( new FileManipulationListener(FileManipulationListener.Type.DELETE_FOLIO, mainFrame, folioPanel) );
        folioPanel.addSaveFolioListener( new FileManipulationListener(FileManipulationListener.Type.SAVE_FOLIO, mainFrame, folioPanel) );
        folioPanel.addCloseFolioListener( new FileManipulationListener(FileManipulationListener.Type.CLOSE_FOLIO, mainFrame, folioPanel) );

        folioPanel.addTableModelListener( new FolioTableListener(folioPanel) );
        mainFrame.addFolioTab((Component) folioPanel);
    }

    private void save() { System.out.println("save"); }
}
