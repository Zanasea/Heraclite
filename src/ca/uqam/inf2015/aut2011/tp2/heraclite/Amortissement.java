package ca.uqam.inf2015.aut2011.tp2.heraclite;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

/**
 * Les noms de variable et de méthodes sont en français afin que celles-ci
 * s'écrivent comme telle dans le fichier.
 */
@JsonPropertyOrder({"periode", "capitalDebut", "versementTotal", "versementInteret", "versementCapital", 
    "capitalFin", "versementTotalCumulatif", "versementInteretCumulatif", "versementCapitalCumulatif"})
public class Amortissement {

    private int periode;
    private BigDecimal capitalDebut;
    private BigDecimal versementTotal;
    private BigDecimal versementTotalCumulatif;
    private BigDecimal versementInteret;
    private BigDecimal versementInteretCumulatif;
    private BigDecimal versementCapital;
    private BigDecimal versementCapitalCumulatif;
    private BigDecimal capitalFin;

    public BigDecimal getVersementCapitalCumulatif() {
        return versementCapitalCumulatif.setScale(2, RoundingMode.HALF_UP);
    }

    public void setVersementCapitalCumulatif(BigDecimal versementCapitalCumulatif) {
        this.versementCapitalCumulatif = versementCapitalCumulatif;
    }

    public BigDecimal getVersementInteretCumulatif() {
        return versementInteretCumulatif.setScale(2, RoundingMode.HALF_UP);
    }

    public void setVersementInteretCumulatif(BigDecimal versementInteretCumulatif) {
        this.versementInteretCumulatif = versementInteretCumulatif;
    }

    public BigDecimal getVersementTotalCumulatif() {
        return versementTotalCumulatif.setScale(2, RoundingMode.HALF_UP);
    }

    public void setVersementTotalCumulatif(BigDecimal versementTotalCumulatif) {
        this.versementTotalCumulatif = versementTotalCumulatif;
    }

    public BigDecimal getCapitalDebut() {
        return capitalDebut.setScale(2, RoundingMode.HALF_UP);
    }

    public void setCapitalDebut(BigDecimal capitalDebut) {
        this.capitalDebut = capitalDebut;
    }

    public BigDecimal getCapitalFin() {
        return capitalFin.setScale(2, RoundingMode.HALF_UP);
    }

    public void setCapitalFin(BigDecimal capitalFin) {
        this.capitalFin = capitalFin;
    }

    public int getPeriode() {
        return periode;
    }

    public void setPeriode(int periode) {
        this.periode = periode;
    }

    public BigDecimal getVersementCapital() {
        return versementCapital.setScale(2, RoundingMode.HALF_UP);
    }

    public void setVersementCapital(BigDecimal versementCapital) {
        this.versementCapital = versementCapital;
    }

    public BigDecimal getVersementInteret() {
        return versementInteret.setScale(2, RoundingMode.HALF_UP);
    }

    public void setVersementInteret(BigDecimal versementInteret) {
        this.versementInteret = versementInteret;
    }

    public BigDecimal getVersementTotal() {
        return versementTotal.setScale(2, RoundingMode.HALF_UP);
    }

    public void setVersementTotal(BigDecimal versementTotal) {
        this.versementTotal = versementTotal;
    }
}
