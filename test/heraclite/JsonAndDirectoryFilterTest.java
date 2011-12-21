/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heraclite;

import heraclite.JsonAndDirectoryFilter;

import java.io.File;
import junit.framework.Assert;
import org.junit.Test;

public class JsonAndDirectoryFilterTest {

    JsonAndDirectoryFilter classUnderTest = new JsonAndDirectoryFilter();
    
    @Test
    public void testAcceptExistingDirectory() {
        File directory = new File(".");
        Assert.assertTrue(classUnderTest.accept(directory));
    }
    
    @Test
    public void testAcceptFileWithoutExtensionReturnsFalse() {
        File extensionlessFile = new File("./fileWithoutExtention");
        Assert.assertFalse(classUnderTest.accept(extensionlessFile));
    }
    
    @Test
    public void testAcceptFileWithExtensionThatIsNotJsonReturnsFalse() {
        File notJsonFile = new File("./fileWithExtension.json1");
        Assert.assertFalse(classUnderTest.accept(notJsonFile));
    }
    
    @Test
    public void testAcceptFileWithManyExtensionAndFinalOneIsNotJsonReturnsFalse() {
        File notJsonFile = new File("./fileWithExtensions.json.json1");
        Assert.assertFalse(classUnderTest.accept(notJsonFile));
    }
    
    @Test
    public void testAcceptFileWithExtensionIsJson() {
        File jsonFile = new File("./fileWithExtension.json");
        Assert.assertTrue(classUnderTest.accept(jsonFile));
    }
    
    @Test
    public void testAcceptFileWithManyExtensionsAndFinalOneIsJson() {
        File jsonFileWithManyExtension = new File("./fileWithExtensions.json1.json");
        Assert.assertTrue(classUnderTest.accept(jsonFileWithManyExtension));
    }
    
    @Test
    public void testAcceptFileWithFileNameHasNothingBeforeExtensionAndItIsAJsonFile() {
        File jsonFileWithManyExtension = new File("./.json1.json");
        Assert.assertTrue(classUnderTest.accept(jsonFileWithManyExtension));
    }
}
