package hk.edu.polyu.comp.comp2021.tms.utilities;

import hk.edu.polyu.comp.comp2021.tms.model.CompositeTask;
import hk.edu.polyu.comp.comp2021.tms.model.PrimitiveTask;
import hk.edu.polyu.comp.comp2021.tms.model.TMS;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**Testing all methods found in the DefinBasicCriterion class*/
public class DefineBasicCriterionTest {
    /**This is testing if the no-arg constructor works as intended*/
    @Test
    public void testDefineBasicCriterionInstantiation1() {
        try {
            Class.forName("hk.edu.polyu.comp.comp2021.tms.utilities.DefineBasicCriterion").getDeclaredConstructor().newInstance();
            // If no exception is thrown, the test passes (i.e., the class could be instantiated).
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException e) {
            fail("Unexpected exception: " + e.getMessage());
        } catch (InstantiationException e) {
            fail("Should not have thrown an InstantiationException because ConcreteClassName is not abstract!");
        } catch (InvocationTargetException e) {
            fail("Unexpected target exception: " + e.getTargetException().getMessage());
        }
    }
    /**Testing the constructor with arguments passed as required*/
    @Test
    public void testDefineBasicCriterionInstantiation2() {
        String string1 = "name";
        String string2 = "property";
        String string3 = "op";
        double double1 = 1.0;
        try {
            Class.forName("hk.edu.polyu.comp.comp2021.tms.utilities.DefineBasicCriterion").getDeclaredConstructor(String.class, String.class, String.class, double.class).newInstance(string1, string2, string3, double1);
            // If no exception is thrown, the test passes (i.e., the class could be instantiated).
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException e) {
            fail("Unexpected exception: " + e.getMessage());
        } catch (InstantiationException e) {
            fail("Should not have thrown an InstantiationException because ConcreteClassName is not abstract!");
        } catch (InvocationTargetException e) {
            fail("Unexpected target exception: " + e.getTargetException().getMessage());
        }
    }
    /**Testing the constructor with arguments passed as required*/
    @Test
    public void testDefineBasicCriterionInstantiation3() {
        String string1 = "name";
        String string2 = "property";
        String string3 = "op";
        String string4 = "val";
        try {
            Class.forName("hk.edu.polyu.comp.comp2021.tms.utilities.DefineBasicCriterion").getDeclaredConstructor(String.class, String.class, String.class, String.class).newInstance(string1, string2, string3, string4);
            // If no exception is thrown, the test passes (i.e., the class could be instantiated).
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException e) {
            fail("Unexpected exception: " + e.getMessage());
        } catch (InstantiationException e) {
            fail("Should not have thrown an InstantiationException because ConcreteClassName is not abstract!");
        } catch (InvocationTargetException e) {
            fail("Unexpected target exception: " + e.getTargetException().getMessage());
        }
    }
    /**Testing the constructor with arguments passed as required*/
    @Test
    public void testDefineBasicCriterionInstantiation4() {
        String string1 = "name";
        String string2 = "property";
        String string3 = "op";
        String[] stringArr = {"val1", "val2"};
        try {
            Class.forName("hk.edu.polyu.comp.comp2021.tms.utilities.DefineBasicCriterion").getDeclaredConstructor(String.class, String.class, String.class, String[].class).newInstance(string1, string2, string3, stringArr);
            // If no exception is thrown, the test passes (i.e., the class could be instantiated).
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException e) {
            fail("Unexpected exception: " + e.getMessage());
        } catch (InstantiationException e) {
            fail("Should not have thrown an InstantiationException because ConcreteClassName is not abstract!");
        } catch (InvocationTargetException e) {
            fail("Unexpected target exception: " + e.getTargetException().getMessage());
        }
    }
    /**Initialize Basic Criteria*/
    protected DefineBasicCriterion criteriaBa = new DefineBasicCriterion(); // for basic criteria
    /**Initialize Binary Criteria*/
    protected DefineBinaryCriterion criteriaBi = new DefineBinaryCriterion(); // for binary criteria
    /**Initialize Negated Criteria*/
    protected DefineNegatedCriterion criteriaNe = new DefineNegatedCriterion(); // for negated criteria
    /**Initialize task map*/
    protected static Map<String, TMS> taskMap = new HashMap<>(); // the taskMap
    /**Initialize criterion map*/
    protected static Map<String, Criterion> criterionMap = new HashMap<>(); // HashMap for criteria
    /**Initialize Primitive Task*/
    protected PrimitiveTask taskP = new PrimitiveTask(); // empty primitive task
    /**Initialize Composite Task*/
    protected CompositeTask taskC = new CompositeTask(); // empty composite task - for thorough testing
    /**Testing the create function for primitive tasks*/
    @Test
    public void testCreate(){
        criterionMap.clear();
        String instruction1 = "DefineBasicCriterion name1 name contains \"boiling\"";
        String instruction2 = "DefineBasicCriterion name2 name > \"something\"";
        String instruction3 = "DefineBasicCriterion name3 description contains \"chicken\"";
        String instruction4 = "DefineBasicCriterion name4 description > \"something\"";
        String instruction5 = "DefineBasicCriterion name5 prerequisites contains boiling,prep";
        String instruction6 = "DefineBasicCriterion name6 prerequisites < something";
        String instruction7 = "DefineBasicCriterion name7 prerequisite > something,something2";
        String instruction8 = "DefineBasicCriterion name8 contains \"boiling\"";

        criteriaBa.create (instruction1, criterionMap);
        criteriaBa.create (instruction2, criterionMap);
        criteriaBa.create (instruction3, criterionMap);
        criteriaBa.create (instruction4, criterionMap);
        criteriaBa.create (instruction5, criterionMap);
        criteriaBa.create (instruction6, criterionMap);
        criteriaBa.create (instruction7, criterionMap);
        criteriaBa.create (instruction8, criterionMap);

        assertTrue (criterionMap.containsKey("name1"));
        assertFalse (criterionMap.containsKey("name2"));
        assertTrue (criterionMap.containsKey("name3"));
        assertFalse (criterionMap.containsKey("name4"));
        assertTrue (criterionMap.containsKey("name5"));
        assertFalse (criterionMap.containsKey("name6"));
        assertFalse (criterionMap.containsKey("name7"));
        assertFalse (criterionMap.containsKey("name8"));
    }
    /**Now testing the search function using the criteria defined*/
    @Test
    public void testSearch(){
        criterionMap.clear();
        taskMap.clear();
        // instruction for tasks
        String instruction1 = "CreatePrimitiveTask boiling boil-the-water-in-the-pot-and-set-to-simmer 0.5 ,";
        String instruction2 = "CreatePrimitiveTask prep shred-the-chicken-and-dice-the-vegetables 0.5 ,";
        String instruction3 = "CreatePrimitiveTask name shred-the-chicken-and-dice-the-vegetables 0.5 ,";
        String instruction4 = "CreateCompositeTask makeSoup steps-to-make-Chicken-soup boiling,prep";

        // instructions for criterion
        String instruction5 = "DefineBasicCriterion name1 name contains \"boiling\"";
        String instruction6 = "DefineBasicCriterion name2 description contains \"chicken\"";
        String instruction7 = "DefineBasicCriterion name3 prerequisites contains boiling,prep";
        String instruction8 = "DefineBasicCriterion name4 contains \"boiling\""; // invalid format
        String instruction9 = "DefineBasicCriterion name5 duration > 0.5";

        //creating everyting
        taskP.create (instruction1, taskMap);
        taskP.create (instruction2, taskMap);
        taskP.create (instruction3, taskMap);
        taskC.create (instruction4, taskMap);
        criteriaBa.create (instruction5, criterionMap);
        criteriaBa.create (instruction6, criterionMap);
        criteriaBa.create (instruction7, criterionMap);
        criteriaBa.create (instruction8, criterionMap);
        criteriaBa.create (instruction9, criterionMap);

        // finally search
        criteriaBa.search("Search name1", taskMap, criterionMap);
        criteriaBa.search("Search name2", taskMap, criterionMap);
        criteriaBa.search("Search name3", taskMap, criterionMap);
        criteriaBa.search("Search name4", taskMap, criterionMap);
        criteriaBa.search("Search name5", taskMap, criterionMap);
    }
    /**Testing the printBasicCriterionFunction*/
    @Test
    public void testPrintBasic(){
        String instruction5 = "DefineBasicCriterion name1 name contains \"boiling\"";
        String instruction6 = "DefineBasicCriterion name2 description contains \"chicken\"";
        String instruction7 = "DefineBasicCriterion name3 prerequisites contains boiling,prep";
        String instruction8 = "DefineBasicCriterion name4 contains \"boiling\""; // invalid format
        String instruction9 = "DefineBasicCriterion name5 duration > 0.5";

        criteriaBa.create (instruction5, criterionMap);
        criteriaBa.create (instruction6, criterionMap);
        criteriaBa.create (instruction7, criterionMap);
        criteriaBa.create (instruction8, criterionMap);
        criteriaBa.create (instruction9, criterionMap);

        criteriaBa.printCriterion("name1", criterionMap);
        criteriaBa.printCriterion("name2", criterionMap);
        criteriaBa.printCriterion("name3", criterionMap);
        criteriaBa.printCriterion("name4", criterionMap);
        criteriaBa.printCriterion("name5", criterionMap);
    }
}
