package heraclite.gui;

import heraclite.dto.Extrant;

public interface GUI {

  public void init();
  public void display();
  public void addExtrant(Extrant extrant);
}
