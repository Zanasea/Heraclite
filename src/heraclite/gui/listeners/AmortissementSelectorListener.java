package heraclite.gui.listeners;

import heraclite.gui.amortissement.AmortissementPanel;
import heraclite.gui.amortissement.AmortissementSelector;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class AmortissementSelectorListener implements ChangeListener {

  AmortissementPanel controler;
  AmortissementSelector selector;
  
  public AmortissementSelectorListener(AmortissementPanel panel, AmortissementSelector selector) {
    this.controler = panel;
    this.selector = selector;
  }
  
  @Override
  public void stateChanged(ChangeEvent e) {
    
    controler.selectExtrant(selector.getValue());
  }

}
