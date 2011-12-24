package heraclite.gui.amortissement;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

public class AmortissementSelector extends JPanel {

  private static final long serialVersionUID = 1L;
  private JSlider selector;
  private boolean used;    
  private List<ChangeListener> listeners = new ArrayList<>();


  public AmortissementSelector() {
    this.setBorder(BorderFactory.createBevelBorder(0));
    this.setLayout(new GridLayout(1, 1));
    selector = new JSlider();
    selector.setMaximum(0);
    selector.setMinorTickSpacing(1);
    selector.setPaintTicks(true);
    disable();
    this.add(selector);
  }

  public void clear() {
    selector.setMaximum(0);
  }

  public void enable() {
    selector.setEnabled(true);
  }

  public void disable() {
    selector.setEnabled(false);
  }

  public void setMaximum(int extrants) {
    selector.setMaximum(extrants);
  }
  
  public int getValue() {
    return selector.getValue();
  }
  
  public boolean hasBeenUsed() {
    return used;
  }
  
  public void setHasBeenUsed(boolean value) {
    this.used = value;
  }
  
  public void addChangeListener(ChangeListener listener) {
    listeners.add(listener);
    selector.addChangeListener(listener);
  }
  
  public void disableListeners() {
    for (ChangeListener listener : listeners) {
      selector.removeChangeListener(listener);
    }
  }
  
  public void enableListeners() {
    for (ChangeListener listener : listeners) {
      selector.addChangeListener(listener);
    }
  }
}
