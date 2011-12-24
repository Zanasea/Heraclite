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
  
  synchronized public void clearAndAddData(Extrant extrant) {
    clearData();
    addData(extrant);
  }

  synchronized public void clearData() {
    DefaultTableModel model = (DefaultTableModel) this.getModel();
    model.setRowCount(0);
  }

  synchronized public void addData(Extrant extrant) {
    DefaultTableModel model = (DefaultTableModel) this.dataModel;
    for (Amortissement amortissement : extrant.getAmortissement()) {
      String[] values = amortissement.toArray();
      model.addRow(values);
    }
  }

}
