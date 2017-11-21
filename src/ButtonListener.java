import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class ButtonListener implements ActionListener {

    enum type {CREATE_FOLIO, OPEN_FOLIO, DELETE_FOLIO}

    ;
    private type buttonPressed;

    public ButtonListener(type buttonPressed) {
        this.buttonPressed = buttonPressed;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (buttonPressed) {
            case CREATE_FOLIO:
                createPortfolio();
                break;
            case OPEN_FOLIO:
                openPortfolio();
                break;
            case DELETE_FOLIO:
                deletePortfolio();
                break;
        }

    }

    private void deletePortfolio() {
        System.out.println("Delete");
    }

    private void createPortfolio() {
        System.out.println("create portfolio");
    }


    private void openPortfolio() {
        System.out.println("open portfolio");
    }
}
