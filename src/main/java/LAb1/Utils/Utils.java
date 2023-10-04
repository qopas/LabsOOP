package Lab1.Utils;

import java.util.regex.Pattern;

/**
 * Utils class
 * general field like path to the file
 * also a few general method that are used in different classes
 */
public class Utils {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static final String PATH_logs = "C:\\Users\\user\\IdeaProjects\\LabsOOP\\universityLogs.log";
    public static final String PATH_state =  "C:\\Users\\user\\IdeaProjects\\LabsOOP\\universityInfo.ser";
    public static final String PATH_enrollStudent =  "C:\\Users\\user\\IdeaProjects\\LabsOOP\\studentEnroll";
    public static final String PATH_graduateStudent =  "C:\\Users\\user\\IdeaProjects\\LabsOOP\\studentGraduate";
    public static String[] parseInput(String input) {
        return input.split("/");
    }
    public static boolean isValidEmail(String email) {
        String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern.matches(emailPattern, email);
    }

}
