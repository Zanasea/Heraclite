package ca.uqam.inf2015.aut2011.tp2.heraclite;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class SourceReader {

    private ArrayList<File> jsonFiles = new ArrayList<File>();
    private Iterator<File> iterator;

    public SourceReader(File directory) {
        File[] files = directory.listFiles(new JsonAndDirectoryFilter());
        addJsonFilesToList(files);
        iterator = jsonFiles.iterator();
    }

    private void addJsonFilesToList(File[] files) {
        for (int i = 0; i < files.length; ++i) {
            File currentFile = files[i];
            if (currentFile.isDirectory()) {                
                addJsonFilesToList(currentFile.listFiles(new JsonAndDirectoryFilter()));
            } else {
                jsonFiles.add(currentFile);
            }
        }
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }

    public File next() {
        return iterator.next();
    }

}
