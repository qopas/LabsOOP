package Lab2.Files;

import java.util.Date;

class ProgramFile extends File {
    private int lineCount;
    private int classCount;
    private int methodCount;

    public ProgramFile(String filename, Date creationTime, Date updateTime,
                       int lineCount, int classCount, int methodCount) {
        super(filename, "java", creationTime, updateTime);
        this.lineCount = lineCount;
        this.classCount = classCount;
        this.methodCount = methodCount;
    }

    @Override
    public void printInfo() {
        System.out.println("File: " + getFilename());
        System.out.println("Extension: " + getExtension());
        System.out.println("Created: " + getCreationTime());
        System.out.println("Updated: " + getUpdateTime());
        System.out.println("Line count: " + lineCount);
        System.out.println("Class count: " + classCount);
        System.out.println("Method count: " + methodCount);
    }
}
