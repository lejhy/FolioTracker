import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class folioTableListener implements TableModelListener {

    private FolioPanel folioPanel;

    public folioTableListener(FolioPanel folioPanel) {
        this.folioPanel = folioPanel;
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        System.out.println(e.getColumn());
        System.out.println(e.getFirstRow());
        System.out.println(e.getLastRow());

        IStock stock = folioPanel.getFolio().getStocks().get(e.getFirstRow());
        TableModel tableModel = (TableModel)e.getSource();

        switch (e.getColumn()) {
            case 1:
                stock.setName(tableModel.getValueAt(e.getFirstRow(), 1).toString());
                break;
            case 2:
                stock.setNumber(Integer.parseInt(tableModel.getValueAt(e.getFirstRow(), 2).toString()));
                folioPanel.getStockTable().getModel().setValueAt(stock.getPrice(), e.getFirstRow(), 4);
                break;
            default:
                break;
        }
    }


}
