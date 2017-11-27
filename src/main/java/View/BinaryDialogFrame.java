package View;

import javax.swing.*;
import java.awt.event.ActionListener;

public class BinaryDialogFrame extends JFrame implements IBinaryDialogFrame {
    private JButton yesButton, noButton;

    public BinaryDialogFrame(String name, String message){
        setName(name);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

        JLabel messageLabel = new JLabel(message);

        JPanel messagePanel = new JPanel();
        messagePanel.add(messageLabel);
        add(messagePanel);

        JPanel buttonPanel = new JPanel();
        yesButton = new JButton("Yes");
        noButton = new JButton("No");

        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        add(buttonPanel);

        pack();
        setVisible(true);
    }

    @Override
    public void addYesListener(ActionListener a) {
        yesButton.addActionListener(a);
    }

    @Override
    public void addNoListener(ActionListener a) {
        noButton.addActionListener(a);
    }
}
