package ca.uqam.inf2015.aut2011.tp2.heraclite;

public interface PersistanceManager {
    
    public void write(Extrant extrant);
    public Intrant read();
    
    
}
