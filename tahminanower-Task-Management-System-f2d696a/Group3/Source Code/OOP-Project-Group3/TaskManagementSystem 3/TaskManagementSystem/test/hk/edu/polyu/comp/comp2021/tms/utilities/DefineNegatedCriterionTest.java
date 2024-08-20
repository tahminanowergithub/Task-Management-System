package hk.edu.polyu.comp.comp2021.tms.utilities;

import hk.edu.polyu.comp.comp2021.tms.model.CompositeTask;
import hk.edu.polyu.comp.comp2021.tms.model.PrimitiveTask;
import hk.edu.polyu.comp.comp2021.tms.model.TMS;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
/**Testing the functions within the DefineNegatedCriterion class*/
public class DefineNegatedCriterionTest {
    /**This is testing if the no-arg constructor works as intended*/
    @Test
    public void testDefineNegatedCriterionInstantiation1() {
        try {
            Class.forName("hk.edu.polyu.comp.comp2021.tms.utilities.DefineNegatedCriterion").getDeclaredConstructor().newInstance();
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
    public void testDefineNegatedCriterionInstantiation2() {
        String string1 = "name";
        String string2 = "property";
        String string3 = "op";
        double double1 = 1.0;
        try {
            Class.forName("hk.edu.polyu.comp.comp2021.tms.utilities.DefineNegatedCriterion").getDeclaredConstructor(String.class, String.class, String.class, double.class).newInstance(string1, string2, string3, double1);
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
    public void testDefineNegatedCriterionInstantiation3() {
        String string1 = "name";
        String string2 = "property";
        String string3 = "op";
        String string4 = "val";
        try {
            Class.forName("hk.edu.polyu.comp.comp2021.tms.utilities.DefineNegatedCriterion").getDeclaredConstructor(String.class, String.class, String.class, String.class).newInstance(string1, string2, string3, string4);
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
    public void testDefineNegatedCriterionInstantiation4() {
        String string1 = "name";
        String string2 = "property";
        String string3 = "op";
        String[] stringArr = {"val1", "val2"};
        try {
            Class.forName("hk.edu.polyu.comp.comp2021.tms.utilities.DefineNegatedCriterion").getDeclaredConstructor(String.class, String.class, String.class, String[].class).newInstance(string1, string2, string3, stringArr);
            // If no exception is thrown, the test passes (i.e., the class could be instantiated).
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException e) {
            fail("Unexpected exception: " + e.getMessage());
        } catch (InstantiationException e) {
            fail("Should not have thrown an InstantiationException because ConcreteClassName is not abstract!");
        } catch (InvocationTargetException e) {
            fail("Unexpected target exception: " + e.getTargetException().getMessage());
        }
    }
    private DefineBasicCriterion criteriaBa = new DefineBasicCriterion(); // for basic criteria
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
    protected CompositeTask taskC = new CompositeTask();
    /**Testing the Creation of negated criterion*/
    @Test
    public void testNegatedCreate(){
        // define basic
        String instruction1 = "DefineBasicCriterion name1 name contains \"boiling\"";
        String instruction2 = "DefineBasicCriterion name2 description contains \"chicken\"";
        String instruction3 = "DefineBasicCriterion name3 prerequisites contains boiling,prep";
        String instruction4 = "DefineBasicCriterion name4 duration > 0.5";

        criteriaBa.create (instruction1, criterionMap);
        criteriaBa.create (instruction2, criterionMap);
        criteriaBa.create (instruction3, criterionMap);
        criteriaBa.create (instruction4, criterionMap);

        String instruction5 = "DefineNegatedCriterion name5 name1";
        String instruction6 = "DefineNegatedCriterion name6 name2";
        String instruction7 = "DefineNegatedCriterion name7 name3";
        String instruction8 = "DefineNegatedCriterion name8 name4";
        String instruction9 = "DefineNegatedCriterion name8 name1";
        String instruction10 = "DefineNegatedCriterion name9 name11"; // name11 doesn't exist
        String instruction11 = "DefineNegatedCriterion name9 name1 dfdfddf"; // invalid command

        criteriaNe.create(instruction5, criterionMap);
        criteriaNe.create(instruction6, criterionMap);
        criteriaNe.create(instruction7, criterionMap);
        criteriaNe.create(instruction8, criterionMap);
        criteriaNe.create(instruction9, criterionMap);
        criteriaNe.create(instruction10, criterionMap);

        assertTrue(criterionMap.containsKey("name5"));
        assertTrue(criterionMap.containsKey("name6"));
        assertTrue(criterionMap.containsKey("name7"));
        assertTrue(criterionMap.containsKey("name8"));
        assertFalse(criterionMap.containsKey("name9"));
    }
    /**Testing the searching for negated criterion*/
    @Test
    public void testSearch(){
        // note: this test is supposed to be run with all the other tests at the same time
        String instruction1 = "CreatePrimitiveTask boiling boil-the-water-in-the-pot-and-set-to-simmer 0.5 ,";
        String instruction2 = "CreatePrimitiveTask prep shred-the-chicken-and-dice-the-vegetables 0.5 ,";
        String instruction3 = "CreatePrimitiveTask name shred-the-chicken-and-dice-the-vegetables 0.5 ,";
        String instruction4 = "CreateCompositeTask makeSoup steps-to-make-Chicken-soup boiling,prep";

        taskP.create (instruction1, taskMap);
        taskP.create (instruction2, taskMap);
        taskP.create (instruction3, taskMap);
        taskC.create (instruction4, taskMap);

        criteriaNe.search("Search name5", taskMap, criterionMap);
        criteriaNe.search("Search name6", taskMap, criterionMap);
        criteriaNe.search("Search name7", taskMap, criterionMap);
        criteriaNe.search("Search name8", taskMap, criterionMap);
        criteriaNe.search("Search name9", taskMap, criterionMap);
        criteriaNe.search("Search name9 something", taskMap, criterionMap);
    }

    /**Testing the print function for Negated Criterion*/
    @Test
    public void testPrintNegated(){
        criterionMap.clear();
        String instruction1 = "DefineBasicCriterion name1 name contains \"boiling\"";
        String instruction2 = "DefineBasicCriterion name2 description contains \"chicken\"";
        String instruction3 = "DefineBasicCriterion name3 prerequisites contains boiling,prep";
        String instruction4 = "DefineBasicCriterion name4 duration > 0.5";

        criteriaBa.create (instruction1, criterionMap);
        criteriaBa.create (instruction2, criterionMap);
        criteriaBa.create (instruction3, criterionMap);
        criteriaBa.create (instruction4, criterionMap);

        String instruction5 = "DefineNegatedCriterion name5 name1";
        String instruction6 = "DefineNegatedCriterion name6 name2";
        String instruction7 = "DefineNegatedCriterion name7 name3";
        String instruction8 = "DefineNegatedCriterion name8 name4";
        String instruction9 = "DefineNegatedCriterion name8 name1";
        String instruction10 = "DefineNegatedCriterion name9 name11"; // name11 doesn't exist
        String instruction11 = "DefineNegatedCriterion name9 name1 dfdfddf"; // invalid command

        criteriaNe.create(instruction5, criterionMap);
        criteriaNe.create(instruction6, criterionMap);
        criteriaNe.create(instruction7, criterionMap);
        criteriaNe.create(instruction8, criterionMap);
        criteriaNe.create(instruction9, criterionMap);
        criteriaNe.create(instruction10, criterionMap);

        criteriaNe.printCriterion("name5", criterionMap);
        criteriaNe.printCriterion("name6", criterionMap);
        criteriaNe.printCriterion("name7", criterionMap);
        criteriaNe.printCriterion("name8", criterionMap);
        criteriaNe.printCriterion("name9", criterionMap);
    }
}
