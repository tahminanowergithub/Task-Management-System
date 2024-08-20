package hk.edu.polyu.comp.comp2021.tms.utilities;

import hk.edu.polyu.comp.comp2021.tms.model.PrimitiveTask;
import hk.edu.polyu.comp.comp2021.tms.model.TMS;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**Testing the methods within the DefineBinaryCriterion Class*/
public class DefineBinaryCriterionTest {
    /**Testing the constructor*/
    @Test
    public void testDefineBinaryCriterionInstantiation() {
        String string1 = "name";
        Criterion criteria1 = new DefineBinaryCriterion();
        String string3 = "op";
        Criterion criteria2 = new DefineNegatedCriterion();
        try {
            Class.forName("hk.edu.polyu.comp.comp2021.tms.utilities.DefineBinaryCriterion").getDeclaredConstructor(String.class, Criterion.class, String.class, Criterion.class).newInstance(string1, criteria1, string3, criteria2);
            // If no exception is thrown, the test passes (i.e., the class could be instantiated).
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException e) {
            fail("Unexpected exception: " + e.getMessage());
        } catch (InstantiationException e) {
            fail("Should not have thrown an InstantiationException because ConcreteClassName is not abstract!");
        } catch (InvocationTargetException e) {
            fail("Unexpected target exception: " + e.getTargetException().getMessage());
        }
    }
    /**Initialize Binary Criteria*/
    protected DefineBinaryCriterion binaryCriterion = new DefineBinaryCriterion();
    /**Initialize task map*/
    protected static Map<String, TMS> taskMap = new HashMap<>(); // the taskMap
    /**Initialize criterion map*/
    protected static Map<String, Criterion> criterionMap = new HashMap<>(); // HashMap for criteria
    /**double for testing*/
    protected double x = Double.parseDouble("40");
    /**double for testing*/
    protected double y = Double.parseDouble("95");

    /**When valid inputs are passed for creation*/
    @Test
    public void testCreateWithValidInputs() {
        criterionMap.clear();
        Criterion basicCriterion1 = new DefineBasicCriterion("basic1", "name", "contains", "something");
        Criterion basicCriterion2 = new DefineBasicCriterion("basic2", "description", "contains", "something");

        criterionMap.put("basic1", basicCriterion1);
        criterionMap.put("basic2", basicCriterion2);

        binaryCriterion.create("DefineBinaryCriterion binary1 basic1 && basic2", criterionMap);

        assertTrue(criterionMap.containsKey("binary1"));
        assertEquals(criterionMap.get("binary1").getClass(), DefineBinaryCriterion.class);
    }
    /**When null inputs are passed for creation*/
    @Test
    public void testCreateWithNullInput() {
        criterionMap.clear();
        assertThrows(NullPointerException.class, () -> {
            binaryCriterion.create(null, criterionMap);
        });
    }
    /**when creating with same criterion*/
    @Test
    public void testCreateWithSameCriterion() {
        criterionMap.clear();
        Criterion basicCriterion1 = new DefineBasicCriterion("basic1", "name", "contains", "something");

        criterionMap.put("basic1", basicCriterion1);

        binaryCriterion.create("DefineBinaryCriterion binary1 basic1 && basic1", criterionMap);

        assertTrue(criterionMap.containsKey("binary1"));
        assertEquals(criterionMap.get("binary1").getClass(), DefineBinaryCriterion.class);
    }
    /**going through all end cases*/
    @Test
    public void testCreationEndCases() {
        criterionMap.clear();
        Criterion basicCriterion1 = new DefineBasicCriterion("basic1", "name", "contains", "something");

        criterionMap.put("basic1", basicCriterion1);

        binaryCriterion.create("DefineBinaryCriterion binary1 basic1 && basic1 dfdfdd", criterionMap);
        binaryCriterion.create("DefineBinaryCriterion binary1 basic1 && basic1", criterionMap);
        binaryCriterion.create("DefineBinaryCriterion binary1 basic1 || basic1 dfdfdd", criterionMap);
        binaryCriterion.create("DefineBinaryCriterion 2binary basic1 && basic1", criterionMap);
        binaryCriterion.create("DefineBinaryCriterion binary2 basic1 .. basic1", criterionMap);
        binaryCriterion.create("DefineBinaryCriterion binary3 basic3 && basic1", criterionMap);
        binaryCriterion.create("DefineBinaryCriterion binary3 basic1 && basic3", criterionMap);

        assertTrue(criterionMap.containsKey("binary1"));
        assertFalse(criterionMap.containsKey("2binary"));
        assertFalse(criterionMap.containsKey("binary2"));
        assertFalse(criterionMap.containsKey("binary3"));
        assertEquals(criterionMap.get("binary1").getClass(), DefineBinaryCriterion.class);

    }

