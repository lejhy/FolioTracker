import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateFolioFrame extends JFrame {
    
    private JTextField nameField;

    CreateFolioFrame(){
        setName("Create Folio");

        setSize(300, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new FlowLayout());

        JLabel label = new JLabel("Enter name of Portfolio ");

        add(label);

        nameField = new JTextField(15);

        add(nameField);

        JButton btn = new JButton("Enter");
        add(btn);

        setVisible(true);
    }

    public void addConfirmationListener(ActionListener a) { }

}