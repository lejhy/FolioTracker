import javax.swing.*;
import java.awt.*;

public class alertFrame extends JFrame{

    public alertFrame(String name, String message) {
        setName(name);
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new FlowLayout());

        JLabel label = new JLabel(message);

        add(label);

        JButton okButton = new JButton("OK");

        okButton.addActionListener(a -> {
            dispose();
        });
        add(okButton);

        setVisible(true);
    }

}
