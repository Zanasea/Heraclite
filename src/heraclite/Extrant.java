package heraclite;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

/**
 * Les noms de variable et de méthodes sont en français afin que celles-ci
 * s'écrivent comme telle dans le fichier.
 */
@JsonPropertyOrder({"id", "description", "montant", "nombreAnnee", "frequenceRemboursement",
    "tauxInteret", "frequenceComposition", "versementPeriodique", "amortissement"})
public class Extrant implements Hypotheque {

    private String id;
    private String description;
    private BigDecimal montant;
    private BigDecimal nombreAnnee;
    private BigDecimal frequenceRemboursement;
    private BigDecimal tauxInteret;
    private BigDecimal versementPeriodique;
    private BigDecimal frequenceComposition;
    private Amortissement[] amortissement;
    
    public Extrant () {
        // Constructeur vide requis pour Jackson.
    }
    
    public Extrant(Hypotheque hypotheque) {
        this.id = hypotheque.getId();
        this.description = hypotheque.getDescription();
        this.montant = hypotheque.getMontant();
        this.nombreAnnee = hypotheque.getNombreAnnee();
        this.frequenceRemboursement = hypotheque.getFrequenceRemboursement();
        this.tauxInteret = hypotheque.getTauxInteret();
        this.frequenceComposition = hypotheque.getFrequenceComposition();
        this.amortissement = new Amortissement[this.frequenceRemboursement.multiply(this.nombreAnnee).intValue()];
        this.init();
    }

    public final void init() {
        if (this.frequenceRemboursement.compareTo(BigDecimal.ZERO) <= 0) {
            throw new UnsupportedOperationException("frenquenceRemboursement has to be higher than 0 for this operation");
        }
        if (this.nombreAnnee.compareTo(BigDecimal.ZERO) <= 0) {
            throw new UnsupportedOperationException("nombreAnnee has to be higher than 0 for this operation");
        }

        for (int i = 0; i < this.amortissement.length; ++i) {
            this.amortissement[i] = new Amortissement();
        }
        this.amortissement[0].setCapitalDebut(this.montant);
    }

    public Amortissement[] getAmortissement() {
        return this.amortissement;
    }

    public void setAmortissement(Amortissement[] amortissement) {
        this.amortissement = amortissement;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public BigDecimal getFrequenceComposition() {
        return this.frequenceComposition;
    }

    @Override
    public void setFrequenceComposition(BigDecimal frequenceComposition) {
        this.frequenceComposition = frequenceComposition;
    }

    @Override
    public BigDecimal getFrequenceRemboursement() {
        return this.frequenceRemboursement;
    }

    @Override
    public void setFrequenceRemboursement(BigDecimal frequenceRemboursement) {
        this.frequenceRemboursement = frequenceRemboursement;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public BigDecimal getMontant() {
        return this.montant;
    }

    @Override
    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    @Override
    public BigDecimal getNombreAnnee() {
        return this.nombreAnnee;
    }

    @Override
    public void setNombreAnnee(BigDecimal nombreAnnee) {
        this.nombreAnnee = nombreAnnee;
    }

    @Override
    public BigDecimal getTauxInteret() {
        return this.tauxInteret;
    }

    @Override
    public void setTauxInteret(BigDecimal tauxInteret) {
        this.tauxInteret = tauxInteret;
    }

    public BigDecimal getVersementPeriodique() {
        return this.versementPeriodique.setScale(2, RoundingMode.HALF_UP);
    }

    public void setVersementPeriodique(BigDecimal versementPeriodique) {
        this.versementPeriodique = versementPeriodique;
    }

    @JsonIgnore
    public BigDecimal getTauxInteretPourcent() {
        return this.tauxInteret.divide(new BigDecimal(100));
    }
}
