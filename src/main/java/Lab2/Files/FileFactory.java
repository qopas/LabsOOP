package Lab2.Files;

import Lab2.Directory.Directory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FileFactory {
    public static void createFile(Path filePath, Directory directory){
        File file;
        String filename = filePath.getFileName().toString();
        String fileExtension = getFileExtension(filename);
        try {
            switch (fileExtension.toLowerCase()) {
                case "txt":
                    file = createTextFile(filePath);
                    break;
                case "png":
                case "jpg":
                    file = createImageFile(filePath);
                    break;
                case "py":
                case "java":
                    file = createProgramFile(filePath);
                    break;
                default:
                    file = createUnknownFile(filePath);
                    break;
            }

            directory.getFiles().add(file);
            directory.getFileChanges().put(filePath, false);
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }
    private static TextFile createTextFile(Path filePath) throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        int lineCount = lines.size();
        int wordCount = Arrays.stream(lines.stream()
                        .map(line -> line.split("\\s+"))
                        .toArray(String[][]::new))
                .flatMap(Arrays::stream)
                .filter(word -> !word.isEmpty())
                .toArray()
                .length;
        int characterCount = lines.stream()
                .mapToInt(String::length)
                .sum();

        return new TextFile(
                filePath.getFileName().toString(),
                getCreationTime(filePath),
                getUpdateTime(filePath),
                lineCount,
                wordCount,
                characterCount
        );
    }

    private static ImageFile createImageFile(Path filePath) throws IOException {
        String imageSize = getImageSize(filePath);

        return new ImageFile(
                filePath.getFileName().toString(),
                getFileExtension(filePath.getFileName().toString()),
                getCreationTime(filePath),
                getUpdateTime(filePath),
                imageSize
        );
    }

    private static String getImageSize(Path filePath) throws IOException {
        try {
            BufferedImage bufferedImage = ImageIO.read(filePath.toFile());
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();

            return width + "x" + height;
        } catch (IOException e) {
            System.out.println("Error reading image: " + e.getMessage());
            throw e;
        }
    }

    private static ProgramFile createProgramFile(Path filePath) throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        int lineCount = lines.size();
        int classCount = (int) lines.stream().filter(line -> line.contains("class")).count();
        int methodCount = (int) lines.stream().filter(line -> line.contains("void ") || line.contains("int ") || line.contains("def ")).count();

        return new ProgramFile(
                filePath.getFileName().toString(),
                getFileExtension(filePath.getFileName().toString()),
                getCreationTime(filePath),
                getUpdateTime(filePath),
                lineCount,
                classCount,
                methodCount
        );
    }
    private static UnknownFile createUnknownFile(Path filePath) throws IOException {
        return new UnknownFile(
                filePath.getFileName().toString(),
                getFileExtension(filePath.getFileName().toString()),
                getCreationTime(filePath),
                getUpdateTime(filePath)
        );
    }

    private static Date getCreationTime(Path filePath) throws IOException {
        BasicFileAttributes attr = Files.readAttributes(filePath, BasicFileAttributes.class);
        return new Date(attr.creationTime().toMillis());
    }

    private static Date getUpdateTime(Path filePath) throws IOException {
        BasicFileAttributes attr = Files.readAttributes(filePath, BasicFileAttributes.class);
        return new Date(attr.lastModifiedTime().toMillis());
    }

    private static String getFileExtension(String filename) {
        int dotIndex = filename.lastIndexOf('.');
        return (dotIndex == -1) ? "" : filename.substring(dotIndex + 1);
    }
    public static void updateFile(Path filePath, File file) {
        String filename = filePath.getFileName().toString();
        String fileExtension = getFileExtension(filename);
        try {
            switch (fileExtension.toLowerCase()) {
                case "txt":
                    updateTextFile(filePath, (TextFile) file);
                    break;
                case "png":
                case "jpg":
                    updateImageFileAttributes(filePath, (ImageFile) file);
                    break;
                case "py":
                case "java":
                    updateProgramFileAttributes(filePath, (ProgramFile) file);
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }
    private static void updateTextFile(Path filePath, TextFile textFile) throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        int lineCount = lines.size();
        int wordCount = Arrays.stream(lines.stream()
                        .map(line -> line.split("\\s+"))
                        .toArray(String[][]::new))
                .flatMap(Arrays::stream)
                .filter(word -> !word.isEmpty())
                .toArray()
                .length;
        int characterCount = lines.stream()
                .mapToInt(String::length)
                .sum();

        textFile.setLineCount(lineCount);
        textFile.setWordCount(wordCount);
        textFile.setCharacterCount(characterCount);
    }
    private static void updateImageFileAttributes(Path filePath, ImageFile imageFile) throws IOException {
        String imageSize = getImageSize(filePath);
        imageFile.setImageSize(imageSize);
    }
    private static void updateProgramFileAttributes(Path filePath, ProgramFile programFile) throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        int lineCount = lines.size();
        int classCount = (int) lines.stream().filter(line -> line.contains("class")).count();
        int methodCount = (int) lines.stream().filter(line -> line.contains("void ") || line.contains("int ") || line.contains("def ")).count();
        programFile.setLineCount(lineCount);
        programFile.setClassCount(classCount);
        programFile.setMethodCount(methodCount);
    }

}
