package heraclite.calculator;

import heraclite.dto.Extrant;
import heraclite.dto.Intrant;

import java.io.File;
import java.io.IOException;
import org.codehaus.jackson.map.ObjectMapper;


public class JSONFileManager implements PersistanceManager {

  private ObjectMapper mapper = new ObjectMapper();
  private File jsonFile;
  private File outputDirectory;
  private File htmlOutputDirectory;

  public JSONFileManager(File jsonFile, File outputDirectory, File htmlOutputDirectory) {
    this.jsonFile = jsonFile;
    this.outputDirectory = outputDirectory;
    this.htmlOutputDirectory = htmlOutputDirectory;

    boolean couldNotCreateOutputDirectories = createOutputDirectoriesIfItDoesNotExist();
    boolean couldNotCreateHtmlDirectories = createHtmlDirectoriesIfItDoesNotExist();

    if (couldNotCreateOutputDirectories) {
      System.err.println("The output directory does not exist and it could not be created.");
      System.err.println("We will not be able to write the result of the JSON file : " + jsonFile.getName());
    }

    if (couldNotCreateHtmlDirectories) {
      System.err.println("The HTML output directory does not exist and it could not be created.");
      System.err.println("We will not be able to write the result of the JSON file in an HTML format : " + jsonFile.getName());
    }
  }

  @Override
  public Intrant read() throws RuntimeException {
    Intrant intrant = null;
    try {
      intrant = mapper.readValue(jsonFile, Intrant.class);
    } catch (IOException exception) {
      StringBuilder message = new StringBuilder();
      message.append("The JSON file : " + jsonFile.getName() + " you provided is not valid." + System.getProperty("line.separator"));
      message.append("It will not produce a result." + System.getProperty("line.separator"));
      message.append("For your convenience, here is the exception message :" + System.getProperty("line.separator") + exception.getMessage() + System.getProperty("line.separator"));
      RuntimeException re = new RuntimeException(message.toString());
      re.addSuppressed(exception);
      throw re;
    }
    return intrant;
  }

  @Override
  public void write(Extrant extrant) throws RuntimeException  {
    try {
      File file = new File(outputDirectory, jsonFile.getName());
      File htmlFile = new File(htmlOutputDirectory, jsonFile.getName());
      HTMLWriter htmlWriter = new HTMLWriter(htmlFile, extrant);
      htmlWriter.writeToHtml();
      file.createNewFile();
      mapper.defaultPrettyPrintingWriter().writeValue(file, extrant);
    } catch (IOException exception) {
      StringBuilder message = new StringBuilder();
      message.append("We could not write the result of the JSON file : " + jsonFile.getName() + System.getProperty("line.separator"));
      message.append("For your convenience, here is the exception message :" + System.getProperty("line.separator") + exception.getMessage() + System.getProperty("line.separator"));
      RuntimeException re = new RuntimeException(message.toString());
      re.addSuppressed(exception);
      throw re;
    }
  }

  private boolean createOutputDirectoriesIfItDoesNotExist() {
    return (!outputDirectory.exists() && !outputDirectory.mkdirs());
  }

  private boolean createHtmlDirectoriesIfItDoesNotExist() {
    return (!htmlOutputDirectory.exists() && !htmlOutputDirectory.mkdirs());
  }
}
