import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class FolioTableListener implements TableModelListener {

    private IFolioPanel folioPanel;

    public FolioTableListener(IFolioPanel folioPanel) {
        this.folioPanel = folioPanel;
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        if (folioPanel.getFolio().getStocks().isEmpty()) {
            System.out.println("Empty here");
        } else {
            IStock stock = folioPanel.getFolio().getStocks().get(e.getFirstRow());
            TableModel tableModel = (TableModel) e.getSource();

            switch (e.getColumn()) {
                case 1:
                    stock.setName(tableModel.getValueAt(e.getFirstRow(), 1).toString());
                    break;
                default:
                    break;


            }

        }
    }
}
