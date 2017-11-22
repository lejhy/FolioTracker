import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CloseFolioFrame extends JFrame {
    private JTextField nameField;
    private JButton yesButton, noButton;

    CloseFolioFrame(String folioName){
        setName("Close Folio " + folioName);

        setSize(400, 100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

        JLabel label = new JLabel("Would you like to save your work for folio " + folioName + "?");

        JPanel message = new JPanel();
        message.add(label);
        add(message);

        JPanel buttons = new JPanel();
        yesButton = new JButton("Yes");
        noButton = new JButton("No");

        buttons.add(yesButton);
        buttons.add(noButton);

        add(buttons);

        setVisible(true);
    }

    public void addConfirmationListener(ActionListener a) {
        yesButton.addActionListener(a);
    }
    public void addCancelationListener(ActionListener a) {
        noButton.addActionListener(a);
    }

    public JTextField getNameField() {
        return nameField;
    }

    public void close(){dispose();}
}
