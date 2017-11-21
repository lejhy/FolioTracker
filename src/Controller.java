import javax.management.OperationsException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class Controller {

    private IMainFrame mainFrame;



    public Controller(IMainFrame frame) {
        this.mainFrame = frame;

        mainFrame.addCreateFolioListener(new ButtonListener(ButtonListener.type.CREATE_FOLIO));

        mainFrame.addDeleteFolioListener(new ButtonListener(ButtonListener.type.DELETE_FOLIO));

        mainFrame.addOpenFolioListener(new ButtonListener(ButtonListener.type.OPEN_FOLIO));
    }


    public class CreateFolio extends JPanel {
        private JTextField nameField;

        CreateFolio(){
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
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainFrame.addFolioTab(new FolioPanel(nameField.getText(),mainFrame.getData(nameField.getText())));
                    frame.dispose();
                }
            });

        }

        }


    public void deleteFolio() { mainFrame.removeFolioTab(); }

    public Vector<Vector<String>> refreshVector(String name) {
        return mainFrame.getData(name);
    }

    public void modifyFolio(Vector<String> i, String name, int index) {
        mainFrame.modifyFolio(i,name,index);

    }

    public void closeFolio() { mainFrame.closeFolio(); }

    public void openFolio() { mainFrame.openFolio(); }


    public void refresh() { mainFrame.refresh(); }
}


