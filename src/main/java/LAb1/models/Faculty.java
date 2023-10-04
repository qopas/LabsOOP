package Lab1.models;


import Lab1.Utils.LogManager;
import Lab1.Utils.Utils;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Faculty class
 * displayGraduates(Boolean type): type - indicates that kind of student to show (graduates or current)
 * addStudent : parsing user input with validation of input string
 * studentCheck: check if student belong to this faculty, by input email - with email validation
 */
public class Faculty implements Serializable {
    private  String name;
    private  String abbreviation;
    private List<Student> studentList;
    private StudyField studyField;

    private final static Scanner scanner = new Scanner(System.in);
    public Faculty(String name, String abbreviation, List<Student> studentList, StudyField studyField) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.studentList = studentList;
        this.studyField = studyField;
    }

    public String getName() {
        return name;
    }


    public String getAbbreviation() {
        return abbreviation;
    }


    public List<Student> getStudentList() {
        return Collections.unmodifiableList(studentList);
    }

    public StudyField getStudyField() {
        return studyField;
    }


    public void addStudent(Student student){
        studentList.add(student);
    }

    public void displayGraduates(Boolean type){
        System.out.println("List of students: ");
        System.out.println("------------------");
        for(Student s : studentList){
            if(s.getGraduated() == type){
                System.out.println(s);
            }
        }
        System.out.println("------------------");
    }

    public void graduateStudentByEmail(){
        System.out.print("Enter email of student to be graduated: ");
        String email = scanner.next();
        if(!Utils.isValidEmail(email)){
            System.out.println(Utils.RED + "INVALID EMAIL" + Utils.RESET);
            return;
        }
        for(Student s : studentList){
            if(s.getEmail().equals(email)){
                s.graduate();
                LogManager.LOGGER.info("Student: " + s + "GRADUATED");
                System.out.println("Student graduate successfully.");
                return;
            }
        }
        LogManager.LOGGER.warning("NO STUDENT FOUND WITH EMAIL" + email);
        System.out.println("Student with such email wasn't found");
    }
    public void addStudent() {
        System.out.println("Please enter student details in the format: <firstname>/<lastname>/<email>/<enrollmentDate>/<dateOfBirth>");
        String student = scanner.nextLine();
        String[] studentDetails = Utils.parseInput(student);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Student newStudent = new Student(studentDetails[0], studentDetails[1], studentDetails[2], dateFormat.parse(studentDetails[3]), dateFormat.parse(studentDetails[4]));
            studentList.add(newStudent);
            System.out.println("Student created successfully!");
            LogManager.LOGGER.info("Student created" + newStudent);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Utils.RED + "Enter student in valid format" + Utils.RESET);
            LogManager.LOGGER.warning("FAIL TO CREATE STUDENT: INVALID DESCRIPTION " + student );
        } catch (ParseException e) {
            System.out.println(Utils.RED +"Invalid date format. Please use the format: dd-MM-yyyy" + Utils.RESET);
            LogManager.LOGGER.warning("FAIL TO CREATE STUDENT: ParseException  " + student );
        }
    }
    public void studentCheck(){
        System.out.print("Enter student email:");
        String email = scanner.next();
        if(!Utils.isValidEmail(email)){
            System.out.println(Utils.RED + "INVALID EMAIL" + Utils.RESET);
            return;
        }
        for(Student student: studentList){
            if(student.getEmail().equalsIgnoreCase(email)){
                System.out.println("Student belong to this faculty");
                return;
            }
        }
        System.out.println("Student doesn't belong to this faculty");
    }
    @Override
    public String toString() {
        return "Faculty{" +
                "name='" + name + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                ", studentList=" + studentList +
                ", studyField=" + studyField +
                '}';
    }
}
