package heraclite.dto;

import java.math.BigDecimal;
import javax.management.InvalidAttributeValueException;

public interface Hypotheque {

  public String getDescription();

  public void setDescription(String description);

  public BigDecimal getFrequenceComposition();

  public void setFrequenceComposition(BigDecimal frequenceComposition) throws InvalidAttributeValueException;

  public BigDecimal getFrequenceRemboursement();

  public void setFrequenceRemboursement(BigDecimal frequenceRemboursement) throws InvalidAttributeValueException;

  public String getId();

  public void setId(String id);

  public BigDecimal getMontant();

  public void setMontant(BigDecimal montant) throws InvalidAttributeValueException;

  public BigDecimal getNombreAnnee();

  public void setNombreAnnee(BigDecimal nombreAnnee) throws InvalidAttributeValueException;

  public BigDecimal getTauxInteret();

  public void setTauxInteret(BigDecimal tauxInteret) throws InvalidAttributeValueException;
}
