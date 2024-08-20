package hk.edu.polyu.comp.comp2021.tms.model;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**The following class is used to test the composite task*/
public class CompositeTaskTest {
    /**First, testing the empty constructor*/
    @Test
    public void testCompositeTaskInitialization() {
        try {
            Class.forName("hk.edu.polyu.comp.comp2021.tms.model.CompositeTask").getDeclaredConstructor().newInstance();
            // If no exception is thrown, the test passes (i.e., the class could be instantiated).
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException e) {
            fail("Unexpected exception: " + e.getMessage());
        } catch (InstantiationException e) {
            fail("Should not have thrown an InstantiationException because ConcreteClassName is not abstract!");
        } catch (InvocationTargetException e) {
            fail("Unexpected target exception: " + e.getTargetException().getMessage());
        }
    }
    /**Testing the formal constructor by passing arguments to it*/
    @Test
    public void testCompositeTaskInitialization2() {
        String string1 = "String1";
        String string2 = "String1";
        String[] arrayString = {"subtask1", "subtask2"};
        try {
            Class.forName("hk.edu.polyu.comp.comp2021.tms.model.CompositeTask").getDeclaredConstructor(String.class, String.class, String[].class).newInstance(string1, string2, arrayString);
            // If no exception is thrown, the test passes (i.e., the class could be instantiated).
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException e) {
            fail("Unexpected exception: " + e.getMessage());
        } catch (InstantiationException e) {
            fail("Should not have thrown an InstantiationException because ConcreteClassName is not abstract!");
        } catch (InvocationTargetException e) {
            fail("Unexpected target exception: " + e.getTargetException().getMessage());
        }
    }
    /**Initialize task map for the tests below*/
    protected static Map<String, TMS> taskMap = new HashMap<>(); // the taskMap

    /**Initialize primitive task for the tests below*/
    protected PrimitiveTask taskP = new PrimitiveTask(); // empty primitive task

    /**Initialize composite task for the tests below*/
    protected CompositeTask taskC = new CompositeTask(); // empty composite task - for thorough testing
    /**Testing the create function for primitive tasks*/
    @Test
    public void testCreate(){
        String instruction1 = "CreatePrimitiveTask boiling boil-the-water-in-the-pot-and-set-to-simmer 0.5 ,";
        String instruction2 = "CreatePrimitiveTask prep shred-the-chicken-and-dice-the-vegetables 0.5 ,";
        String instruction3 = "CreateCompositeTask nameTooBig steps-to-make-Chicken-soup boiling,prep";
        String instruction4 = "CreateCompositeTask makeSoup steps-to-make-Chicken-soup boiling,prep";
        taskP.create(instruction1, taskMap);
        taskP.create(instruction2, taskMap);
        taskC.create (instruction3, taskMap);
        taskC.create(instruction4, taskMap);
        assertTrue(taskMap.containsKey("makeSoup"));
        assertFalse(taskMap.containsKey("nameTooBig")); // invalid name
    }

