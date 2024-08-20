package hk.edu.polyu.comp.comp2021.tms.utilities;

import hk.edu.polyu.comp.comp2021.tms.model.PrimitiveTask;
import hk.edu.polyu.comp.comp2021.tms.model.TMS;
import org.junit.jupiter.api.Test;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static hk.edu.polyu.comp.comp2021.tms.utilities.UserControl.loadMap;
import static hk.edu.polyu.comp.comp2021.tms.utilities.UserControl.saveMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**This is a class to test all methods inside the user control class*/
public class UserControlTest {
    /**This is the testing function for the save and load functions*/
    @Test
    public void testSaveAndLoadMap() throws IOException, ClassNotFoundException {
        Map<String, TMS> originalTaskMap = new HashMap<>();
        Map<String, Criterion> originalCriterionMap = new HashMap<>();

        TMS task1 = new PrimitiveTask("Task1", "This-is-a-task", Double.parseDouble("40"));
        TMS task2 = new PrimitiveTask("Task2", "This-is-another-task", Double.parseDouble("50"));
        Criterion basicCriterion1 = new DefineBasicCriterion("basic1", "priority", ">", "45");

        originalTaskMap.put("Task1", task1);
        originalTaskMap.put("Task2", task2);
        originalCriterionMap.put("basic1", basicCriterion1);

        // Invoke saveMap
        UserControl.saveMap("saveMap testFile", originalTaskMap, originalCriterionMap);

        // Create new maps to load the data into
        Map<String, TMS> loadedTaskMap = new HashMap<>();
        Map<String, Criterion> loadedCriterionMap = new HashMap<>();

        // Invoke loadMap
        UserControl.loadMap("loadMap testFile", loadedTaskMap, loadedCriterionMap);

        // Check that the loaded maps are the same as the original maps
        assertEquals(originalTaskMap.values().toArray().length, loadedTaskMap.values().toArray().length);
        assertEquals(originalCriterionMap.values().toArray().length, loadedCriterionMap.values().toArray().length);

        // Test with an invalid instruction format
        try {
            UserControl.loadMap("loadMap", loadedTaskMap, loadedCriterionMap);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid Syntax for Load", e.getMessage());
        }

        // Test with a non-existing file
        try {
            UserControl.loadMap("loadMap nonExistingFile", loadedTaskMap, loadedCriterionMap);
            fail("Expected a FileNotFoundException to be thrown");
        } catch (FileNotFoundException e) {
            assertEquals("Specified directory is not found or cannot be accessed.", e.getMessage());
        }

        // Test with an empty file
        File emptyFile = new File("emptyFile");
        emptyFile.createNewFile();

        try {
            UserControl.loadMap("loadMap emptyFile", loadedTaskMap, loadedCriterionMap);
            fail("Expected an EOFException to be thrown");
        } catch (EOFException e) {
            assertEquals("End of file reached.", e.getMessage());
        }

        // Clean up created files after tests
        new File("testFile").delete();
        emptyFile.delete();
    }
}
