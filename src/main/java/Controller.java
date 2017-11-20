import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {

    private IMainFrame frame;



    public Controller(IMainFrame frame) {
        this.frame = frame;
        setupListeners();


    }

    private void setupListeners() {
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {


            }

        };
    }



}
