package View;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class FolioTableModel extends DefaultTableModel {

    FolioTableModel(Vector<Object> columns, int rows) {
        super(columns, rows);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return column == 1;
    }
}
