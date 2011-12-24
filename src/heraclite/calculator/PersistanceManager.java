package heraclite.calculator;

import heraclite.dto.Extrant;
import heraclite.dto.Intrant;

public interface PersistanceManager {

  public void write(Extrant extrant) throws RuntimeException;

  public Intrant read() throws RuntimeException;

}
