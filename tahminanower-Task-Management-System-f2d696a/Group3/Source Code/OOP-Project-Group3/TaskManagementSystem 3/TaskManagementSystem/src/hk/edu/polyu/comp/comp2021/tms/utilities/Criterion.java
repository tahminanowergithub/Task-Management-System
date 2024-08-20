package hk.edu.polyu.comp.comp2021.tms.utilities;

import hk.edu.polyu.comp.comp2021.tms.model.TMS;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Abstract Class used to create Basic, Negated and Binary Criteria.
 * The class also implements the Serializable interface which is used
 * for saving and loading its contents to a file.
 */
public abstract class Criterion implements Serializable {
    private String name;
    private String property;
    private String op;
    private double val;
    private String valStr;
    private List<String> valList;
    private Criterion criterion;
    private Criterion criterion2;

    /**
     * Default constructor for the Criterion class. <p>
     * This no-argument constructor that's used for the initialization of its children.
     */
    public Criterion(){

    }

    /** Constructor for Criterion initializing Basic and Negated Criteria
     * @param name contains the name of the criterion
     * @param property contains the property of the criterion
     * @param op contains the operator of the criterion
     * @param val contains the double value needed to the criterion*/
    public Criterion(String name, String property, String op, double val){
        this.name = name;
        this.property = property;
        this.op = op;
        this.val = val;
    }

    /** Constructor for Criterion initializing Basic and Negated Criteria
     * @param name contains the name of the criterion
     * @param property contains the property of the criterion
     * @param op contains the operator of the criterion
     * @param val contains the string value needed to the criterion*/
    public Criterion(String name, String property, String op, String val){
        this.name = name;
        this.property = property;
        this.op = op;
        this.valStr = val;
    }

    /** Constructor for Criterion initializing Basic and Negated Criteria
     * @param name contains the name of the criterion
     * @param property contains the property of the criterion
     * @param op contains the operator of the criterion
     * @param val contains the list of string value needed to the criterion*/
    public Criterion(String name, String property, String op, String[] val){
        this.name = name;
        this.property = property;
        this.op = op;
        this.valList = Arrays.asList(val);
    }

    /** Constructor for Criterion initializing Binary Criteria
     * @param name contains the name of the criterion
     * @param criterion contains first criterion
     * @param op contains the logical operator for the criteria
     * @param criterion2 contains second criterion */
    public Criterion(String name, Criterion criterion, String op, Criterion criterion2){
        this.name = name;
        this.criterion = criterion;
        this.op = op;
        this.criterion2 = criterion2;
    }

    /** Common function - For all types of criteria
     * Defined Function: returns the name of the Criteria object that calls it.
     *                     It includes Basic, Negated and Binary Criteria.
     *
     * @return String representation of the criterion name
     */
    public String getName() { return name; }

    /** Common function - For all types of criteria
     * Defined Function: sets the name of the Criteria object that calls it.
     *                     It includes Basic, Negated and Binary Criteria.
     *
     * @param name contains the name of the criteria needed to change
     */
    public void setName(String name) { this.name = name; }

    /** Common function - For all types of criteria
     * Defined Function: returns the property of the Criteria object that calls it.
     *                     It includes Basic, Negated and Binary Criteria.
     *
     * @return String representation of the criterion property
     */
    public String getProperty() { return property; }

    /** Common function - For all types of criteria
     * Defined Function: sets the property of the Criteria object that calls it.
     *                     It includes Basic, Negated and Binary Criteria.
     *
     * @param property contains the property of the criteria needed to change
     */
    public void setProperty(String property) { this.property = property; }

    /** Common function - For all types of criteria
     * Defined Function: returns the operator of the Criteria object that calls it.
     *                     It includes Basic, Negated and Binary Criteria.
     *
     * @return String representation of the criterion operator
     */
    public String getOp() { return op; }

