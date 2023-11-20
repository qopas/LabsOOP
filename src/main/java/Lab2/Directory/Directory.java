package Lab2.Directory;
import Lab2.Files.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Directory {
    private List<File> files;
    private Map<Path, Boolean> fileChanges;

    public Directory(String directoryPath) {
        this.files = new ArrayList<>();
        this.fileChanges = new HashMap<>();
        populateFiles(directoryPath);
    }

    private void populateFiles(String directoryPath) {
        try {
            Files.walk(Paths.get(directoryPath))
                    .filter(Files::isRegularFile)
                    .forEach(f -> FileFactory.createFile(f, this));
        } catch (IOException e) {
            System.out.println("Error populating files: " + e.getMessage());
        }
    }

    public File getFile(String filename) {
        for (File file : files) {
            if (file.getFilename().equals(filename)) {
                return file;
            }
        }
        return null;
    }

    public List<File> getFiles() {
        return files;
    }

    public Map<Path, Boolean> getFileChanges() {
        return fileChanges;
    }
}