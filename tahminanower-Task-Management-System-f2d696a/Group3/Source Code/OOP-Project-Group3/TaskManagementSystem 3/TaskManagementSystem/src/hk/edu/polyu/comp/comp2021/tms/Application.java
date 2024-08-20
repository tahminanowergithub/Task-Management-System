package hk.edu.polyu.comp.comp2021.tms;

import hk.edu.polyu.comp.comp2021.tms.model.*;
import hk.edu.polyu.comp.comp2021.tms.utilities.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * Class that represents the main entry point for the Task Management System (TMS) application,
 * providing functionality for creating, managing, and interacting with tasks and criteria.
 * Users can execute various operations such as creating tasks, defining criteria, searching, and more.
 * Additionally, users can save and load preset up files with maps of tasks and criteria.
 */

public class Application {

    /** A map to store tasks, both primitive and composite, using their names as keys. */
    protected static Map<String, TMS> taskMap = new HashMap<>(); // initializing storage choice

    /** A map to store criteria using their names as keys. */
    protected static Map<String, Criterion> criterionMap = new HashMap<>(); // HashMap for criterion

    /**
     * The main method that represents the main entry point for the Task Management System (TMS) application.
     * @param args Command-line arguments (not used in this application).
     * @throws IOException checks if input/output exception occurs during file operations.
     * @throws ClassNotFoundException checks if a class is not found during deserialization.
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //TMS tms = new TMS();
        PrimitiveTask taskP = new PrimitiveTask();
        CompositeTask taskC = new CompositeTask();

        DefineBasicCriterion criteriaBa = new DefineBasicCriterion();
        DefineBinaryCriterion criteriaBi = new DefineBinaryCriterion();
        DefineNegatedCriterion criteriaNe = new DefineNegatedCriterion();

        // Initialize and run the system
        Scanner scanner = new Scanner(System.in);
        introduction();

        while (true) {
            System.out.println("\nEnter a command : ");
            String input = scanner.nextLine();

            if (input.startsWith("CreatePrimitiveTask")) {taskP.create(input,taskMap);}
            else if (input.startsWith("CreateCompositeTask")) {taskC.create(input, taskMap);}

            else if (input.startsWith("DeleteTask")) {
                String[] inputParts = input.split(" ");
                String taskName = inputParts[1];
                System.out.println(taskMap.get(taskName).delete(input, taskMap));
            }
            else if (input.startsWith("ChangeTask")) {
                String taskName = input.split(" ")[1];
                taskP.changeTask("ChangeTask " + taskName, taskMap);
                taskC.changeTask("ChangeTask " + taskName, taskMap);
            }
            else if (input.startsWith("PrintTask")) {
                String[] inputParts = input.split(" ");
                String taskName = inputParts[1];
                TMS task = taskMap.get(taskName);
                if (task.isPrimitive(task.getName(), taskMap)) {
                    taskP.printTask(input,taskMap);
                } else if (!task.isPrimitive(task.getName(), taskMap)) {
                    taskC.printTask(input, taskMap);
                }
            }
            else if (input.startsWith("PrintAllTasks")) { printAllTasks(); }
            else if (input.startsWith("ReportDuration")) {
                String[] inputParts = input.split (" ");
                if (inputParts.length >=2 && taskC.isName (inputParts[1])){
                    double ans = taskC.reportDuration (inputParts[1],taskMap);
                    if (ans!=0) System.out.println (inputParts[1] + " will take "+ans+" time.");
                    else {System.out.println ("The specified task does not exist in the system");}
                }
                else {System.out.println ("Invalid Command Passed.");}

            }
            else if (input.startsWith("ReportEarliestFinishTime")) {
                String[] inputParts = input.split (" ");
                if (inputParts.length >= 2 && taskC.isName(inputParts[1])){
                    double ans = taskC.reportEarliestFinishTime (inputParts[1],taskMap);
                    if (ans!=0) System.out.println (inputParts[1] + " will take at least "+ans+" time.");
                    else {System.out.println ("The specified task does not exist in the system");}
                }
                else {System.out.println ("Invalid Command Passed.");}
            }
            else if (input.startsWith("DefineBasicCriterion")){ criteriaBa.create (input, criterionMap); }
            else if (input.startsWith("IsPrimitive")) {
                System.out.println(taskP.isPrimitive(input.split(" ")[1].strip(), taskMap) ? "Yes" : "No");
            }
            else if (input.startsWith("DefineNegatedCriterion")) { criteriaNe.create(input, criterionMap); }
            else if (input.startsWith("DefineBinaryCriterion")) { criteriaBi.create(input, criterionMap); }
            else if (input.startsWith("PrintAllCriteria")) {
                for (Criterion criteria : criterionMap.values()) {
                    criteria.printCriterion(criteria.getName(), criterionMap);
                }
            } else if (input.startsWith("Search")) {
                printAllCriteria();
            }
            else if (input.startsWith("Store")) { UserControl.saveMap (input,taskMap, criterionMap); }
            else if (input.startsWith("Load")) { UserControl.loadMap (input,taskMap, criterionMap); }
            else if (input.equalsIgnoreCase("Quit")) {
                System.out.println("Thank You for using the System.");
                System.out.println ("All unsaved changes will be discarded.");
                System.out.println ("Would you like to proceed? (Y?N)");
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("Y")){
                    System.out.println("Shutting Down...");
                    break;
                }
                System.out.println ("Shut down process halted.");
            } else if (input.startsWith("Undo")) {
                // BONUS: call respective function - anyone who's free

            } else if (input.startsWith("Redo")) {
                // BONUS: call respective function - anyone who's free
            }
            else if (input.equalsIgnoreCase("Help")){ helper(); }
            else { System.out.println("Unknown command."); }
        }
        scanner.close();
    }

    /* This is the end of the Main application. Below are functions used to re-generate parts of the interface*/

