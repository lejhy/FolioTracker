package Controller;

import View.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class FileManipulationListener implements ActionListener{

    enum Type {CLOSE_FOLIO, SAVE_FOLIO, DELETE_FOLIO}

    private Type actionType;
    private IMainFrame mainFrame;
    private IFolioPanel folioPanel;

    FileManipulationListener(Type actionType, IMainFrame mainFrame, IFolioPanel folioPanel) {
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
            case SAVE_FOLIO:
                saveFolio();
                break;
            case DELETE_FOLIO:
                deleteFolio();
                break;
        }
    }

    private void closeFolio() {
        String folioName = folioPanel.getFolio().getName();
        String dialogName = "Close Folio " + folioName;
        String dialogMessage = "Would you like to save your work for folio " + folioName + "?";
        IBinaryDialogFrame closeDialog = new BinaryDialogFrame(dialogName, dialogMessage);
        closeDialog.addYesListener(a -> {
            closeDialog.dispose();
            if (saveFolio()) delete();
        });
        closeDialog.addNoListener(a -> {
            closeDialog.dispose();
            delete();
        });
    }

    private boolean saveFolio() {
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
                new AlertFrame("Success", "File saved successfuly");
                return true;
            } catch (FileNotFoundException e) {
                new AlertFrame("Error", "File not found");
            } catch (IOException e) {
                new AlertFrame("Error", "Problem writing to the file");
            }
        }
        return false;
    }

    private void deleteFolio() {
        System.out.println("Delete");
        IBinaryDialogFrame binaryDialogFrame = new BinaryDialogFrame("Delete", "Are you sure you want to delete this folio?");
        binaryDialogFrame.addYesListener(a -> {
            binaryDialogFrame.dispose();
            delete();
        });
        binaryDialogFrame.addNoListener(a -> {
            binaryDialogFrame.dispose();
        });


    }

    private void delete() {
        mainFrame.removeFolioTab();
        folioPanel.getFolio().delete();
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
