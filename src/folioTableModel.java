import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class folioTableModel extends DefaultTableModel {

    public folioTableModel (Vector<Object> columns, int rows) {
        super(columns, rows);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        if (column == 1 || column == 2) {
            return true;
        } else {
            return false;
        }
    }
}