    /**Testing the delete function for primitive task*/
    @Test
    public void testDelete(){
        taskMap.clear();
        String instruction1 = "CreatePrimitiveTask boiling boil-the-water-in-the-pot-and-set-to-simmer 0.5 ,";
        String instruction2 = "CreatePrimitiveTask prep shred-the-chicken-and-dice-the-vegetables 0.5 ,";
        String instruction3 = "CreatePrimitiveTask name shred-the-chicken-and-dice-the-vegetables 0.5 ,";
        String instruction4 = "CreateCompositeTask makeSoup steps-to-make-Chicken-soup boiling,prep";
        String instruction5 = "CreateCompositeTask new testing-something boiling,makeSoup";
        String instruction6 = "DeleteTask makeSoup";
        String instruction7 = "DeleteTask new";
        taskP.create(instruction1, taskMap);
        taskP.create(instruction2, taskMap);
        taskP.create (instruction3, taskMap);
        taskC.create (instruction4, taskMap);
        taskC.create (instruction5, taskMap);
        taskC.delete (instruction6, taskMap);
        taskC.delete (instruction7, taskMap);
        assertTrue(taskMap.containsKey("makeSoup"));
        assertTrue(taskMap.containsKey("new"));
    }
    /**Checking the change task function. It will modify task elements*/
    @Test
    public void testChangeTask(){
        taskMap.clear();
        String instruction1 = "CreatePrimitiveTask boiling boil-the-water-in-the-pot-and-set-to-simmer 0.5 ,";
        String instruction2 = "CreatePrimitiveTask prep shred-the-chicken-and-dice-the-vegetables 0.5 ,";
        String instruction3 = "CreatePrimitiveTask name shred-the-chicken-and-dice-the-vegetables 0.5 ,";
        String instruction4 = "CreateCompositeTask makeSoup steps-to-make-Chicken-soup boiling,prep";
        String instruction5 = "ChangeTask makeSoup name Soups";
        String instruction6 = "ChangeTask makeSoup subtask boiling,prep,name";
        String instruction7 = "ChangeTask makeSoup description new-description";
        taskP.create(instruction1, taskMap);
        taskP.create(instruction2, taskMap);
        taskP.create (instruction3, taskMap);
        taskC.create (instruction4, taskMap);
        taskC.changeTask (instruction5, taskMap);
        taskC.changeTask (instruction6, taskMap);
        assertFalse(taskMap.containsKey("Soups"));
        assertTrue(taskMap.containsKey("makeSoup"));
    }
    /**Checking the printingTask function*/
    @Test
    public void testPrintTask(){
        taskMap.clear();
        String instruction1 = "CreatePrimitiveTask boiling boil-the-water-in-the-pot-and-set-to-simmer 0.5 ,";
        String instruction2 = "CreatePrimitiveTask prep shred-the-chicken-and-dice-the-vegetables 0.5 ,";
        String instruction3 = "CreateCompositeTask makeSoup steps-to-make-Chicken-soup boiling,prep";
        String instruction4 = "PrintTask makeSoup";
        taskP.create(instruction1, taskMap);
        taskP.create(instruction2, taskMap);
        taskC.create (instruction3, taskMap);
        taskC.printTask (instruction4, taskMap);
    }
    /**The following function tests the report duration feature of the primitive task*/
    @Test
    public void testReportDuration(){
        taskMap.clear();
        String instruction1 = "CreatePrimitiveTask boiling boil-the-water-in-the-pot-and-set-to-simmer 0.5 ,";
        String instruction2 = "CreatePrimitiveTask prep shred-the-chicken-and-dice-the-vegetables 0.5 ,";
        String instruction3 = "CreateCompositeTask makeSoup steps-to-make-Chicken-soup boiling,prep";
        String taskName = "makeSoup";
        taskP.create(instruction1, taskMap);
        taskP.create(instruction2, taskMap);
        taskC.create(instruction3, taskMap);
        //taskC.reportDuration (taskName2, taskMap);
        assertEquals(1.0, taskC.reportDuration (taskName, taskMap));
    }
    /**The following function tests the report earliest finish time feature of the primitive task*/
    @Test
    public void testEarliestFinishTime(){
        taskMap.clear();
        String instruction1 = "CreatePrimitiveTask boiling boil-the-water-in-the-pot-and-set-to-simmer 0.5 ,";
        String instruction2 = "CreatePrimitiveTask prep shred-the-chicken-and-dice-the-vegetables 0.5 ,";
        String instruction3 = "CreateCompositeTask makeSoup steps-to-make-Chicken-soup boiling,prep";
        String taskName = "makeSoup";
        taskP.create(instruction1, taskMap);
        taskP.create(instruction2, taskMap);
        taskC.create(instruction3, taskMap);
        //taskC.reportDuration (taskName2, taskMap);
        assertEquals(1.0, taskC.reportEarliestFinishTime (taskName, taskMap));
    }
}
