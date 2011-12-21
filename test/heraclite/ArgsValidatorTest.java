package heraclite;

import static heraclite.ArgsValidator.isNoGUIValid;

import java.io.File;
import java.io.IOException;
import junit.framework.Assert;
import org.junit.Test;

public class ArgsValidatorTest {

  @Test
  public void testIsValidWithAllThreeArgumentsBeingDirectories() {
    Assert.assertTrue(isNoGUIValid(new String[] { ".", ".", "." }));
  }

  @Test
  public void testIsNotValidWithAllThreeArgumentsBeingDirectoriesButWithNoGuiOption() {
    Assert.assertFalse(isNoGUIValid(new String[] { ".", ".", ".", "--gui" }));
  }

  @Test
  public void testIsNotValidWithAllThreeArgumentsBeingDirectoriesButWithNoGuiOptionSomewhereInBetweenTheDirectories() {
    Assert.assertFalse(isNoGUIValid(new String[] { ".", "--gui", ".", "." }));
  }

  @Test
  public void testNoArgumentIsntValid() {
    Assert.assertFalse(isNoGUIValid(new String[] {}));
  }

  @Test
  public void testNoGuiArgumentIsntValid() {
    Assert.assertFalse(isNoGUIValid(new String[] {"--gui"}));
  }

  @Test
  public void testOneArgumentIsntValid() {
    Assert.assertFalse(isNoGUIValid(new String[] { "1" }));
  }

  @Test
  public void testTwoArgumentIsntValid() {
    Assert.assertFalse(isNoGUIValid(new String[] { "1", "2" }));
  }

  @Test
  public void testFirstArgumentIsntADirectoryIsNotValid() {
    Assert.assertFalse(isNoGUIValid(new String[] { "inputDir", ".", "." }));
  }

  @Test
  public void testSecondArgumentIsntDirectoryIsNotValid() throws IOException {
    File testFile = new File("invalidOutput");

    testFile.createNewFile();
    testFile.deleteOnExit();

    Assert.assertFalse(isNoGUIValid(new String[] { ".", "invalidOutput", "." }));
  }

  @Test
  public void testThirdArgumentIsntDirectoryIsNotValid() throws IOException {
    File testFile = new File("invalidOutput");

    testFile.createNewFile();
    testFile.deleteOnExit();

    Assert.assertFalse(isNoGUIValid(new String[] { ".", ".", "invalidOutput" }));
  }
}
