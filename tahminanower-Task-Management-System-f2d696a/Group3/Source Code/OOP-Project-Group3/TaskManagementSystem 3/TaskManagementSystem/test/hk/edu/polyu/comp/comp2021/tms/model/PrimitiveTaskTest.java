package hk.edu.polyu.comp.comp2021.tms.model;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the PrimitiveTask class.
 * The series of tests begin with testing the default (empty) constructor
 * to check if an instance of PrimitiveTask can be created successfully.
 * Subsequent tests will validate other aspects of the PrimitiveTask class
 * as per their functionality and requirements.
 * Each test within this class follows the JUnit framework,
 * and will pass if the class behaves as expected under the test conditions,
 * and fail otherwise.
 */
public class PrimitiveTaskTest {
    /**
     * This test checks if a new instance of the PrimitiveTask class
     * can be created successfully. The test uses reflection to create
     * an instance of the class using its default constructor (i.e., the
     * constructor with no arguments).
     * If the instantiation is successful (i.e., no exception is thrown),
     * the test passes; otherwise, it fails. The test can fail due to several
     * reasons, including (but not limited to):
     * - The PrimitiveTask class does not exist (ClassNotFoundException)
     * - The PrimitiveTask class does not have a no-arg constructor (NoSuchMethodException)
     * - The no-arg constructor of the PrimitiveTask class is not accessible (IllegalAccessException)
     * - The PrimitiveTask class or its no-arg constructor throws an exception (InvocationTargetException)
     *
     */
    @Test
    public void testPrimitiveTaskInstantiation1() {
        try {
            Class.forName("hk.edu.polyu.comp.comp2021.tms.model.PrimitiveTask").getDeclaredConstructor().newInstance();
            // If no exception is thrown, the test passes (i.e., the class could be instantiated).
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException e) {
            fail("Unexpected exception: " + e.getMessage());
        } catch (InstantiationException e) {
            fail("Should not have thrown an InstantiationException because ConcreteClassName is not abstract!");
        } catch (InvocationTargetException e) {
            fail("Unexpected target exception: " + e.getTargetException().getMessage());
        }
    }
    /**
     * This test checks if a new instance of the PrimitiveTask class
     * can be created successfully. The test uses reflection to create
     * an instance of the class using its default constructor (i.e., the
     * constructor with no arguments).
     * If the instantiation is successful (i.e., no exception is thrown),
     * the test passes; otherwise, it fails. The test can fail due to several
     * reasons, including (but not limited to):
     * - The PrimitiveTask class does not exist (ClassNotFoundException)
     * - The PrimitiveTask class does not have a no-arg constructor (NoSuchMethodException)
     * - The no-arg constructor of the PrimitiveTask class is not accessible (IllegalAccessException)
     * - The PrimitiveTask class or its no-arg constructor throws an exception (InvocationTargetException)
     *
     */
    @Test
    public void testPrimitiveTaskInstantiation2() {
        String string1 = "String1";
        String string2 = "String1";
        double double1 = 1.0;
        try {
            Class.forName("hk.edu.polyu.comp.comp2021.tms.model.PrimitiveTask").getDeclaredConstructor(String.class, String.class, double.class).newInstance(string1, string2, double1);
            // If no exception is thrown, the test passes (i.e., the class could be instantiated).
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException e) {
            fail("Unexpected exception: " + e.getMessage());
        } catch (InstantiationException e) {
            fail("Should not have thrown an InstantiationException because ConcreteClassName is not abstract!");
        } catch (InvocationTargetException e) {
            fail("Unexpected target exception: " + e.getTargetException().getMessage());
        }
    }
    /**Initialize variables for the tests below*/
    protected static Map<String, TMS> taskMap = new HashMap<>(); // the taskMap

    /**Initialize primitive task for the tests below*/
    protected PrimitiveTask taskP = new PrimitiveTask(); // empty primitive task

    /**Initialize composite task for the tests below*/
    protected CompositeTask taskC = new CompositeTask(); // empty composite task - for thorough testing

