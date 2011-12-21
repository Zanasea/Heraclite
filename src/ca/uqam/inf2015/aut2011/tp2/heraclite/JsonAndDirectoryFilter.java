package ca.uqam.inf2015.aut2011.tp2.heraclite;

import java.io.File;
import java.io.FileFilter;

public class JsonAndDirectoryFilter implements FileFilter {

    @Override
    public boolean accept(File file) {

        return file.isDirectory() || isOfASupportedExtension(file);
    }

    private boolean isOfASupportedExtension(File file) {
        boolean isSupported = true;

        int indexOfFileExtension = file.getName().lastIndexOf(".");
        String fileName = file.getName();
        String supportedExtension = ".json";

        isSupported = hasFileExtension(indexOfFileExtension)
                && fileName.substring(indexOfFileExtension).equalsIgnoreCase(supportedExtension);

        return isSupported;
    }

    private boolean hasFileExtension(int indexOfFileExtension) {
        return indexOfFileExtension >= 0;
    }
}
