package Lab2.Files;

import java.util.Date;

public class UnknownFile extends File {

    public UnknownFile(String filename, String extension, Date creationTime, Date updateTime) {
        super(filename, extension, creationTime, updateTime);
    }

    @Override
    public void printInfo() {
        System.out.println("Unknown file type: " + getFilename());
    }
}
