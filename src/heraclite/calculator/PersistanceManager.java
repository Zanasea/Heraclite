package heraclite.calculator;

import heraclite.dto.Extrant;
import heraclite.dto.Intrant;

public interface PersistanceManager {

  public void write(Extrant extrant);

  public Intrant read();

}
