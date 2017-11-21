import javax.management.OperationsException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class Controller {

    private IMainFrame mainFrame;



    public Controller(IMainFrame frame) {
        this.mainFrame = frame;

        mainFrame.addCreateFolioListener(new ButtonListener(ButtonListener.type.CREATE_FOLIO, mainFrame));

        mainFrame.addOpenFolioListener(new ButtonListener(ButtonListener.type.OPEN_FOLIO, mainFrame));
    }

    public void deleteFolio() { mainFrame.removeFolioTab(); }

    public Vector<Vector<String>> refreshVector(String name) {
        return mainFrame.getData(name);
    }

    public void modifyFolio(Vector<String> i, String name, int index) {
        mainFrame.modifyFolio(i,name,index);

    }

    public void closeFolio() { mainFrame.closeFolio(); }

    public void openFolio() { mainFrame.openFolio(); }

    public void refresh() { mainFrame.refresh(); }
}


