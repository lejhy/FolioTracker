package View;

import javax.swing.*;
import java.awt.event.ActionListener;

public interface IInputFrame {

    void dispose();

    JTextField getInputField();

    void addConfirmationListener(ActionListener actionListener);
}
