package hk.edu.polyu.comp.comp2021.tms.utilities;

import hk.edu.polyu.comp.comp2021.tms.model.TMS;
import java.io.*;
import java.util.Map;


/**
 * UserControl Class used to provide methods to for saving and loading maps of tasks and criteria
 * to facilitates the serialization and deserialization of maps to and from files.
 * This class is designed to interact with maps of tasks and criteria, allowing users to persistently
 * store their data or load data from a specified file
 */
public class UserControl {
    /**
     * Method serves to save the current state of task and criterion maps to file.
     *
     * @param instruction A string representing the entire user input
     * @param taskMap A map that stores the user's tasks, mapped by their names. The task to be modified should be present in this map.
     * @param criterionMap stores the user's criterion information
     * @throws IOException checks if an input/output error occurs during the save operation
     */
    public static void saveMap(String instruction, Map<String, TMS> taskMap, Map<String, Criterion> criterionMap) throws IOException {
        String[] inputArray = instruction.split(" ");
        if (inputArray.length == 2) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(inputArray[1]))) {
                oos.writeObject(taskMap);
                oos.writeObject(criterionMap);
                System.out.println("Files were updated successfully");
            } catch (FileNotFoundException e) {
                System.out.println("The file path was not found. Try again");
            } catch (NotSerializableException e) {
                System.out.println("There is an implementation error.");
            }
        } else {
            System.out.println("Invalid format for Store function");
        }
    }

    /**
     * Methods serves to load task and criterion maps from a specified file.
     *
     * @param instruction A string representing the entire user input
     * @param taskMap A map that stores the user's tasks, mapped by their names. The task to be modified should be present in this map.
     * @param criterionMap stores the user's criterion information
     * @throws IOException checks if an input/output error occurs during the save operation
     * @throws ClassNotFoundException checks if the class of a serialized object can not be found during deserialization
     */
    public static void loadMap(String instruction, Map<String, TMS> taskMap, Map<String, Criterion> criterionMap) throws IOException, ClassNotFoundException {
        String[] inputArray = instruction.split(" ");
        if (inputArray.length == 2) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(inputArray[1]))) {
                taskMap.clear();
                criterionMap.clear();
                taskMap.putAll((Map<String, TMS>) ois.readObject());
                criterionMap.putAll((Map<String, Criterion>) ois.readObject());
                System.out.println("All lines within file have been read.");
            } catch (FileNotFoundException e) {
                throw new FileNotFoundException("Specified directory is not found or cannot be accessed.");
            } catch (EOFException e) {
                throw new EOFException("End of file reached.");
            }
        } else {
            throw new IllegalArgumentException("Invalid Syntax for Load");
        }
    }
}


