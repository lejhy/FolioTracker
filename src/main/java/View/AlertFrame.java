package View;

import javax.swing.*;
import java.awt.*;

public class AlertFrame extends JFrame{

    public AlertFrame(String name, String message) {
        setName(name);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container container = getContentPane();

        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));

        JLabel label = new JLabel(message);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        container.add(label);

        JButton okButton = new JButton("OK");
        okButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        okButton.addActionListener(a -> {
            dispose();
        });
        container.add(okButton);

        pack();
        setVisible(true);
    }

}
