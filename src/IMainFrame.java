import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Vector;

public interface IMainFrame {

    boolean addFolioTab(Component folioTab);

    boolean removeFolioTab();

    boolean openFolio();

    boolean closeFolio();

    void addCreateFolioListener(ActionListener a);

    void addOpenFolioListener(ActionListener a);

    JTabbedPane getTabbedPane();

}
