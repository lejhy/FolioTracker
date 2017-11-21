import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class folioTableListener implements TableModelListener {

    private IFolio folio;

    public folioTableListener(IFolio folio) {
        this.folio = folio;
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        System.out.println(e.getColumn());
        System.out.println(e.getFirstRow());
        System.out.println(e.getLastRow());

        IStock stock = folio.getStocks().get(e.getFirstRow());
        TableModel tableModel = (TableModel)e.getSource();

        switch (e.getColumn()) {
            case 1:
                stock.setName(tableModel.getValueAt(e.getFirstRow(), e.getColumn()).toString());
                break;
            case 2:
                stock.setNumber(Integer.parseInt(tableModel.getValueAt(e.getFirstRow(), e.getColumn()).toString()));
                break;
            default:
                break;
        }
    }
}
