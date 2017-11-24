package View;

import java.awt.*;
import java.awt.event.ActionListener;

public interface IMainFrame {

    void addFolioTab(Component folioTab);

    void removeFolioTab();

    void addCreateListener(ActionListener a);

    void addOpenListener(ActionListener a);

}
