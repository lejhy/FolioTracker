import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class ButtonListener implements ActionListener {
    String buttonPressed;

    public ButtonListener(String buttonPressed){
        this.buttonPressed = buttonPressed;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(buttonPressed.equals("create"))
            createPotfolio();
        else if(buttonPressed.equals("open"))
            openPortfolio();
        else
            inputGiven(buttonPressed);
    }

    private void createPotfolio() {
        System.out.println("create portfolio");
        GetUserInput in = new GetUserInput();
    }

    private void openPortfolio(){
        System.out.println("open portfolio");
    }

    private void inputGiven(String input){
        Folio f = new Folio(input);
    }
}
