package Lab1.models;
import Lab1.Utils.LogManager;
import Lab1.Utils.Utils;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Stream;

/**
University class (Singleton bcs we will have only one university);
 ....Methods:
    -addFaculty  - add a faculty to list
    -getFaculties - return the faculties;
    -facultyStudentBelong - checks if student belong to faculty by inputted email with validation
    -facultyBelongToField - print a list of faculty related to input field
 **/
public class University implements Serializable {

    private  List<Faculty> faculties;
    private static final Scanner scanner = new Scanner(System.in);

    public University() {
        this.faculties = new ArrayList<>();
    }

    public void addFaculty(){
        System.out.println("Please enter the input in the format: <faculty name>/<faculty abbreviation>/<field>");
        String facultyInput = scanner.nextLine();
        String[] facultyDetails = Utils.parseInput(facultyInput);
        try {
            Faculty newFaculty = new Faculty(facultyDetails[0], facultyDetails[1], new ArrayList<>(), StudyField.valueOf(facultyDetails[2]));
            faculties.add(newFaculty);
            System.out.println("Faculty created successfully!");
            LogManager.LOGGER.info("Faculty created" + newFaculty);
        } catch (IllegalArgumentException e) {
            System.out.println(Utils.RED +"Invalid study field: " + facultyDetails[2] + Utils.RESET);
            LogManager.LOGGER.warning("Invalid study field input: " + facultyDetails[2]);
        } catch (IndexOutOfBoundsException e){
            System.out.println(Utils.RED +"Enter full description of faculty in given format!" + Utils.RESET);
            LogManager.LOGGER.warning(" Faculty creation fail wrong decription " + facultyInput);
        }
    }

    public List<Faculty> getFaculties() {
        if (faculties == null) {
            faculties = new ArrayList<>();
        }
        return faculties;
    }
    public void facultyStudentBelong(){
        System.out.print("Enter email of student to be graduated: ");
        String email = scanner.next();
        if(!Utils.isValidEmail(email)){
            System.out.println(Utils.RED + "INVALID EMAIL" + Utils.RESET);
            return;
        }
        Optional<Faculty> foundFaculty = faculties.stream()
                .filter(faculty -> faculty.getStudentList().stream()
                        .anyMatch(student -> student.getEmail().equals(email)))
                .findFirst();

        if (foundFaculty.isPresent()) {
            System.out.println("The student with email " + email + " belongs to the " + foundFaculty.get().getName() + " faculty.");
        } else {
            System.out.println(Utils.RED + "Student not found." + Utils.RESET );
        }
    }
    public void facultyBelongToField(){
        System.out.print("Enter field: ");
        String field = scanner.next();
        Stream<Faculty> f = faculties.stream()
                .filter(faculty -> faculty.getStudyField().toString().equalsIgnoreCase(field));
        if(f.findAny().isEmpty()){
            System.out.println(Utils.RED + "NO SUCH FIELD" + Utils.RESET);
        }else{
            System.out.println(f);
        }
    }
}
