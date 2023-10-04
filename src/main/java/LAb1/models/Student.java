package Lab1.models;

import java.io.Serializable;
import java.util.Date;

/**
 * Student class
 * student field and basic methods
 */
public class Student implements Serializable {
    private String firstName;
    private String lastName;
    private String email;
    private Date enrolimentDate;
    private Date dateOFBirth;
    private Boolean graduated;

    public String getEmail() {
        return email;
    }

    public Boolean getGraduated() {
        return graduated;
    }

    public void setGraduated(Boolean graduated) {
        this.graduated = graduated;
    }

    public Student(String firstName, String lastName, String email, Date enrolimentDate, Date dateOFBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.enrolimentDate = enrolimentDate;
        this.dateOFBirth = dateOFBirth;
        this.graduated = false;
    }
    public void graduate(){
        this.setGraduated(true);
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", enrolimentDate=" + enrolimentDate +
                ", dateOFBirth=" + dateOFBirth +
                ", graduated=" + graduated +
                '}';
    }
}
