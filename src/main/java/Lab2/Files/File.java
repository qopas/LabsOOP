package Lab2.Files;

import java.util.Date;

public abstract class File {
    private String filename;
    private String extension;
    private Date creationTime;
    private Date updateTime;


    public File(String filename, String extension, Date creationTime, Date updateTime) {
        this.filename = filename;
        this.extension = extension;
        this.creationTime = creationTime;
        this.updateTime = updateTime;
    }

    public abstract void printInfo();



    public String getFilename() {
        return filename;
    }

    public String getExtension() {
        return extension;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }
}