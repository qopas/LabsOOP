package LAb1;

import LAb1.model.University;

import java.io.Serializable;

class SaveData  implements Serializable {
    private University university;

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }
}
