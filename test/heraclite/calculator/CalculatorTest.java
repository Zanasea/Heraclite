package heraclite.calculator;

import heraclite.dto.Amortissement;
import heraclite.dto.Extrant;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class CalculatorTest {

    private Calculator classUnderTest;

    @Before
    public void setup() {
        File sourceFile = new File("resources/jsonTest.json");
        File outputDirectory = new File("testResults/");
        File htmlOutputDirectory = new File("testResults/");
        PersistanceManager persistanceManager = new JSONFileManager(sourceFile, outputDirectory, htmlOutputDirectory);
        classUnderTest = new Calculator(persistanceManager);
        outputDirectory.deleteOnExit();
    }

    @Test
    public void testResultingFileCreationAndAccuracy() throws Exception {
        assertResultingFileDoesNotExist();

        classUnderTest.start();
        classUnderTest.join();
        
        assertResultingFileExistsAndDeleteItOnExit();

        assertThatResultingFileHasAcceptableValues();
        
    }

    private void assertResultingFileExistsAndDeleteItOnExit() {
        File outputFile = new File("testResults/jsonTest.json");
        Assert.assertTrue(outputFile.exists());
        outputFile.deleteOnExit();
    }

    private void assertResultingFileDoesNotExist() {
        File outputFile = new File("testResults/jsonTest.json");
        outputFile.delete();
        Assert.assertFalse(outputFile.exists());
    }

    private void assertThatResultingFileHasAcceptableValues() throws Exception {
        File outputFile = new File("testResults/jsontest.json");
        ObjectMapper mapper = new ObjectMapper();
        Extrant testResult = mapper.readValue(outputFile, Extrant.class);
        
        assertValueIsRight("Test 1", testResult.getId());
        assertValueIsRight("Premier test", testResult.getDescription());
        assertValueIsRight(new BigDecimal("2"), testResult.getFrequenceComposition());
        assertValueIsRight(new BigDecimal("12"), testResult.getFrequenceRemboursement());
        assertValueIsRight(new BigDecimal("100000"), testResult.getMontant());
        assertValueIsRight(new BigDecimal("25"), testResult.getNombreAnnee());
        assertValueIsRight(new BigDecimal("6"), testResult.getTauxInteret());
        assertValueIsRight(new BigDecimal("639.81"), testResult.getVersementPeriodique());
        
        Amortissement firstAmortization = testResult.getAmortissement()[0];
        assertValueIsRight(1, firstAmortization.getPeriode());
        assertValueIsRight(new BigDecimal("100000.00"), firstAmortization.getCapitalDebut());
        assertValueIsRight(new BigDecimal("639.81"), firstAmortization.getVersementTotal());
        assertValueIsRight(new BigDecimal("493.86"), firstAmortization.getVersementInteret());
        assertValueIsRight(new BigDecimal("145.95"), firstAmortization.getVersementCapital());
        assertValueIsRight(new BigDecimal("99854.05"), firstAmortization.getCapitalFin());
        assertValueIsRight(new BigDecimal("639.81"), firstAmortization.getVersementTotalCumulatif());
        assertValueIsRight(new BigDecimal("493.86"), firstAmortization.getVersementInteretCumulatif());
        assertValueIsRight(new BigDecimal("145.95"), firstAmortization.getVersementCapitalCumulatif());
    }

    private void assertValueIsRight(Object expectedResult, Object actualResult) {
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testParseFinalOutputDirectoryMethod() throws Exception {
        Method parseFinalOutputDirectory = prepareParseFinalOutputDirectoryMethod();
        assertParentDirectoryIsAccuratelyParsed(parseFinalOutputDirectory);
    }

    private Method prepareParseFinalOutputDirectoryMethod() throws Exception {
        Method parseFinalOutputDirectory = Calculator.class.getDeclaredMethod("parseFinalOutputDirectory", File.class, File.class);
        Field inputDirectory = Calculator.class.getDeclaredField("inputDirectory");
        Field originalOutputDirectory = Calculator.class.getDeclaredField("originalOutputDirectory");
        parseFinalOutputDirectory.setAccessible(true);
        inputDirectory.setAccessible(true);
        originalOutputDirectory.setAccessible(true);
        inputDirectory.set(classUnderTest, new File("resources"));
        originalOutputDirectory.set(classUnderTest, new File("testResults"));
        return parseFinalOutputDirectory;
    }

    private void assertParentDirectoryIsAccuratelyParsed(Method parseFinalOutputDirectory) throws Exception {
        File inputFile = new File("resources/subFolder/jsonTest from subfolder.json");
        File expectedResult = new File("testResults/subFolder/");
        File outputDirectory = new File("testResults/");
        File testResult = (File)parseFinalOutputDirectory.invoke(classUnderTest, inputFile, outputDirectory);
        Assert.assertEquals(expectedResult.getAbsolutePath(), testResult.getAbsolutePath());
    }
}

