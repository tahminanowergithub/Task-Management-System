package hk.edu.polyu.comp.comp2021.tms;

import hk.edu.polyu.comp.comp2021.tms.model.CompositeTask;
import hk.edu.polyu.comp.comp2021.tms.model.PrimitiveTask;
import hk.edu.polyu.comp.comp2021.tms.model.TMS;
import org.junit.jupiter.api.Test;


import java.util.HashMap;
import java.util.Map;




/** This class is to test some of the basic functions found in application*/
public class ApplicationTest {

    /** Testing the printing features for the interface*/
    protected static Map<String, TMS> taskMap = new HashMap<>(); // the taskMap
    protected PrimitiveTask taskP = new PrimitiveTask(); // empty primitive task
    protected CompositeTask taskC = new CompositeTask(); // empty composite task - for thorough testing
    /**Testing the printing features for the interface*/
    @Test
    public void testIntroduction(){
        Application.introduction();
        System.out.println ("Runs correctly");
    }
    /**Testing the helping bot*/
    @Test
    public void testPrintAllTasks(){
        String instruction1 = "CreatePrimitiveTask boiling boil-the-water-in-the-pot-and-set-to-simmer 0.5 ,";
        String instruction2 = "CreatePrimitiveTask prep shred-the-chicken-and-dice-the-vegetables 0.5 ,";
        String instruction3 = "CreateCompositeTask nameTooBig steps-to-make-Chicken-soup boiling,prep";
        String instruction4 = "CreateCompositeTask makeSoup steps-to-make-Chicken-soup boiling,prep";
        taskP.create(instruction1, Application.taskMap);
        taskP.create(instruction2, Application.taskMap);
        taskC.create (instruction3, Application.taskMap);
        taskC.create(instruction4, Application.taskMap);
        Application.printAllTasks();
    }
}
