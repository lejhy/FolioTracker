import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Vector;

public interface IMainFrame {

    boolean addFolioTab(Component folioTab);

    boolean removeFolioTab();

    boolean openFolio();

    boolean closeFolio();

    Vector<Vector<String>> getData(String name);

    void modifyFolio(Vector<String> i, String name, int x);

    void refresh();

    void addCreateFolioListener(ActionListener a);

    void addOpenFolioListener(ActionListener a);

    JTabbedPane getTabbedPane();

}
