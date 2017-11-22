import javax.swing.*;
import java.awt.event.ActionListener;

public class BinaryDialogFrame extends JFrame {
    private JTextField nameField;
    private JButton yesButton, noButton;

    BinaryDialogFrame(String name, String message){
        setName(name);

        setSize(400, 100);
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

        setVisible(true);
    }

    public void addYesListener(ActionListener a) {
        yesButton.addActionListener(a);
    }
    public void addNoListener(ActionListener a) {
        noButton.addActionListener(a);
    }

    public JTextField getNameField() {
        return nameField;
    }

    public void close(){dispose();}
}
