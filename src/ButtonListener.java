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
        switch(buttonPressed) {
            case "create": createPortfolio();
                           break;
            case "open" : openPortfolio();
                          break;
            case "delete" : deletePortfolio();
                           break;
        }
        if(buttonPressed.equals("create"))
            createPortfolio();
        else if(buttonPressed.equals("open"))
            openPortfolio();
        else
            inputGiven(buttonPressed);
    }

    private void deletePortfolio() {
        System.out.println("Delete");
        Controller.deleteFolio();
    }

    private void createPortfolio() {
        System.out.println("create portfolio");
        Controller.CreateFolio in = new Controller.CreateFolio();
    }



    private void openPortfolio(){
        System.out.println("open portfolio");
    }

    private void inputGiven(String input){
        Folio f = new Folio(input);
    }
}
