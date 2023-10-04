package LAb1.model;


import java.util.Date;

class Student {
    private String firstName;
    private String lastName;
    private String email;
    private Date enrolimentDate;
    private Date dateOFBirth;
    private Boolean graduated;
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getEnrolimentDate() {
        return enrolimentDate;
    }

    public void setEnrolimentDate(Date enrolimentDate) {
        this.enrolimentDate = enrolimentDate;
    }

    public Date getDateOFBirth() {
        return dateOFBirth;
    }

    public void setDateOFBirth(Date dateOFBirth) {
        this.dateOFBirth = dateOFBirth;
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
}
