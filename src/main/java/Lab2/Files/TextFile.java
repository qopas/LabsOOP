package Lab2.Files;

import java.util.Date;

class TextFile extends File {
    private int lineCount;
    private int wordCount;
    private int characterCount;

    public TextFile(String filename, Date creationTime, Date updateTime,
                    int lineCount, int wordCount, int characterCount) {
        super(filename, "txt", creationTime, updateTime);
        this.lineCount = lineCount;
        this.wordCount = wordCount;
        this.characterCount = characterCount;
    }

    @Override
    public void printInfo() {
        System.out.println("File: " + getFilename());
        System.out.println("Extension: " + getExtension());
        System.out.println("Created: " + getCreationTime());
        System.out.println("Updated: " + getUpdateTime());
        System.out.println("Line count: " + lineCount);
        System.out.println("Word count: " + wordCount);
        System.out.println("Character count: " + characterCount);
    }
}