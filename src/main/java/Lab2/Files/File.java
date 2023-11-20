package Lab2.Files;

import java.util.Date;

public abstract class File {
    private String filename;
    private String extension;
    private Date creationTime;
    private Date updateTime;
    private boolean changed;

    public File(String filename, String extension, Date creationTime, Date updateTime) {
        this.filename = filename;
        this.extension = extension;
        this.creationTime = creationTime;
        this.updateTime = updateTime;
        this.changed = false;
    }

    public abstract void printInfo();

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

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