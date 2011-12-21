package ca.uqam.inf2015.aut2011.tp2.heraclite;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HTMLWriter {

    private File htmlFile;
    private Extrant extrant;

    public HTMLWriter(File htmlFile, Extrant extrant) {
        this.extrant = extrant;
        this.htmlFile = htmlFile;
    }

    public void writeToHtml() {
        try {
            FileWriter fstream = new FileWriter(this.htmlFile + ".html", false);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write("<html>" + getLineSeparator() + "<table>" + getLineSeparator());
            out.write(generateExtrantId());
            out.write(generateExtrantDescription());
            out.write(generateExtrantMontant());
            out.write(generateExtrantNombreAnnee());
            out.write(generateExtrantFrequenceRemboursement());
            out.write(generateExtrantTauxInteret());
            out.write(generateExtrantFrequenceComposition());
            out.write(generateExtrantVersementPeriodique());
            out.write("</table>");
            out.flush();
            
            out.write(generateTable());
            out.write(generateTableHeader());
            generateAmortissementRows(out);
            out.write("</table></html>");
            out.flush();

            out.close();
            fstream.close();
        } catch (Exception exception) {
            System.err.println("We could not write the HTML of the JSON file : " + htmlFile.getName());
            System.err.println("For your convenience, here is the exception message :" + getLineSeparator() + exception.getMessage());
        }
    }

    private String generateTable() {
        return "<html>" + getLineSeparator() + "<table border=\"1\" " + addTableCss() + "><br>" + getLineSeparator();
    }

    private void generateAmortissementRows(BufferedWriter out) throws IOException {
        for (Amortissement amortissement : extrant.getAmortissement()) {
            out.write("<tr>" + generateTd() + amortissement.getPeriode() + " </td>");
            out.write(generateTd() + amortissement.getCapitalDebut() + "$</td>");
            out.write(generateTd() + amortissement.getVersementTotal() + "$</td>");
            out.write(generateTd() + amortissement.getVersementInteret() + "$</td>");
            out.write(generateTd() + amortissement.getVersementCapital() + "$</td>");
            out.write(generateTd() + amortissement.getCapitalFin() + "$</td>");
            out.write(generateTd() + amortissement.getVersementTotalCumulatif() + "$</td>");
            out.write(generateTd() + amortissement.getVersementInteretCumulatif() + "$</td>");
            out.write(generateTd() + amortissement.getVersementCapitalCumulatif() + "$</td>");
            out.write("</tr>" + getLineSeparator());
            out.flush();
        }
    }

    private String generateTd() {
        return "<td" + addTdCss() + ">";
    }

    private String generateExtrantVersementPeriodique() {
        return "<tr><td>Versement periodique</td><td>" + extrant.getVersementPeriodique() + "$</td></tr>" + getLineSeparator();
    }

    private String generateExtrantFrequenceComposition() {
        return "<tr><td>Frequence de composition</td><td>" + extrant.getFrequenceComposition() + "</td></tr>" + getLineSeparator();
    }

    private String generateExtrantTauxInteret() {
        return "<tr><td>Taux d'interet</td><td>" + extrant.getTauxInteret() + "</td></tr>" + getLineSeparator();
    }

    private String generateExtrantFrequenceRemboursement() {
        return "<tr><td>Frequence de remboursement</td><td>" + extrant.getFrequenceRemboursement() + "</td></tr>" + getLineSeparator();
    }

    private String generateExtrantNombreAnnee() {
        return "<tr><td>Nombre d'annee</td><td>" + extrant.getNombreAnnee() + "</td></tr>" + getLineSeparator();
    }

    private String generateExtrantMontant() {
        return "<tr><td>Montant</td><td>" + extrant.getMontant() + "$</td></tr>" + getLineSeparator();
    }

    private String generateExtrantDescription() {
        return "<tr><td>Description</td><td>" + extrant.getDescription() + "</td></tr>" + getLineSeparator();
    }

    private String generateExtrantId() {
        return "<tr><td>Id</td><td>" + extrant.getId() + "</td></tr>" + getLineSeparator();
    }

    private String generateTableHeader() {
        return "<tr><th" + addTdCss() + ">Periode</th><th" + addTdCss() + ">Capital debut</th><th" + addTdCss() + ">Versement total</th><th" + addTdCss() + ">Versement interet</th>"
                + "<th" + addTdCss() + ">Versement capital</th><th" + addTdCss() + ">Capital fin</th><th" + addTdCss() + ">Versement total cumulatif</th>"
                + "<th" + addTdCss() + ">Versement interet cumulatif</th><th" + addTdCss() + ">Versement capital cumulatif</th></tr>" + getLineSeparator();
    }

    private String getLineSeparator() {
        return System.getProperty("line.separator");
    }

    private String addTableCss() {
        return " style=\"padding:2px;border-collapse:collapse;text-align:center\" ";
    }

    private String addTdCss() {
        return " style=\"padding-left:10px;padding-right:10px;\" ";
    }
}
