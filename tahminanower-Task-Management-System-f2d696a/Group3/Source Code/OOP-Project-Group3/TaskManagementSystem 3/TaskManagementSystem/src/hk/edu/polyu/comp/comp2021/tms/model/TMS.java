package hk.edu.polyu.comp.comp2021.tms.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Abstract Class used to create Primitive and Composite Tasks.
 * The class also implements the Serializable interface which is used
 * for saving and loading its contents to a file.
 */
public abstract class TMS implements Serializable {
    private String name;
    private String description;
    private double duration;
    private List<String> prerequisites;

    /**
     * Default constructor for the TMS class. <p>
     * This no-argument constructor that's used for the initialization of its children.
     */
    public TMS (){
        // Empty constructors for children initialization
    }

    /** Constructors for TMS initializing primitive task
     * @param name contains the name of the Primitive Task
     * @param description contains the description of the task
     * @param duration contains the time needed to complete the task*/
    public TMS (String name, String description, double duration){
        // Constructor used for Primitive Tasks
        this.name = name;
        this.description = description;
        this.duration = duration;
    }

    /** Constructors for TMS for initializing composite task
     * @param name contains the name of the Primitive Task
     * @param description contains the description of the task
     * @param subtaskNames contains the time needed to complete the task*/
    public TMS (String name, String description, String[] subtaskNames){
        // Constructor used for Composite Tasks
        this.name = name;
        this.description = description;
        this.prerequisites = Arrays.asList (subtaskNames);
    }

    /** Common function - For all types of tasks
     * Defined Function: returns the name of the TMS object that calls it.
     *                     It includes both Primitive and Composite Tasks.
     *
     * @return String representation of name task
     */
    public String getName() {
        return name;
    }

    /** Common function - For all types of tasks
     * Defined Function: returns the name of the TMS object that calls it.
     *                     It includes both Primitive and Composite Tasks.
     *
     * @param name contains the name of the task needed to change
     */
    public void setName(String name) {
        this.name = name;
    }

    /** Common function - For all types of tasks
     * Defined Function: returns the description of the TMS object that calls it.
     *                     It includes both Primitive and Composite Tasks.
     *
     * @return String representation of task description
     */
    public String getDescription() {
        return description;
    }

    /** Common function - For all types of tasks
     * Defined Function: returns the description of the TMS object that calls it.
     *                     It includes both Primitive and Composite Tasks.
     *
     * @param description contains the description of the task needed to change
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /** Common function - For all types of tasks
     * Defined Function: returns the time taken to complete the TMS object that calls it.
     *                     It includes both Primitive and Composite Tasks.
     *
     * @return Double representation of task duration
     */
    public double getDuration() {
        return duration;
    }

    /** Common function - For all types of tasks
     * Defined Function: returns the duration of the TMS object that calls it.
     *                     It includes both Primitive and Composite Tasks.
     *
     * @param duration contains the description of the task needed to change
     */
    public void setDuration(double duration) {
        this.duration = duration;
    }

    /** Common function - For all types of tasks
     * Defined Function: returns the name of all the pre-requisites required to complete the
     *                      task that calls it.
     *                      It includes both Primitive and Composite Tasks.
     *
     * @return String list containing the names of all the pre-requisites
     */
    public List<String> getPrerequisites() {
        return prerequisites;
    }

    /** Common function - For all types of tasks
     * Defined Function: returns the duration of the TMS object that calls it.
     *                     It includes both Primitive and Composite Tasks.
     *
     * @param prerequisites contains the string list of the names of all the pre-requisites needed to change
     */
    public void setPrerequisites(List<String> prerequisites) {
        this.prerequisites = prerequisites;
    }

    /** Common function - For all types of tasks
     * Defined Function: returns true if the current task is Primitive
     *                     It includes both Primitive and Composite Tasks.
     *
     * @param name contains a string representation of task name
     * @param taskMap contains a map that stores user's tasks
     * @return boolean if the task is a primitive task
     */
    public boolean isPrimitive (String name, Map<String, TMS> taskMap){
        //String [] tokens = instruction.split (" ");
        if (taskMap.containsKey(name)){
            try{
                if(taskMap.get(name).getPrerequisites() == null) return true;
            }
            catch (Exception e) {e.printStackTrace();}
        }
        return false;
    }

    /** Common function - For all types of tasks
     * Defined Function: returns true if the name arguments satisfying the naming
     *                      conditions
     *                      It includes both Primitive and Composite Tasks.
     *
     * @param name contains the name of the task needed to check
     * @return boolean True if and only if the task name follows the naming requirements
     *  mentioned in the question.
     */
    public boolean isName(String name){
        if(name.length() > 8 || (name.charAt(0) >= '0' && name.charAt(0) <= '9') || !name.matches("[a-zA-Z0-9]+")){
            System.out.println("Incorrect input. Try again!");
            return false;
        }
        return true;
    }

    /** Common function - For all types of tasks
     * Defined Function: returns true if and only if the given String is a description style entry
     *                      It checks whether the format of the description satisfies the naming
     *                      criteria
     *                      It includes both Primitive and Composite Tasks.
     *
     * @param description contains the description of the task needed to check
     * @return boolean True if and only if the description follows the requirements
     *  mentioned in the question.
     */
    public boolean isDescription(String description) {
        if(!description.matches("^[a-zA-Z0-9-]+$")){
            System.out.println("Incorrect input. Try again!");
            return false;
        }
        return true;
    }

    /** ABSTRACT function - For all types of tasks
     * Defined Function: Creates a task object of either primitive or composite
     *
     * @param instruction contains string representation of entire user input
     * @param taskMap contains map that stores the user's tasks, mapped by their names. The task to be modified should be present in this map.
     */
    public abstract void create(String instruction, Map<String,TMS>taskMap);

    /** ABSTRACT function - For all types of tasks
     * Defined Function: Deletes a particular task from the system
     *
     * @param instruction contains string representation of entire user input
     * @param taskMap contains map that stores the user's tasks, mapped by their names. The task to be modified should be present in this map.
     * @return String value that contains the name of the deleted task
     */
    public abstract String delete (String instruction,Map<String,TMS>taskMap);

    /** ABSTRACT function - For all types of tasks
     * Defined Function: Changes the features of an already defined task
     *                      Its implementation varies based on the instance
     *                      of the defined class
     *
     * @param instruction contains string representation of entire user input
     * @param taskMap contains map that stores the user's tasks, mapped by their names. The task to be modified should be present in this map.
     */
    public abstract void changeTask (String instruction, Map<String,TMS>taskMap);

    /** ABSTRACT function - For all types of tasks
     * Defined Function: Prints all the tasks defined in the system
     *                      As it must work for both Primitive and Composite
     *                      tasks, its implementation differs slightly from
     *                      each other
     * @param instruction contains string representation of entire user input
     * @param taskMap contains map that stores the user's tasks, mapped by their names. The task to be modified should be present in this map.
     */
    public abstract void printTask (String instruction, Map<String,TMS>taskMap);

    /** ABSTRACT function - For all types of tasks
     * Defined Function: States the time taken to complete a particular task
     *
     * @param instruction contains string representation of entire user input
     * @param taskMap contains map that stores the user's tasks, mapped by their names. The task to be modified should be present in this map.
     * @return double value that consists of the time to complete a particular task x.
     */
    public abstract double reportDuration (String instruction, Map<String,TMS>taskMap);

}
