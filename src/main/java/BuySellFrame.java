import javax.swing.*;
import java.awt.event.ActionListener;

public class BuySellFrame extends JFrame implements IBuySellFrame {
    private boolean buy;
    private IFolioPanel folio;
    private int index;
    private JPanel panel;

    public BuySellFrame(String name, boolean buy, IFolioPanel folioPanel, int index) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setName(name);
        setSize(250,150);
        this.buy = buy;
        this.folio = folioPanel;
        this.index = index;
        panel = new JPanel();
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        setup();
    }



    private void setup() {
        String buyOrSell = "";
        if(buy)
            buyOrSell= " buy";
        else
            buyOrSell=" sell";
        JLabel message = new JLabel("Please enter the amount you wish to" + buyOrSell);
        JTextField input = new JTextField(10);
        JButton enter = new JButton("Enter");
        ActionListener l = new BuySellListener(buy, input, folio, this, folio.getFolio().getStocks().get(index).getName());
        enter.addActionListener(l);

        panel.add(message);
        panel.add(input);
        panel.add(enter);

        add(panel);

        setVisible(true);
    }

    @Override
    public void dispose() {
       super.dispose();
    }
}
