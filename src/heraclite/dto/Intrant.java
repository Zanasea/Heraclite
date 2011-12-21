package heraclite.dto;

import java.math.BigDecimal;
import javax.management.InvalidAttributeValueException;


/**
 * Les noms de variable et de méthodes sont en français afin que celles-ci soit
 * associées comme telle depuis le fichier.
 */
public class Intrant implements Hypotheque {

  private String id;
  private String description;
  private BigDecimal montant;
  private BigDecimal nombreAnnee;
  private BigDecimal frequenceRemboursement;
  private BigDecimal tauxInteret;
  private BigDecimal frequenceComposition;

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public BigDecimal getFrequenceComposition() {
    return frequenceComposition;
  }

  @Override
  public void setFrequenceComposition(BigDecimal frequenceComposition) throws InvalidAttributeValueException {
    final String errorMessage = "The composition frequency (fréquence de composition) of the Intrant file must be higher than 0.";
    validateHigherThanZero(frequenceComposition, errorMessage);
    this.frequenceComposition = frequenceComposition;
  }

  @Override
  public BigDecimal getFrequenceRemboursement() {
    return frequenceRemboursement;
  }

  @Override
  public void setFrequenceRemboursement(BigDecimal frequenceRemboursement) throws InvalidAttributeValueException {
    final String errorMessage = "The repayment frequency (fréquence de remboursement) of the Intrant file must be higher than 0.";
    validateHigherThanZero(frequenceRemboursement, errorMessage);
    this.frequenceRemboursement = frequenceRemboursement;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public void setId(String id) {
    this.id = id;
  }

  @Override
  public BigDecimal getMontant() {
    return montant;
  }

  @Override
  public void setMontant(BigDecimal montant) throws InvalidAttributeValueException {
    final String errorMessage = "The amount (montant) of the Intrant file must be higher than 0.";
    validateHigherThanZero(montant, errorMessage);
    this.montant = montant;
  }

  @Override
  public BigDecimal getNombreAnnee() {
    return nombreAnnee;
  }

  @Override
  public void setNombreAnnee(BigDecimal nombreAnnee) throws InvalidAttributeValueException {
    final String errorMessage = "The number of years (nombre d'année) of the Intrant file must be higher than 0.";
    validateHigherThanZero(nombreAnnee, errorMessage);
    this.nombreAnnee = nombreAnnee;
  }

  @Override
  public BigDecimal getTauxInteret() {
    return tauxInteret;
  }

  @Override
  public void setTauxInteret(BigDecimal tauxInteret) throws InvalidAttributeValueException {
    final String errorMessage = "The interest rates (taux d'intérêt) of the Intrant file must be higher than 0.";
    validateHigherThanZero(tauxInteret, errorMessage);
    this.tauxInteret = tauxInteret;
  }

  public BigDecimal getTauxInteretPourcent() {
    return tauxInteret.divide(new BigDecimal(100));
  }

  private void validateHigherThanZero(BigDecimal nombre, String errorMessage) throws InvalidAttributeValueException {
    if (nombre.compareTo(BigDecimal.ZERO) <= 0) {
      throw new InvalidAttributeValueException(errorMessage);
    }
  }
}
