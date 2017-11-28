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
        Container container = getContentPane();

        setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));

        JLabel label = new JLabel(message);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        container.add(label);

        JPanel inputPanel = new JPanel();

        inputField = new JTextField(15);
        enterButton = new JButton("Enter");

        inputPanel.add(inputField);
        inputPanel.add(enterButton);

        container.add(inputPanel);

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