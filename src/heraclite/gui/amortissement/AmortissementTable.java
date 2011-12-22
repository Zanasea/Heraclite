package heraclite.gui.amortissement;

import heraclite.dto.Amortissement;
import heraclite.dto.Extrant;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AmortissementTable extends JTable {

  private static final long serialVersionUID = -8340670300606522834L;

  public AmortissementTable(int rows, int columns) {
    super(rows, columns);
  }
  
  public void clearData() {
    DefaultTableModel model = (DefaultTableModel)this.getModel();
    while (model.getRowCount() > 0) {
      model.removeRow(0);
    }
  }
  
  public void addData(Extrant extrant) {
    int j = 0;
    for (Amortissement amortissement : extrant.getAmortissement()) {
      String[] values = amortissement.toArray();
      for (int i = 0; i < this.getColumnCount(); i++) {
        this.setValueAt(values[i], j, i);
      }
      j++;
    }
  }
  
}
