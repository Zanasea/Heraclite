package heraclite;

public interface PersistanceManager {

  public void write(Extrant extrant);

  public Intrant read();

}
