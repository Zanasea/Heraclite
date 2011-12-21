package heraclite;

import heraclite.calculator.Calculator;
import heraclite.calculator.JSONFileManager;
import heraclite.calculator.PersistanceManager;
import heraclite.calculator.SourceReader;
import heraclite.gui.GUI;
import heraclite.gui.SwingGUI;

import java.io.File;

public class Main {

  private static File inputDirectory;
  private static File originalOutputDirectory;
  private static File originalHtmlOutputDirectory;

  public static void main(String[] args) throws Exception {
    if (ArgsValidator.isNoGUIValid(args)) {

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
    } else {
      GUI gui = new SwingGUI();
      gui.init();
      gui.display();
    }
  }

  private static File parseFinalOutputDirectory(File inputFile, File outputDirectory) {
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

}
