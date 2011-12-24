package heraclite.gui.amortissement;

import heraclite.dto.Extrant;
import heraclite.gui.ConstraintsFactory;
import heraclite.gui.listeners.AmortissementSelectorListener;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class AmortissementPanel extends JPanel {

  private static final long serialVersionUID = 1L;
  private List<Extrant> extrants = new ArrayList<>();
  private AmortissementTable table;
  private AmortissementSelector selector;

  public AmortissementPanel() {
    super();
    this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    this.setLayout(new GridBagLayout());
    selector = new AmortissementSelector();
    selector.addChangeListener(new AmortissementSelectorListener(this, selector));
    this.add(selector, ConstraintsFactory.createAmortissementSelector());
    this.add(createAmortissementTable(), ConstraintsFactory.createAmortissementTable());
  }

  private JScrollPane createAmortissementTable() {
    table = new AmortissementTable(0, AmortissementHeader.values().length);
    setAmortissementTableHeaderValues();
    table.setBorder(BorderFactory.createBevelBorder(0));
    return new JScrollPane(table);
  }

  private void setAmortissementTableHeaderValues() {
    for (int i = 0; i < table.getColumnCount(); i++) {
      table.getColumnModel().getColumn(i).setHeaderValue(AmortissementHeader.values()[i]);
    }
  }

  public void selectExtrant(final int index) {
    selector.setHasBeenUsed(true);
    table.clearAndAddData(extrants.get(index));
  }

  synchronized public void addExtrant(Extrant extrant) {
    selector.disableListeners();
    extrants.add(extrant);
    selector.enable();
    selector.setMaximum(extrants.size() - 1);
    if (!selector.hasBeenUsed()) {
      selectExtrant(0);
    }
    selector.enableListeners();
  }

  public void clearExtrants() {
    extrants.clear();
  }

  public AmortissementSelector getSelector() {
    return selector;
  }
}
