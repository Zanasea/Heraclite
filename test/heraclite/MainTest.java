package heraclite;

import heraclite.Main;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import junit.framework.Assert;
import org.junit.Test;

public class MainTest {

    @Test
    public void testParseFinalOutputDirectoryMethod() throws Exception {
        Method parseFinalOutputDirectory = prepareParseFinalOutputDirectoryMethod();
        assertParentDirectoryIsAccuratelyParsed(parseFinalOutputDirectory);
    }

    private Method prepareParseFinalOutputDirectoryMethod() throws Exception {
        Method parseFinalOutputDirectory = Main.class.getDeclaredMethod("parseFinalOutputDirectory", File.class, File.class);
        Field inputDirectory = Main.class.getDeclaredField("inputDirectory");
        Field originalOutputDirectory = Main.class.getDeclaredField("originalOutputDirectory");
        parseFinalOutputDirectory.setAccessible(true);
        inputDirectory.setAccessible(true);
        originalOutputDirectory.setAccessible(true);
        inputDirectory.set(Main.class, new File("resources"));
        originalOutputDirectory.set(Main.class, new File("testResults"));
        return parseFinalOutputDirectory;
    }

    private void assertParentDirectoryIsAccuratelyParsed(Method parseFinalOutputDirectory) throws Exception {
        File inputFile = new File("resources/subFolder/jsonTest from subfolder.json");
        File expectedResult = new File("testResults/subFolder/");
        File outputDirectory = new File("testResults/");
        File testResult = (File)parseFinalOutputDirectory.invoke(Main.class, inputFile, outputDirectory);
        Assert.assertEquals(expectedResult.getAbsolutePath(), testResult.getAbsolutePath());
    }
}