    /** Common function - For all types of criteria
     * Defined Function: sets the op of the Criteria object that calls it.
     *                     It includes Basic, Negated and Binary Criteria.
     *
     * @param op contains the op of the criteria needed to change
     */
    public void setOp(String op) { this.op = op; }

    /** Common function - For all types of criteria
     * Defined Function: returns the double value of the Criteria object that calls it.
     *                     It includes Basic, Negated and Binary Criteria.
     *
     * @return Double representation of the criterion value
     */
    public double getVal() { return val; }

    /** Common function - For all types of criteria
     * Defined Function: sets the value of the Criteria object that calls it.
     *                     It includes Basic, Negated and Binary Criteria.
     *
     * @param val contains the value of the criteria needed to change
     */
    public void setVal(double val) { this.val = val; }

    /** Common function - For all types of criteria
     * Defined Function: returns the string value of the Criteria object that calls it.
     *                     It includes Basic, Negated and Binary Criteria.
     *
     * @return String representation of the criterion value
     */
    public String getValStr() { return valStr; }

    /** Common function - For all types of criteria
     * Defined Function: sets the value of the Criteria object that calls it.
     *                     It includes Basic, Negated and Binary Criteria.
     *
     * @param valStr contains the value of the criteria needed to change
     */
    public void setValStr(String valStr) { this.valStr = valStr; }

    /** Common function - For all types of criteria
     * Defined Function: returns the list of string value of the Criteria object that calls it.
     *                     It includes Basic, Negated and Binary Criteria.
     *
     * @return List string representation of the criterion value
     */
    public List<String> getValList() { return valList; }

    /** Common function - For all types of criteria
     * Defined Function: sets the list of string value of the Criteria object that calls it.
     *                     It includes Basic, Negated and Binary Criteria.
     *
     * @param valList contains the value of the criteria needed to change
     */
    public void setValList(List<String> valList) { this.valList = valList; }

    /** Common function - For Binary Criteria
     * Defined Function: returns the first criteria object that calls it.
     *                     It includes Binary Criteria.
     *
     * @return Object representation of the first criteria
     */
    public Criterion getCriterion() { return criterion; }

    /** Common function - For Binary Criteria
     * Defined Function: sets the value of the Criteria object that calls it.
     *                     It includes Binary Criteria.
     *
     * @param criterion contains the criteria object needed to change
     */
    public void setCriterion(Criterion criterion) { this.criterion = criterion; }

    /** Common function - For Binary Criteria
     * Defined Function: returns the second criteria object that calls it.
     *                     It includes Binary Criteria.
     *
     * @return Object representation of the second criteria
     */
    public Criterion getCriterion2() { return criterion2; }

    /** Common function - For Binary Criteria
     * Defined Function: sets the second criteria object that calls it.
     *                     It includes Binary Criteria.
     *
     * @param criterion2 contains the criteria object needed to change
     */
    public void setCriterion2(Criterion criterion2) { this.criterion2 = criterion2; }

    /** Common function - For all types of criteria
     * Defined Function: returns true if the name arguments satisfying the naming
     *                      conditions
     *                      It includes Basic, Negated and Binary Criteria.
     *
     * @param name contains the name of the criteria needed to check
     * @return boolean True if and only if the name follows the naming requirements
     *  mentioned in the question.
     */
    public static boolean isName(String name){
        if(name.length() > 8 || (name.charAt(0) >= '0' && name.charAt(0) <= '9') || !name.matches("[a-zA-Z0-9]+")){
            System.out.println("Incorrect input. Try again!");
            return false;
        }
        return true;
    }

    /** Common function - For all types of criteria
     * Defined Function: returns true if and only if the given String is a description style entry
     *                      It checks whether the format of the description satisfies the naming
     *                      criteria
     *                     It includes Basic, Negated and Binary Criteria.
     *
     * @param description contains the description of the criteria needed to check
     * @return boolean True if and only if the description follows the requirements
     *  mentioned in the question.
     */
    public static boolean isDescription(String description) {
        if(!description.matches("^[a-zA-Z0-9-]+$")){
            System.out.println("Incorrect input. Try again!");
            return false;
        }
        return true;
    }