    /**Testing search for combinations*/
    @Test
    public void testSearchWithGreaterThan() {
        criterionMap.clear();
        taskMap.clear();
        TMS task1 = new PrimitiveTask("Task1", "This-is-a-task", x);
        TMS task2 = new PrimitiveTask("Task2", "This-is-another-task", y);

        taskMap.put("Task1", task1);
        taskMap.put("Task2", task2);

        Criterion basicCriterion1 = new DefineBasicCriterion("basic1", "priority", "<", "45");
        Criterion basicCriterion2 = new DefineBasicCriterion("basic2", "priority", "==", "90");
        criterionMap.put("basic1", basicCriterion1);
        criterionMap.put("basic2", basicCriterion2);
        binaryCriterion.create("DefineBinaryCriterion binary1 basic1 || basic2", criterionMap);

        Criterion basicCriterion3 = new DefineBasicCriterion("basic3", "priority", ">=", "45");
        Criterion basicCriterion4 = new DefineBasicCriterion("basic4", "priority", "<=", "90");
        criterionMap.put("basic3", basicCriterion3);
        criterionMap.put("basic4", basicCriterion4);
        binaryCriterion.create("DefineBinaryCriterion binary2 basic3 || basic4", criterionMap);

        Criterion basicCriterion5 = new DefineBasicCriterion("basic5", "priority", "!=", "45");
        Criterion basicCriterion6 = new DefineBasicCriterion("basic6", "priority", ">", "90");
        criterionMap.put("basic3", basicCriterion5);
        criterionMap.put("basic4", basicCriterion6);
        binaryCriterion.create("DefineBinaryCriterion binary3 basic3 && basic4", criterionMap);

        binaryCriterion.search("search binary1", taskMap, criterionMap);
        binaryCriterion.search("search binary2", taskMap, criterionMap);
        binaryCriterion.search("search binary3", taskMap, criterionMap);
    }

    /**For the search function part 2*/
    @Test
    public void testSearchWithLogicalAnd() {
        taskMap.clear();
        criterionMap.clear();
        TMS task1 = new PrimitiveTask("Task1", "This-is-a-task", x);
        TMS task2 = new PrimitiveTask("Task2", "This-is-another-task", y);

        taskMap.put("Task1", task1);
        taskMap.put("Task2", task2);

        Criterion basicCriterion1 = new DefineBasicCriterion("basic1", "priority", ">", "45");
        Criterion basicCriterion2 = new DefineBasicCriterion("basic2", "name", "==", "Task2");

        criterionMap.put("basic1", basicCriterion1);
        criterionMap.put("basic2", basicCriterion2);

        binaryCriterion.create("DefineBinaryCriterion binary1 basic1 && basic2", criterionMap);

        // This should print Task2
        binaryCriterion.search("search binary1", taskMap, criterionMap);
    }
    /**For the search function part 3*/
    @Test
    public void testSearchWithLogicalOr() {
        taskMap.clear();
        criterionMap.clear();
        TMS task1 = new PrimitiveTask("Task1", "This-is-a-task", x);
        TMS task2 = new PrimitiveTask("Task2", "This-is-another-task", y);

        taskMap.put("Task1", task1);
        taskMap.put("Task2", task2);

        Criterion basicCriterion1 = new DefineBasicCriterion("basic1", "priority", "<", "45");
        Criterion basicCriterion2 = new DefineBasicCriterion("basic2", "name", "==", "Task2");

        criterionMap.put("basic1", basicCriterion1);
        criterionMap.put("basic2", basicCriterion2);

        binaryCriterion.create("DefineBinaryCriterion binary1 basic1 || basic2", criterionMap);

        // This should print Task1 and Task2
        binaryCriterion.search("search binary1", taskMap, criterionMap);
    }

    /**For the test print function to check both tasks and criteria*/
    @Test
    public void testPrint() {

        taskMap.clear();
        criterionMap.clear();
        TMS task1 = new PrimitiveTask("Task1", "This-is-a-task", x);
        TMS task2 = new PrimitiveTask("Task2", "This-is-another-task", y);

        taskMap.put("Task1", task1);
        taskMap.put("Task2", task2);

        Criterion basicCriterion1 = new DefineBasicCriterion("basic1", "priority", "<", "45");
        Criterion basicCriterion2 = new DefineBasicCriterion("basic2", "name", "==", "Task2");

        criterionMap.put("basic1", basicCriterion1);
        criterionMap.put("basic2", basicCriterion2);

        binaryCriterion.create("DefineBinaryCriterion binary1 basic1 || basic2", criterionMap);
        binaryCriterion.printCriterion("binary1", criterionMap);
    }
}
