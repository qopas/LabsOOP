package Lab1.models;

import java.io.Serializable;


enum StudyField implements Serializable {
    MEHANICAL_ENGINEERING("MEHANICAL_ENGINEERING"),
    SOFTWARE_ENGINEERING("SOFTWARE_ENGINEERING"),
    FOOD_TECHNOLOGY("FOOD_TECHNOLOGY"),
    URBANISM_ARHITECTURE("URBANISM_ARHITECTURE"),
    VETERENARY_MEDICINE("VETERENARY_MEDICINE");

    private final String displayName;

    StudyField(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}