    /**
     * Prints information about all the criteria stored in the task map.
     * If no tasks are in map, a message indicating it will be displayed.
     */

    public static void printAllCriteria() {
        if (criterionMap.isEmpty()) {
            System.out.println("No criteria available.");
        } else{
            for (Criterion criteria : criterionMap.values()) {
                criteria.printCriterion(criteria.getName(), criterionMap);
            }
        }
    }
    /**
     * Prints information about all the tasks stored in the task map.
     * If no tasks are in map, a message indicating it will be displayed.
     */

    public static void printAllTasks() {
        if (taskMap.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            for (TMS task : taskMap.values()) {
                if (task.isPrimitive(task.getName(), taskMap)) {
                    PrimitiveTask primitiveTask = (PrimitiveTask) task;
                    System.out.println("Task Name: " + primitiveTask.getName());
                    System.out.println("Description: " + primitiveTask.getDescription());
                    System.out.println("Duration: " + primitiveTask.getDuration());
                    System.out.println();
                } else if (!task.isPrimitive(task.getName(), taskMap)) {
                    CompositeTask compositeTask = (CompositeTask) task;
                    System.out.println("Task Name: " + compositeTask.getName());
                    System.out.println("Description: " + compositeTask.getDescription());
                    System.out.println("Subtasks: " + String.join(", ", compositeTask.getPrerequisites()));
                    System.out.println();
                }
            }
        }
    }

