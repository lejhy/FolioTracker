import javax.swing.*;
import java.awt.event.ActionListener;

public class BuySellFrame extends JFrame implements IBuySellFrame {
    private boolean buy;
    private IFolioPanel folio;
    private int index;
    private JPanel panel;

    public BuySellFrame(String name, boolean buy, IFolioPanel folioPanel, int index) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setName(name);
        setSize(250,150);
        this.buy = buy;
        this.folio = folioPanel;
        this.index = index;
        panel = new JPanel();
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        if(this.buy)
            setAsBuyFrame();
        else
            setAsSellFrame();

    }



    private void setAsBuyFrame() {
        JLabel message = new JLabel("Please enter the amount you wish to buy");
        JLabel label = new JLabel("Amount=");
        JTextField input = new JTextField(20);
        JButton enter = new JButton("Enter");
        ActionListener l = new BuySellListener(buy, input, folio, this, folio.getFolio().getStocks().get(index).getName());

        enter.addActionListener(l);

        panel.add(message);
        panel.add(input);
        panel.add(enter);

        add(panel);

        setVisible(true);
    }

    private void setAsSellFrame() {
    }

    @Override
    public void dispose() {
       super.dispose();
    }
}
