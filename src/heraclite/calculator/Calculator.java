package heraclite.calculator;

import static java.math.BigDecimal.ONE;
import heraclite.dto.Amortissement;
import heraclite.dto.Extrant;
import heraclite.dto.Intrant;
import heraclite.gui.GUI;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe permettant de calculer les modalités de remboursement du prêt pour
 * ensuite l'écrire dans un fichier.
 */
public class Calculator extends Thread {

  private Intrant intrant;
  private Extrant extrant;
  private PersistanceManager persistanceManager;
  
  private static List<GUI> guis = new ArrayList<GUI>();
  private static File inputDirectory;
  private static File originalOutputDirectory;
  private static File originalHtmlOutputDirectory;

  public static void calculate(String[] args) {
    inputDirectory = new File(args[0]);
    originalOutputDirectory = new File(args[1]);
    originalHtmlOutputDirectory = new File(args[2]);

    SourceReader sourceReader = new SourceReader(inputDirectory);

    while (sourceReader.hasNext()) {
      final File inputFile = sourceReader.next();

      File outputDirectory = parseFinalOutputDirectory(inputFile, originalOutputDirectory);
      File htmlOutputDirectory = parseFinalOutputDirectory(inputFile, originalHtmlOutputDirectory);

      PersistanceManager persistanceManager = new JSONFileManager(inputFile, outputDirectory, htmlOutputDirectory);
      Calculator calculator = new Calculator(persistanceManager);
      calculator.start();
    }
  }
  
  public Calculator(PersistanceManager persistanceManager) {
    this.persistanceManager = persistanceManager;
  }

  @Override
  public void run() {
    intrant = persistanceManager.read();
    if (intrant != null) {
      extrant = new Extrant(intrant);
      calculerExtrant();
      fireExtrantCalculated();
      persistanceManager.write(extrant);
    }
  }
  
  private void fireExtrantCalculated() {
    for (GUI gui : guis) {
      gui.addExtrant(extrant);
    }
  }

  private BigDecimal monthlyPayment() {

    BigDecimal monthlyPayment = BigDecimal.ZERO;
    BigDecimal paymentAmount = intrant.getNombreAnnee().multiply(intrant.getFrequenceRemboursement());
    BigDecimal compoundInterest = calculateCompoundInterest();

    BigDecimal divisor = ONE.subtract(new BigDecimal(Math.pow(ONE.add(compoundInterest).doubleValue(), -paymentAmount.doubleValue())));
    monthlyPayment = intrant.getMontant().multiply(compoundInterest.divide(divisor, 52, RoundingMode.HALF_UP));

    return monthlyPayment;
  }

  private BigDecimal calculateCompoundInterest() {
    final BigDecimal compositionFrequency = intrant.getFrequenceComposition();
    final BigDecimal interestRatesPercent = intrant.getTauxInteretPourcent();
    final BigDecimal repaymentFrequency = intrant.getFrequenceRemboursement();

    BigDecimal interestRates = ONE.add(interestRatesPercent.divide(compositionFrequency, 52, RoundingMode.HALF_UP));
    BigDecimal period = ONE.divide(repaymentFrequency.divide(compositionFrequency, 52, RoundingMode.HALF_UP), 52, RoundingMode.HALF_UP);

    return new BigDecimal(Math.pow(interestRates.doubleValue(), period.doubleValue())).subtract(ONE);
  }

  private void calculerExtrant() {

    BigDecimal monthlyPayment = this.monthlyPayment().setScale(2, RoundingMode.HALF_UP);
    BigDecimal startingCapital = extrant.getMontant();
    BigDecimal interest = calculateCompoundInterest();
    BigDecimal totalCumulativePayment = BigDecimal.ZERO;
    BigDecimal cumulativeInterestPayment = BigDecimal.ZERO;
    BigDecimal cumulativeCapitalPayment = BigDecimal.ZERO;

    extrant.setVersementPeriodique(monthlyPayment);
    final int amortizationAmount = intrant.getFrequenceRemboursement().multiply(intrant.getNombreAnnee()).intValue();

    for (int i = 0; i < amortizationAmount; ++i) {
      Amortissement amortization = extrant.getAmortissement()[i];

      BigDecimal interestPayment = interest.multiply(startingCapital).setScale(2, RoundingMode.HALF_UP);

      if (i == amortizationAmount - 1) {
        monthlyPayment = startingCapital.add(interestPayment);
      }

      BigDecimal capitalPayment = monthlyPayment.subtract(interestPayment);
      BigDecimal endingCapital = startingCapital.subtract(capitalPayment);

      totalCumulativePayment = totalCumulativePayment.add(monthlyPayment);
      cumulativeInterestPayment = cumulativeInterestPayment.add(interestPayment);
      cumulativeCapitalPayment = cumulativeCapitalPayment.add(capitalPayment);

      amortization.setPeriode(i + 1);
      amortization.setCapitalDebut(startingCapital);
      amortization.setVersementTotal(monthlyPayment);
      amortization.setVersementInteret(interestPayment);
      amortization.setVersementCapital(capitalPayment);
      amortization.setCapitalFin(endingCapital);
      amortization.setVersementTotalCumulatif(totalCumulativePayment);
      amortization.setVersementInteretCumulatif(cumulativeInterestPayment);
      amortization.setVersementCapitalCumulatif(cumulativeCapitalPayment);

      startingCapital = endingCapital;
    }
  }

  private static  File parseFinalOutputDirectory(File inputFile, File outputDirectory) {
    String filePath = inputFile.getAbsolutePath();
    filePath = removeParentDirectoryFromAbsolutePath(filePath, inputDirectory);
    filePath = removeFileNameFromPath(filePath, inputFile);
    String resultingParentDirectory = outputDirectory.getAbsolutePath() + filePath;
    return new File(resultingParentDirectory);
  }

  private static String removeFileNameFromPath(String path, File file) {
    return path.substring(0, path.lastIndexOf(file.getName()));
  }

  private static String removeParentDirectoryFromAbsolutePath(String path, File directory) {
    return path.substring(directory.getAbsolutePath().length());
  }
  
  public static void registerGUI(GUI gui) {
    guis.add(gui);
  }
}
