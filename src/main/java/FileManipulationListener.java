import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class FileManipulationListener implements ActionListener{

    enum type {CLOSE_FOLIO}

    private type actionType;
    private IMainFrame mainFrame;
    private IFolioPanel folioPanel;

    public FileManipulationListener(type actionType, IMainFrame mainFrame, IFolioPanel folioPanel) {
        this.actionType = actionType;
        this.mainFrame = mainFrame;
        this.folioPanel = folioPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(actionType) {
            case CLOSE_FOLIO:
                closeFolio();
                break;
        }
    }

    public void closeFolio() {
        String folioName = folioPanel.getFolio().getName();
        String dialogName = "Close Folio " + folioName;
        String dialogMessage = "Would you like to save your work for folio " + folioName + "?";
        BinaryDialogFrame closeDialog = new BinaryDialogFrame(dialogName, dialogMessage);
        closeDialog.addYesListener(a -> {
            closeDialog.dispose();
            saveFolio();
        });
        closeDialog.addNoListener(a -> {
            closeDialog.dispose();
            deleteFolio();
        });
    }

    private void saveFolio() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter folioExtension = new FileNameExtensionFilter("Folio files (*.fol)", "fol");
        fileChooser.addChoosableFileFilter(folioExtension);
        fileChooser.setFileFilter(folioExtension);
        int response = fileChooser.showSaveDialog((Component) mainFrame);
        if (response == fileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (fileChooser.getFileFilter() == folioExtension) {
                file = addExtension(file, "fol");
            }
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(folioPanel.getFolio());
                objectOutputStream.close();
                fileOutputStream.close();
                mainFrame.removeFolioTab();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                new AlertFrame("Error", "File not found");
            } catch (IOException e) {
                new AlertFrame("Error", "Problem writing to the file");
                e.printStackTrace();
            }
        }
    }

    private void deleteFolio() {
        mainFrame.removeFolioTab();
    }

    private File addExtension(File file, String extension) {
        String withExtension = file.getAbsolutePath();
        if( !withExtension.toLowerCase().endsWith( "." + extension ) ) {
            withExtension += "." + extension;
            file = new File(withExtension);
        }
        return file;
    }
}
