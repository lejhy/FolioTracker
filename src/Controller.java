import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {

    private IMainFrame mainFrame;



    public Controller(IMainFrame frame) {
        this.mainFrame = frame;
        setupListeners();


    }

    private void setupListeners() {
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {


            }

        };
    }



    public class GetUserInput extends JPanel {

        private JTextField nameField;

        GetUserInput(){
            JFrame frame = new JFrame("Test");

            frame.setVisible(true);
            frame.setSize(300, 300);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLayout(new BorderLayout());


            frame.setLayout(new FlowLayout());
            JLabel label = new JLabel("Enter name of Portfolio ");
            label.setVisible(true);
            frame.add(label);

            nameField = new JTextField(15);
            nameField.setVisible(true);
            frame.add(nameField);

            JButton btn = new JButton("Enter");
            frame.add(btn);
            btn.setVisible(true);
            btn.addActionListener(new BtnListener());

        }



        private class BtnListener implements ActionListener {


            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.addFolioTab(new FolioPanel(nameField.getText()));


            }


            }

        }



}
