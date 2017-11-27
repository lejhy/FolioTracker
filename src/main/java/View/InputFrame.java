package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InputFrame extends JFrame implements IInputFrame {

    private JTextField inputField;
    private JButton enterButton;

    public InputFrame(String name, String message){
        setName(name);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new FlowLayout());

        JLabel label = new JLabel(message);

        add(label);

        inputField = new JTextField(15);

        add(inputField);

        enterButton = new JButton("Enter");
        add(enterButton);

        pack();
        setVisible(true);
    }

    @Override
    public void addConfirmationListener(ActionListener a) {
        enterButton.addActionListener(a);
    }

    @Override
    public JTextField getInputField() {
        return inputField;
    }
}