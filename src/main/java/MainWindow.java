import javax.swing.*;

public class MainWindow implements IView {
    JFrame frame;
    JPanel panel1;
    JButton refresh;
    JButton createPortfolio;
    JButton deletePortfolio;
    JPanel portfolio;

    public MainWindow() {
        JFrame frame = new JFrame("Folio Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
