import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateFolioFrame extends JFrame {

    private JTextField nameField;
    private JButton enterButton;

    CreateFolioFrame(){
        setName("Create Folio");

        setSize(300, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new FlowLayout());

        JLabel label = new JLabel("Enter name of Portfolio ");

        add(label);

        nameField = new JTextField(15);

        add(nameField);

        enterButton = new JButton("Enter");
        add(enterButton);

        setVisible(true);
    }

    public void addConfirmationListener(ActionListener a) {
        enterButton.addActionListener(a);
    }

    public JTextField getNameField() {
        return nameField;
    }
}