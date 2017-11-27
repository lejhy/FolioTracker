package View;

import java.awt.event.ActionListener;

public interface IBinaryDialogFrame {

    void addYesListener(ActionListener a);

    void addNoListener(ActionListener a);

    void dispose();
}
