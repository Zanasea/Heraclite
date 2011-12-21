package heraclite.calculator;

import heraclite.calculator.JSONFileManager;
import heraclite.calculator.PersistanceManager;
import heraclite.dto.Extrant;

import java.io.File;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class JSONFileManagerTest {

    private File wrongFieldNameJson = new File("./wrongFieldName.json");
    private File wrongDataTypeJson = new File("./wrongDataType.json");
    private File unbindedFieldJson = new File("./unbindedFieldJson.json");
    private File validJson = new File("./validJson.json");
    private File outputDirectory = new File(".");
    private File htmlOutputDirectory = new File(".");
    private File invalidOutputDirectory = new File("!&*%#..");
    private File invalidHtmlOutputDirectory = new File("!?*%$#..");

    @Before
    public void setup() throws Exception {
        wrongFieldNameJson.deleteOnExit();
        wrongDataTypeJson.deleteOnExit();
        unbindedFieldJson.deleteOnExit();
        validJson.deleteOnExit();

        JsonFactory jsonFactory = new JsonFactory();
        JsonGenerator jsonGenerator = jsonFactory.createJsonGenerator(wrongFieldNameJson, JsonEncoding.UTF8);
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", "Test1");
        jsonGenerator.writeStringField("description", "Premier test");
        jsonGenerator.writeNumberField("wrongFieldName", 100000);
        jsonGenerator.writeNumberField("nombreAnnee", 25);
        jsonGenerator.writeNumberField("frequenceRemboursement", 12);
        jsonGenerator.writeNumberField("tauxInteret", 6);
        jsonGenerator.writeNumberField("frequenceComposition", 2);
        jsonGenerator.writeEndObject();
        jsonGenerator.close();

        jsonGenerator = jsonFactory.createJsonGenerator(wrongDataTypeJson, JsonEncoding.UTF8);
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", "Test1");
        jsonGenerator.writeStringField("description", "Premier test");
        jsonGenerator.writeStringField("montant", "WRONGDATATYPE");
        jsonGenerator.writeNumberField("nombreAnnee", 25);
        jsonGenerator.writeNumberField("frequenceRemboursement", 12);
        jsonGenerator.writeNumberField("tauxInteret", 6);
        jsonGenerator.writeNumberField("frequenceComposition", 2);
        jsonGenerator.writeEndObject();
        jsonGenerator.close();

        jsonGenerator = jsonFactory.createJsonGenerator(unbindedFieldJson, JsonEncoding.UTF8);
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", "Test1");
        jsonGenerator.writeStringField("description", "Premier test");
        jsonGenerator.writeNumberField("montant", 100000);
        jsonGenerator.writeNumberField("nombreAnnee", 25);
        jsonGenerator.writeNumberField("frequenceRemboursement", 12);
        jsonGenerator.writeNumberField("tauxInteret", 6);
        jsonGenerator.writeNumberField("frequenceComposition", 2);
        jsonGenerator.writeStringField("Test", "Coucou");
        jsonGenerator.writeEndObject();
        jsonGenerator.close();

        jsonGenerator = jsonFactory.createJsonGenerator(validJson, JsonEncoding.UTF8);
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", "Test1");
        jsonGenerator.writeStringField("description", "Premier test");
        jsonGenerator.writeNumberField("montant", 100000);
        jsonGenerator.writeNumberField("nombreAnnee", 25);
        jsonGenerator.writeNumberField("frequenceRemboursement", 12);
        jsonGenerator.writeNumberField("tauxInteret", 6);
        jsonGenerator.writeNumberField("frequenceComposition", 2);
        jsonGenerator.writeEndObject();
        jsonGenerator.close();
    }

    @Test
    public void testDoNotAcceptJsonWithWrongFieldName() {
        PersistanceManager classUnderTest = new JSONFileManager(wrongFieldNameJson, outputDirectory, htmlOutputDirectory);
        Assert.assertNull(classUnderTest.read());
    }

    @Test
    public void testDoNotAcceptJsonWithWrongDataType() {
        PersistanceManager classUnderTest = new JSONFileManager(wrongDataTypeJson, outputDirectory, htmlOutputDirectory);
        Assert.assertNull(classUnderTest.read());
    }

    @Test
    public void testDoNotAcceptJsonWithFieldNameNotInIntrantClass() {
        PersistanceManager classUnderTest = new JSONFileManager(unbindedFieldJson, outputDirectory, htmlOutputDirectory);
        Assert.assertNull(classUnderTest.read());
    }

    @Test
    public void testWriteWithValidExtrant() {
        PersistanceManager classUnderTest = new JSONFileManager(validJson, outputDirectory, htmlOutputDirectory);
        classUnderTest.write(new Extrant(classUnderTest.read()));
        Assert.assertTrue(new File(outputDirectory, validJson.getName()).exists());
    }
    
    @Test
    public void testDoesNotWriteWithInvalidExtrant() {
        PersistanceManager classUnderTest = new JSONFileManager(validJson, invalidOutputDirectory, htmlOutputDirectory);
        classUnderTest.write(new Extrant(classUnderTest.read()));
        Assert.assertFalse(new File(invalidOutputDirectory, validJson.getName()).exists());
    }
    
    @Test
    public void testWriteWithValidHtml() {
        PersistanceManager classUnderTest = new JSONFileManager(validJson, outputDirectory, htmlOutputDirectory);
        classUnderTest.write(new Extrant(classUnderTest.read()));
        Assert.assertTrue(new File(htmlOutputDirectory, validJson.getName()).exists());
    }
    
    @Test
    public void testDoesNotWriteWithInvalidHtml() {
        PersistanceManager classUnderTest = new JSONFileManager(validJson, outputDirectory, invalidHtmlOutputDirectory);
        classUnderTest.write(new Extrant(classUnderTest.read()));
        Assert.assertFalse(new File(invalidHtmlOutputDirectory, validJson.getName()).exists());
    }
}
