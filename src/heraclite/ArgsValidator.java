package heraclite;

import java.io.File;

public class ArgsValidator {

  public static boolean isNoGUIValid(String[] args) {
    if (argsContainNoGuiOption(args)) {
      return false;
    }
    if (argsDoesntHaveAtLeast3Values(args)) {
      return false;
    }
    if (firstArgIsntADirectory(args[0])) {
      return false;
    }
    if (secondArgIsntADirectory(args[1])) {
      return false;
    }
    if (thirdArgIsntADirectory(args[2])) {
      return false;
    }
    return true;
  }

  private static boolean argsContainNoGuiOption(String[] args) {
    for (String arg : args) {
      if (arg.equalsIgnoreCase("--gui")) {
        return true;
      }
    }
    return false;
  }

  private static boolean argsDoesntHaveAtLeast3Values(String[] args) {
    return args.length < 3;
  }

  private static boolean firstArgIsntADirectory(String directoryPath) {
    File directory = new File(directoryPath);
    return !directory.isDirectory();
  }

  private static boolean secondArgIsntADirectory(String directoryPath) {
    File directory = new File(directoryPath);
    return !directory.isDirectory() && directory.exists();
  }

  private static boolean thirdArgIsntADirectory(String directoryPath) {
    File directory = new File(directoryPath);
    return !directory.isDirectory() && directory.exists();
  }
}
