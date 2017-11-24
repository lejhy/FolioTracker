package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public interface IMainFrame {

    boolean addFolioTab(Component folioTab);

    boolean removeFolioTab();

    boolean openFolio();

    boolean closeFolio();

    void addCreateListener(ActionListener a);

    void addOpenListener(ActionListener a);

    void addExitListener(ActionListener a);

    JTabbedPane getTabbedPane();
}
