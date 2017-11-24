package View;

import javax.swing.*;
import java.awt.*;

public class AlertFrame extends JFrame{

    public AlertFrame(String name, String message) {
        setName(name);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new FlowLayout());

        JLabel label = new JLabel(message);

        add(label);

        JButton okButton = new JButton("OK");

        okButton.addActionListener(a -> {
            dispose();
        });
        add(okButton);

        pack();
        setVisible(true);
    }

}
