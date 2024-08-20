package hk.edu.polyu.comp.comp2021.tms.utilities;

import hk.edu.polyu.comp.comp2021.tms.model.TMS;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

/**
 * Class used to create Basic Criteria. It inherits from the abstract class Criteria
 * The class also implements the Serializable interface which is used
 * for saving and loading its contents to a file.
 */
public class DefineBasicCriterion extends Criterion implements Serializable {

    /**
     * Default constructor for the DefineBasicCriterion class. <p>
     * This no-argument constructor calls the Criterion class's no-argument constructor.
     */
    public DefineBasicCriterion(){
        super();
    }

    /** Constructor for the Basic Criterion
     * @param name contains the name of the basic criterion
     * @param property contains the property of the criterion
     * @param op contains the operator of the criterion
     * @param val contains the double value needed to the criterion*/
    public DefineBasicCriterion(String name, String property, String op, double val) {
        super(name, property, op, val);
    }

    /** Constructor for the Basic Criterion
     * @param name contains the name of the basic criterion
     * @param property contains the property of the criterion
     * @param op contains the operator of the criterion
     * @param val contains the string value needed to the criterion*/
    public DefineBasicCriterion(String name, String property, String op, String val) {
        super(name, property, op, val);
    }

    /** Constructor for the Basic Criterion
     * @param name contains the name of the basic criterion
     * @param property contains the property of the criterion
     * @param op contains the operator of the criterion
     * @param val contains the list of string value needed to the criterion*/
    public DefineBasicCriterion(String name, String property, String op, String[] val) { super(name, property, op, val); }


    /**Method to create a Basic Criterion
     * This method expects an instruction string and a Map
     * that stores criteria to create Basic criteria
     *
     * @param instruction A string representing the entire user input
     * @param criterionMap stores the user's criterion information*/
    @Override
    public void create(String instruction, Map <String, Criterion> criterionMap){
        String[] tokens = instruction.split(" ");
        String name = tokens[1], property = tokens[2];
        if (tokens.length != 5) {
            System.out.println("Invalid DefineBasicCriterion command format.");
            return;
        }
        if (criterionMap.containsKey(name)){
            System.out.println("Task with the same name already exists: " + name);
            return;
        }
        if(!isName(name)){
            System.out.println("Invalid CreateSimpleTask name format.");
            return;
        }

        DefineBasicCriterion criteria;
        String op = tokens[3], val = tokens[4];
        switch (property) {
            case "name":
            case "description":
                if (!op.matches("contains")) return;
                criteria = new DefineBasicCriterion(name, property, "contains", val.substring(1,val.length()-1));
                break;
            case "duration":
                try {
                    if(!op.matches(">|<|>=|<=|==|!=")){
                        System.out.println("Invalid duration op: " + op);
                        return;
                    }
                    criteria = new DefineBasicCriterion(name, property, op, Double.parseDouble(val));
                    break;
                } catch (NumberFormatException e){
                    System.out.println("Invalid duration value: " + val);
                    return;
                }
            case "prerequisites":
                String[] valList = tokens[4].split(",");
                if (!op.matches("contains")) return;
                criteria = new DefineBasicCriterion(name, property, "contains", valList);
                break;
            default:
                System.out.println("Invalid DefineBasicCriterion property format");
                return;
        }
        criterionMap.put(name, criteria);
        System.out.println("Criteria created: " + name);
    }


    /**Method to search Tasks based on Basic Criteria
     * This method expects an instruction string, a Map that stores tasks,
     * and another Map that stores criteria in order to search tasks based on the given criteria
     *
     * @param instruction A string representing the entire user input
     * @param taskMap A map that stores the user's tasks, mapped by their names.
     * The task to be modified should be present in this map.
     * @param criterionMap stores the user's criterion information */
    @Override
    public void search(String instruction, Map<String, TMS> taskMap, Map <String, Criterion> criterionMap){
        String[] tokens = instruction.split(" ");
        if (tokens.length != 2) {
            System.out.println("Invalid search command format.");
            return;
        }

        String name = tokens[1];
        if (!criterionMap.containsKey(name)) {
            System.out.println("Criterion with the given name does not exist: " + name);
            return;
        }

        Criterion criterion = criterionMap.get(name);
        String property = criterion.getProperty();
        String op = criterion.getOp();

        ArrayList<TMS> matching = new ArrayList<>();
        for(Map.Entry<String, TMS> entry : taskMap.entrySet()) {
            TMS task = entry.getValue();
            //System.out.println(task + " is the object passed");
            if(op.equals("contains") && containsCriterion(criterion, task)){
                matching.add(task);
            } else if(op.equals("notContains") && !containsCriterion(criterion, task)){
                matching.add(task);
            } else if(property.equals("duration") && checkDurationCriterion(criterion, task)){
                matching.add(task);
            }
        }
        if(matching.isEmpty()){
            System.out.println("No tasks match the given criterion.");
            return;
        } else{
            System.out.println("Tasks matching the given criterion:");
            int cnt = 0;
            for(TMS task : matching){
                cnt++;
                System.out.println(cnt + "." + task.getName());
            }
        }
    }

    /**Method to print a Basic criterion
     * This method expects an instruction string and a Map that stores criteria
     * It will format and print all the tasks currently found in the system
     *
     * @param name A string representing criterion name
     * @param criterionMap stores the user's criterion information*/
    @Override
    public void printCriterion(String name, Map<String,Criterion>criterionMap){
        if (criterionMap.isEmpty()){
            System.out.println ("There are no criteria defined.");
            return;
        }
        if(criterionMap.containsKey(name)){
            Criterion criterion = (Criterion) criterionMap.get(name);
            System.out.println("Task Name: " + criterion.getName());
            System.out.println("Property: " + criterion.getProperty());
            System.out.println("Operator: " + criterion.getOp());
            if(criterion.getValStr() != null){
                System.out.println("Value: " + criterion.getValStr() + "\n");
            } else if(criterion.getValList() != null){
                System.out.println("Value: " + String.join(", ", criterion.getValList()) + "\n");
            } else{
                System.out.println("Value: " + criterion.getVal() + "\n");
            }
        }
    }
}
