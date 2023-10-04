package Lab1.app;

import Lab1.Utils.StateController;
import Lab1.models.Faculty;
import Lab1.Utils.StudentBatch;
import Lab1.models.University;
import java.util.HashMap;
import java.util.Scanner;

/**
 * P.S : I didn't complicate it, I didn't divide everything into very many classes because I didn't see the sense in it.
  -the students' functionality is in the students' class
  -Faculty functionality is in faculty
  -General university functionality is in the university class
   I don't see the sense in splitting it into controllers and others, to a simple console app.
 *
 * ConsoleMenu class:
 * this class contain menu for UNIVERSITY STUDENT MANAGEMENT SYSTEM
 * methods:
 * run - initialize menu
 * displayMenu - main menu
 * facultyMenu - displaying list of available faculty
 * generalOperation - displaying list of general operation
 * facultyOperation - menu of operation applied to faculty
 * batchMenu - menu to batch operation(enrollment, graduate) with student
 */
class ConsoleMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private University university;

    /**
     * Constructor with shut down Hook to auto serialize(save current program state) university object
     * when application is closed(any cases)
     */
    public ConsoleMenu() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            StateController.saveState(university);
        }));
    }

    public void run() {
        university = StateController.loadState();
        while (true) {
            displayMainMenu();
            String choice = scanner.next().toLowerCase();
            switch (choice) {
                case "f":
                    facultyMenu();
                    break;
                case "g":
                    generalOperation();
                    break;
                case "s":
                    batchMenu();
                    break;
                case "q":
                    System.out.println("Exiting program.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid input");
            }
        }
    }

    public  void displayMainMenu() {
        System.out.print(""" 
                            Main Menu:
                            f - Faculty Operation
                            g - General Operation
                            s - Batch Operation
                            q - Quit
                            Enter your choice:""");
    }

    public void facultyMenu() {
        HashMap<String, Faculty> abbrev = new HashMap<>();
        String choice;
        System.out.println("Choose faculty to operate with: (INPUT ABREVIATION)");
        do {
            if (university.getFaculties().isEmpty()) {
                System.out.println("No faculties");
            } else {
                for (Faculty faculty : university.getFaculties()) {
                    System.out.println("\t" +faculty.getAbbreviation() + " - " + faculty.getName());
                    abbrev.put(faculty.getAbbreviation(), faculty);
                }
            }
            System.out.println("b - Back");
            System.out.print("Enter your choice: ");
            choice = scanner.next().toUpperCase();
            if (abbrev.containsKey(choice)) {
                facultyOperation(abbrev.get(choice));
            } else if (!choice.equals("B")) {
                System.out.println("INVALID ABBREVIATION... back to menu");
            }
        } while (!choice.equals("B"));
    }

    public void facultyOperation(Faculty faculty) {
        String choice;
        do {
            System.out.println("FACULTY: " + faculty.getName());
            System.out.println("""
                    cs - Create a new student
                    gr - Graduate a student by email
                    dc - Display current enrolled students
                    dg - Display graduates 
                    sb - Check if student belong to this faculty by email
                    b  - Back to menu""");
            System.out.print("Enter your choice: ");
            choice = scanner.next().toLowerCase();
            switch (choice){
                case "cs":
                    faculty.addStudent();
                    break;
                case "gr":
                    faculty.graduateStudentByEmail();
                    break;
                case "dc":
                    faculty.displayGraduates(false);
                    break;
                case "dg":
                    faculty.displayGraduates(true);
                    break;
                case "sb":
                    faculty.studentCheck();
                    break;
                case "b":
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }while (!choice.equals("b"));
    }
    public void batchMenu() {
        String choice;
        do {
            System.out.println("""
                    as - Batch enrollment operation for students via a text file
                    gs - Batch graduation operation for students via text file.
                    b  - Back to menu""");
            System.out.print("Enter your choice: ");
            choice = scanner.next().toLowerCase();
            switch (choice){
                case "as":
                    StudentBatch.enrollment(university);
                    break;
                case "gs":
                    StudentBatch.graduation(university);
                    break;
                case "b":
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }while (!choice.equals("b"));
    }
    public void generalOperation() {
        String choice;
        do {
            System.out.println("""
                    cf - Create a new faculty
                    sf - Search what faculty a student belongs by email
                    du - Display University faculties
                    df - Display all faculties belonging to a field
                    b  - Back to menu""");
            System.out.print("Enter your choice: ");
            choice = scanner.next().toLowerCase();
            switch (choice){
                case "cf":
                    university.addFaculty();
                    break;
                case "sf":
                    university.facultyStudentBelong();
                    break;
                case "du":
                    university.getFaculties();
                    break;
                case "df":
                    university.facultyBelongToField();
                    break;
                case "b":
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }while (!choice.equals("b"));
    }
}
