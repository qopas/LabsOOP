package Lab1.Utils;

import Lab1.models.Faculty;
import Lab1.models.University;
import Lab1.models.Student;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

/**
 * StudentBatch
 * this class manage batch operation
 * enrollment - enroll student from file, validates input
 * graduation - graduate student from file by email, validates input
 */
public class StudentBatch {
    public static void enrollment(University university) {
        try (BufferedReader reader = new BufferedReader(new FileReader(Utils.PATH_enrollStudent))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] studentDetails = line.split("/");
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Student newStudent = new Student(studentDetails[0], studentDetails[1], studentDetails[2], dateFormat.parse(studentDetails[3]), dateFormat.parse(studentDetails[4]));
                String facultyAbbreviation = studentDetails[5].toUpperCase();
                boolean found = false;
                for(Faculty faculty : university.getFaculties()){
                    if(faculty.getAbbreviation().equals(facultyAbbreviation)){
                        faculty.addStudent(newStudent);
                        found = true;
                    }
                }
                if(!found) {
                    System.out.println(Utils.RED + "FACULTY NOT FOUND: " + facultyAbbreviation + Utils.RESET);
                }
            }
            System.out.println("Batch enrollment completed successfully.");
            LogManager.LOGGER.info("STUDENT BATCH ENORLEMENT COMPLETE");
        } catch (IOException | ParseException e) {
            System.err.println("Error during batch enrollment: " + e.getMessage());
        }
    }
    public static void graduation(University university) {
        try (BufferedReader reader = new BufferedReader(new FileReader(Utils.PATH_graduateStudent))) {
            String line;
            while ((line = reader.readLine()) != null) {
                    if(Utils.isValidEmail(line)){
                        String finalLine = line;
                        Optional<Student> foundStudent = university.getFaculties()
                                .stream()
                                .flatMap(faculty -> faculty.getStudentList().stream())
                                .filter(student -> student.getEmail().equals(finalLine))
                                .findFirst();
                        if (foundStudent.isPresent()) {
                            foundStudent.get().graduate();
                        } else {
                            System.out.println(Utils.RED + "Student not found." + Utils.RESET);
                            LogManager.LOGGER.info("STUDENT BATCH GRADUATION FAIL: WRONG EMAIL " + finalLine);
                        }
                }
            }
            System.out.println("Batch graduation completed successfully.");
        } catch (IOException e) {
            System.err.println("Error during batch graduation: ");
        }
    }
}