    /**
     * Method provides assistance and additional information related to the Task Management System (TMS)
     * application. It guides the user through different options, including understanding the system,
     * learning command syntax, and contacting the developers for support. The method uses a
     * console-based interaction to gather user input and respond accordingly, offering information
     * about the system, command syntax, and developer contact details.
     */
    public static void helper (){
        Scanner scanner = new Scanner(System.in);
        System.out.println ("Thank you for using the help service.\n");
        System.out.println ("What can I help you with today?");
        System.out.println ("\t1. About this system");
        System.out.println ("\t2. How to write commands");
        System.out.println ("\t3. Contact the developers\n");

        System.out.println ("Your question? [1/2/3]: ");
        String choice = scanner.nextLine();
        if (choice.startsWith("1")){
            System.out.println("This is group 3's command line based task management system\n " +
                    "It was made for subject called COMP2021 Object-Oriented Programming course");
        }
        else if (choice.startsWith("2")){
            // new case statement with all the commands
            while (true) {
                System.out.println("\nWhich command's syntax would you like to know?");
                String ques = scanner.nextLine();
                if (ques.startsWith("Store")) {
                    System.out.println("Syntax: Store <path of storage location>");
                } else if (ques.startsWith("Load")) {
                    System.out.println("Syntax: Load <path of storage location>");
                } else if (ques.startsWith("CreatePrimitiveTask")) {
                    System.out.println("Syntax: CreatePrimitiveTask name description duration ,");
                } else if (ques.startsWith("CreateCompositeTask")) {
                    System.out.println("Syntax: CreateCompositeTask name description pre-req1,pre-req2,pre-req3,.....");
                } else if (ques.startsWith("DeleteTask")) {
                    System.out.println("Syntax: DeleteTask nameOfTask");
                } else if (ques.startsWith("ChangeTask")) {
                    System.out.println("Syntax: ChangeTask nameOfTask propertyToChange newValue");
                } else if (ques.startsWith("PrintTask")) {
                    System.out.println("Syntax: PrintTask nameOfTask");
                } else if (ques.startsWith("PrintAllTasks")) {
                    System.out.println("Syntax: PrintAllTasks");
                } else if (ques.startsWith("ReportDuration")) {
                    System.out.println("Syntax: ReportDuration nameOfTask");
                } else if (ques.startsWith("ReportEarliestFinishTime")) {
                    System.out.println("Syntax: ReportEarliestFinishTime nameOfTask");
                } else if (ques.startsWith("DefineBasicCriterion")) {
                    System.out.println("Syntax: DefineBasicCriterion nameOfCriterion propertyAffected operation value");
                } else if (ques.startsWith("IsPrimitive")) {
                    System.out.println("Syntax: IsPrimitive nameOfTask");
                } else if (ques.startsWith("DefineNegatedCriterion")) {
                    System.out.println("Syntax: DefineNegatedCriterion nameOfCriterion nameOfExistingCriterion");
                } else if (ques.startsWith("DefineBinaryCriterion")) {
                    System.out.println("Syntax: DefineBinaryCriterion nameOfCriterion nameOfExistingCriterion1 logicOP nameOfExistingCriterion2");
                } else if (ques.startsWith("PrintAllCriteria")) {
                    System.out.println("Syntax: PrintAllCriteria");
                } else if (ques.startsWith("Search")) {
                    System.out.println("Syntax: Search nameOfCriteria");
                } else if (ques.equalsIgnoreCase("Quit")) {
                    break;
                } else {
                    System.out.println("Sorry, this command cannot be found");
                }
            }
        }
        else if (choice.startsWith("3")){
            // developer support string
            System.out.println ("Thank you for your interest in reaching out.");
            System.out.println ("To contact us directly, please refer to the instructions below.");
            System.out.println ("\tInstitution: The Hong Kong Polytechnic University");
            System.out.println ("\tLocation: Hong Kong, SAR");
            System.out.println ("\tPhone No: +852 55763918");
            System.out.println ("Do note that only WhatsApp messages are entertained\n");
        }
        else {
            System.out.println ("It seems that I am unable to help with your query.");
            System.out.println ("Please reach out to the developers using the contact information provided.\n\n");
        }
    }

    /**
     * Method displays an introductory message for the Task Management System (TMS) application.
     * It provides an overview of the available functions and commands in the system,
     * categorizing them into user control functions, task commands, and criterion commands.
     * The method serves as an initial guide for users interacting with the TMS application,
     * outlining the functionalities and commands they can use.
     */
    public static void introduction (){
        System.out.println ("\t\tTASK MANAGEMENT SYSTEM\n");
        System.out.println ("Available Functions:");
        System.out.println ("1. USER CONTROL FUNCTIONS");
        System.out.println ("\tStore");
        System.out.println ("\tLoad");
        System.out.println ("2. TASK COMMANDS");
        System.out.println ("\tCreatePrimitiveTask");
        System.out.println ("\tCreateCompositeTask");
        System.out.println ("\tDeleteTask");
        System.out.println ("\tChangeTask");
        System.out.println ("\tPrintTask");
        System.out.println ("\tPrintAllTasks");
        System.out.println ("\tReportDuration");
        System.out.println ("\tReportEarliestFinishTime");
        System.out.println ("3. CRITERION COMMANDS");
        System.out.println ("\tDefineBasicCriterion");
        System.out.println ("\tIsPrimitive");
        System.out.println ("\tDefineNegatedCriterion");
        System.out.println ("\tDefineBinaryCriterion");
        System.out.println ("4. Quit");
        System.out.println ("5. Help");
    }

}
