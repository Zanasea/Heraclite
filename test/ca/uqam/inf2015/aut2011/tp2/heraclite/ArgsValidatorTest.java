package ca.uqam.inf2015.aut2011.tp2.heraclite;

import static heraclite.ArgsValidator.isValid;

import java.io.File;
import java.io.IOException;
import junit.framework.Assert;
import org.junit.Test;

public class ArgsValidatorTest {

    @Test
    public void testIsValidWithAllThreeArgumentsBeingDirectories() {
        Assert.assertTrue(isValid(new String[]{".", ".", "."}));
    }

    @Test
    public void testNoArgumentIsntValid() {
        Assert.assertFalse(isValid(new String[]{}));
    }
    
    @Test
    public void testOneArgumentIsntValid() {
        Assert.assertFalse(isValid(new String[]{"1"}));
    }
    
    @Test
    public void testTwoArgumentIsntValid() {
        Assert.assertFalse(isValid(new String[]{"1", "2"}));
    }

    @Test
    public void testFirstArgumentIsntADirectoryIsNotValid() {
        Assert.assertFalse(isValid(new String[]{"inputDir", ".", "."}));
    }

    @Test
    public void testSecondArgumentIsntDirectoryIsNotValid() throws IOException {
        File testFile = new File("invalidOutput");

        testFile.createNewFile();
        testFile.deleteOnExit();

        Assert.assertFalse(isValid(new String[]{".", "invalidOutput", "."}));
    }
    
    @Test
    public void testThirdArgumentIsntDirectoryIsNotValid() throws IOException {
        File testFile = new File("invalidOutput");

        testFile.createNewFile();
        testFile.deleteOnExit();

        Assert.assertFalse(isValid(new String[]{".", ".", "invalidOutput"}));
    }
}
