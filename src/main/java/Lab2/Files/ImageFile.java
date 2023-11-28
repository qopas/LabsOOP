package Lab2.Files;

import java.util.Date;

public class ImageFile extends File {
    private String imageSize;

    public ImageFile(String filename,String extension, Date creationTime, Date updateTime, String imageSize) {
        super(filename, extension, creationTime, updateTime);
        this.imageSize = imageSize;
    }

    @Override
    public void printInfo() {
        System.out.println("File: " + getFilename());
        System.out.println("Extension: " + getExtension());
        System.out.println("Created: " + getCreationTime());
        System.out.println("Updated: " + getUpdateTime());
        System.out.println("Image size: " + imageSize);
    }

    public String getImageSize() {
        return imageSize;
    }

    public void setImageSize(String imageSize) {
        this.imageSize = imageSize;
    }
}
