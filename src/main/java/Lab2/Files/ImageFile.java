package Lab2.Files;

import java.util.Date;

class ImageFile extends File {
    private String imageSize;

    public ImageFile(String filename, Date creationTime, Date updateTime, String imageSize) {
        super(filename, "png", creationTime, updateTime);
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
}
