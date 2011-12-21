package ca.uqam.inf2015.aut2011.tp2.heraclite;

import java.io.File;

public class ArgsValidator {

    public static boolean isValid(String[] args) {
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
    
    private static boolean argsDoesntHaveAtLeast3Values(String[] args) {
        return args.length < 3;
    }
    
    private static boolean firstArgIsntADirectory(String directoryPath) {
        File directory = new File(directoryPath);
        return !directory.isDirectory();
    }
    
    private static boolean secondArgIsntADirectory(String directoryPath) {
        File directory = new File(directoryPath);
        return  !directory.isDirectory() && directory.exists();
    }
    
    private static boolean thirdArgIsntADirectory(String directoryPath) {
        File directory = new File(directoryPath);
        return  !directory.isDirectory() && directory.exists();
    }
}
