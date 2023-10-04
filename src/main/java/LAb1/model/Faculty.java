package LAb1.model;


import java.util.List;

class Faculty {
    private  String name;
    private  String abbreviation;
    private List<Student> studentList;
    private StudyField studyField;

    public Faculty(String name, String abbreviation, List<Student> studentList, StudyField studyField) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.studentList = studentList;
        this.studyField = studyField;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public StudyField getStudyField() {
        return studyField;
    }

    public void setStudyField(StudyField studyField) {
        this.studyField = studyField;
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
    public String studentCheck(Student s){
         return studentList.contains(s) ? "Bellong" : "Dosen't bellong";
    }

    public void graduateStudentByEmail(String email){
        for(Student s : studentList){
            if(s.getEmail().equalsIgnoreCase(email)){
                s.graduate();
                break;
            }
        }
    }
}