    /**Testing the create function for primitive tasks*/
    @Test
    public void testCreate(){
        String instruction1 = "CreatePrimitiveTask boiling boil-the-water-in-the-pot-and-set-to-simmer 0.5 ,";
        String instruction2 = "CreatePrimitiveTask !prep shred-the-chicken-and-dice-the-vegetables 0.5 ,";
        String instruction3 = "CreatePrimitiveTask nameTooBig shred-the-chicken-and-dice-the-vegetables 0.5 ,";
        taskP.create(instruction1, taskMap);
        taskP.create(instruction2, taskMap);
        taskP.create (instruction3, taskMap);
        assertTrue(taskMap.containsKey("boiling"));
        assertFalse(taskMap.containsKey("!prep")); // invalid name
        assertFalse(taskMap.containsKey("nameTooBig"));
    }

    /**Checking the change task function. It will modify task elements*/
    @Test
    public void testChangeTask(){
        String instruction1 = "CreatePrimitiveTask boiling boil-the-water-in-the-pot-and-set-to-simmer 0.5 ,";
        String instruction2 = "CreatePrimitiveTask prep shred-the-chicken-and-dice-the-vegetables 0.5 ,";
        String instruction3 = "CreatePrimitiveTask name shred-the-chicken-and-dice-the-vegetables 0.5 ,";
        String instruction4 = "CreateCompositeTask makeSoup steps-to-make-Chicken-soup boiling,prep";
        String instruction5 = "ChangeTask boiling duration 1.0";
        String instruction6 = "ChangeTask name duration 1.0";
        taskP.create(instruction1, taskMap);
        taskP.create(instruction2, taskMap);
        taskP.create (instruction3, taskMap);
        taskC.create (instruction4, taskMap);
        taskP.changeTask (instruction5, taskMap);
        taskP.changeTask (instruction6, taskMap);
        assertTrue(taskMap.containsKey("boiling"));
        assertTrue(taskMap.containsKey("name"));
    }
    /**Checking the printingTask function*/
    @Test
    public void testPrintTask(){
        String instruction1 = "CreatePrimitiveTask boiling boil-the-water-in-the-pot-and-set-to-simmer 0.5 ,";
        String instruction2 = "CreatePrimitiveTask prep shred-the-chicken-and-dice-the-vegetables 0.5 ,";
        String instruction3 = "PrintTask boiling";
        String instruction4 = "PrintTask prep";
        taskP.create(instruction1, taskMap);
        taskP.create(instruction2, taskMap);
        taskP.printTask (instruction3, taskMap);
        taskC.printTask (instruction4, taskMap);
    }
    /**The following function tests the report duration feature of the primitive task*/
    @Test
    public void testReportDuration(){
        String instruction1 = "CreatePrimitiveTask boiling boil-the-water-in-the-pot-and-set-to-simmer 0.5 ,";
        String instruction2 = "CreatePrimitiveTask prep shred-the-chicken-and-dice-the-vegetables 0.5 ,";
        String taskName = "boiling";
        String taskName2 = "preps";
        taskP.create(instruction1, taskMap);
        taskP.create(instruction2, taskMap);
        taskC.reportDuration (taskName2, taskMap);
        assertEquals(Double.parseDouble("0.5"), taskP.reportDuration (taskName, taskMap));
    }
    /**Testing the delete function for primitive task*/
    @Test
    public void testDelete(){
        String instruction1 = "CreatePrimitiveTask boiling boil-the-water-in-the-pot-and-set-to-simmer 0.5 ,";
        String instruction2 = "CreatePrimitiveTask prep shred-the-chicken-and-dice-the-vegetables 0.5 ,";
        String instruction3 = "CreatePrimitiveTask name shred-the-chicken-and-dice-the-vegetables 0.5 ,";
        String instruction4 = "CreateCompositeTask makeSoup steps-to-make-Chicken-soup boiling,prep";
        String instruction5 = "DeleteTask boiling";
        String instruction6 = "DeleteTask name";
        taskP.create(instruction1, taskMap);
        taskP.create(instruction2, taskMap);
        taskP.create (instruction3, taskMap);
        taskC.create (instruction4, taskMap);
        taskP.delete (instruction5, taskMap);
        taskP.delete (instruction6, taskMap);
        assertTrue(taskMap.containsKey("boiling"));
        assertFalse(taskMap.containsKey("name"));
    }
}


