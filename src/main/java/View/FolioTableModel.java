package View;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class FolioTableModel extends DefaultTableModel {

    public FolioTableModel(Vector<Object> columns, int rows) {
        super(columns, rows);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        if (column == 1) {
            return true;
        } else {
            return false;
        }
    }
}
