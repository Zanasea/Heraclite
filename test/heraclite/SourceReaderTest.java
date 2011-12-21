package heraclite;

import heraclite.SourceReader;

import java.io.File;
import java.util.ArrayList;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class SourceReaderTest {

    private SourceReader classUnderTest;
    private File sourceDirectory = new File("heracliteSourceReaderTest/");
    private File jsonFile;
    private File jsonFile2;
    private File notJsonFile;

    @Before
    public void setup() throws Exception {

        sourceDirectory.mkdirs();
        sourceDirectory.deleteOnExit();
        jsonFile = new File("heracliteSourceReaderTest/test1.json");
        jsonFile2 = new File("heracliteSourceReaderTest/test2.json");
        notJsonFile = new File("heracliteSourceReaderTest/test3.json.jpg");

        jsonFile.createNewFile();
        jsonFile2.createNewFile();
        notJsonFile.createNewFile();
        jsonFile.deleteOnExit();
        notJsonFile.deleteOnExit();
        jsonFile2.deleteOnExit();
        
    }

    @Test
    public void testSourceReaderIgnoresFilesThatAreNotOfJsonType() throws Exception {

        classUnderTest = new SourceReader(sourceDirectory);
        
        Assert.assertTrue(classUnderTest.hasNext());
        classUnderTest.next();
        Assert.assertTrue(classUnderTest.hasNext());
        classUnderTest.next();
        Assert.assertFalse(classUnderTest.hasNext());
    }

    @Test
    public void testSourceReaderReadsFilesInSubFolder() throws Exception {

        File subFolder = new File("heracliteSourceReaderTest/subfolder");
        subFolder.mkdir();
        subFolder.deleteOnExit();
        
        File subJsonFile = new File("heracliteSourceReaderTest/subfolder/subtest1.json");
        subJsonFile.createNewFile();
        subJsonFile.deleteOnExit();
        
        classUnderTest = new SourceReader(sourceDirectory);
        ArrayList<File> filesList = new ArrayList<File>();
        filesList.add(jsonFile);
        filesList.add(jsonFile2);
        filesList.add(notJsonFile);
        filesList.add(subJsonFile);

        Assert.assertTrue(classUnderTest.hasNext());
        classUnderTest.next();
        Assert.assertTrue(classUnderTest.hasNext());
        classUnderTest.next();
        Assert.assertTrue(classUnderTest.hasNext());
        Assert.assertTrue(filesList.contains(classUnderTest.next()));
        Assert.assertFalse(classUnderTest.hasNext());
    }
}