    /** Common function - Used only for search functions in the DefineBasicCriterion,
     *  DefineNegatedCriterion and DefineBinaryCriterion classes
     *  Defined Function: Determines if a given Criterion matches the properties of a Task.
     *                   The match is based on the specified property like "name," "description,"
     *                   or "prerequisites".
     *                   For "name" and "description" properties, it checks if the Criterion's
     *                   value matches the corresponding Task property.
     *                   For the "prerequisites" property, it checks if all elements in the Criterion's
     *                   value list are present in the Task's prerequisites list.
     *
     * @param criterion criterion object that is needed to be compared with the task parameter.
     * @param task task object that is needed to be compared with the criterion parameter.
     * @return boolean True if Criterion matches the Task based on the specified property. Otherwise, false.
     */
    protected boolean containsCriterion(Criterion criterion, TMS task) {
        switch (criterion.getProperty()) {
            case "name":
                return criterion.getValStr().equals(task.getName());
            case "description":
                return criterion.getValStr().equals(task.getDescription());
            case "prerequisites":
                List<String> valList = criterion.getValList();
                List<String> prerequisites = task.getPrerequisites();
                boolean allPrerequisites = true;

                if(prerequisites == null) return false;
                for (String val : valList) {
                    if (!prerequisites.contains(val)) {
                        allPrerequisites = false;
                        break;
                    }
                }
                return allPrerequisites;
            default:
                return false;
        }
    }

    /** Common function - Used only for search functions in the DefineBasicCriterion,
     *  DefineNegatedCriterion and DefineBinaryCriterion classes
     *  Defined Function: Checks if a given task's duration meets the specified criteria
     *                   based on the provided criterion object.
     *                   The method evaluates task's duration and criterion value
     *                   based on criterion operator.
     *
     * @param criterion criterion object that is needed to be compared with the task parameter.
     * @param task task object that is needed to be compared with the criterion parameter.
     * @return boolean True if Task matches the Criteria based on the specified property.
     *         Otherwise, false.
     */
    protected boolean checkDurationCriterion(Criterion criterion, TMS task) {
        switch (criterion.getOp()) {
            case ">":
                return task.getDuration() > criterion.getVal();
            case "<":
                return task.getDuration() < criterion.getVal();
            case ">=":
                return task.getDuration() >= criterion.getVal();
            case "<=":
                return task.getDuration() <= criterion.getVal();
            case "==":
                return task.getDuration() == criterion.getVal();
            case "!=":
                return task.getDuration() != criterion.getVal();
            default:
                return false;
        }
    }

    /** ABSTRACT function - For all types of criteria
     * Defined Function: Creates a criteria object of basic, negated or binary
     *                      Its implementation varies...
     *
     * @param instruction A string representing the entire user input
     * @param criterionMap stores the user's criterion information
     */
    public abstract void create(String instruction, Map <String, Criterion> criterionMap);

    /** ABSTRACT function - For all types of criteria
     * Defined Function: Creates a criteria object of basic, negated or binary
     *                      Its implementation varies...
     *
     * @param instruction A string representing the entire user input
     * @param taskMap A map that stores the user's tasks, mapped by their names. The task to be modified should be present in this map.
     * @param criterionMap stores the user's criterion information
     */
    public abstract void search(String instruction, Map<String, TMS> taskMap, Map <String, Criterion> criterionMap);

    /** ABSTRACT function - For all types of criteria
     * Defined Function: Prints a criteria object of basic, negated or binary
     *
     * @param instruction A string representing the entire user input
     * @param criterionMap stores the user's criterion information
     */
    public abstract void printCriterion(String instruction, Map <String, Criterion> criterionMap);
}
