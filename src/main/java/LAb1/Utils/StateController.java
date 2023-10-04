package Lab1.Utils;

import Lab1.models.University;

import java.io.*;

/**
 * StateController class
 * saveState : method to serialize data in universityInfo.ser file (ALL MODEL CLASSES IMPLEMENT Serializable INTERFACE)
 * loadState : method to deserialize data to get our university object
 */
public class StateController {
    public static void saveState(University university){
        if(university == null){
            university = new University();
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Utils.PATH_state))) {
            oos.writeObject(university);
            System.out.println("Object saved with modifications.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static University loadState(){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Utils.PATH_state))) {
            System.out.println("Object loaded successfully:");
            University u =  (University)ois.readObject();
            return u;
        } catch (IOException | ClassNotFoundException e) {
            return new University();
        }
    }
}
