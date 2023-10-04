package LAb1.model;

import LAb1.model.Faculty;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
University class (Singleton bcs we will have only one university);
 ....Methods:
    -addFaculty  - add a faculty to list
    -getFaculties - return the faculties;
    -facultyStudentBelong - checks if student belong to faculty by inputted email
    -facultyBelongToField - print a list of faculty related to input field
 **/
public class University {

    private static List<Faculty> faculties;


    public University() {}

    public void addFaculty(Faculty faculty){
        faculties.add(faculty);
    }

    public List<Faculty> getFaculties() {
        if (faculties == null) {
            faculties = new ArrayList<>();
        }
        return faculties;
    }

    public void facultyStudentBelong(String email){
        Optional<Faculty> foundFaculty = faculties.stream()
                .filter(faculty -> faculty.getStudentList().stream()
                        .anyMatch(student -> student.getEmail().equals(email)))
                .findFirst();

        if (foundFaculty.isPresent()) {
            System.out.println("The student with email " + email + " belongs to the " + foundFaculty.get().getName() + " faculty.");
        } else {
            System.out.println("Student not found.");
        }
    }
    public void facultyBelongToField(String field){
        System.out.println(faculties.stream()
                .filter(faculty -> faculty.getStudyField().toString().equalsIgnoreCase(field)).toString());
    }
}
