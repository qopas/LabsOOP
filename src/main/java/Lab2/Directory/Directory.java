package Lab2.Directory;
import Lab2.Files.File;

import java.util.ArrayList;
import java.util.List;

public class Directory {
    private List<File> files;

    public Directory() {
        this.files = new ArrayList<>();
    }

    public void addFile(File file) {
        files.add(file);
    }

    public File getFile(String filename) {
        for (File file : files) {
            if (file.getFilename().equals(filename)) {
                return file;
            }
        }
        return null;
    }

    public void printFileInfo(String filename) {
        File file = getFile(filename);
        if (file != null) {
            file.printInfo();
        } else {
            System.out.println("File not found.");
        }
    }

    public void printAllFileInfo() {
        for (File file : files) {
            file.printInfo();
            System.out.println();
        }
    }

    public void updateFileInformation(String filename) {
        File file = getFile(filename);
        if (file != null) {
            // Update file information based on the file type
            // ...
        } else {
            System.out.println("File not found.");
        }
    }
}