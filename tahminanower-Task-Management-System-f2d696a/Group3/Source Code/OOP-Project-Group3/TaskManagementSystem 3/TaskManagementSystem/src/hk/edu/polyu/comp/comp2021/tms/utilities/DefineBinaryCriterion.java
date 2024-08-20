package hk.edu.polyu.comp.comp2021.tms.utilities;

import hk.edu.polyu.comp.comp2021.tms.model.TMS;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

/**
 * Class used to create Binary Criteria. It inherits from the abstract class Criteria
 * The class also implements the Serializable interface which is used
 * for saving and loading its contents to a file.
 */
public class DefineBinaryCriterion extends Criterion implements Serializable {
    /**
     * Default constructor for the DefineBinaryCriterion class. <p>
     * This no-argument constructor calls the Criterion class's no-argument constructor.
     */
    public DefineBinaryCriterion(){ super(); }

    /** Constructor for the Binary Criterion
     *@param name contains the name of the basic criterion
     * @param criterion contains the first criterion
     * @param criterion2 contains the logical operator for the two criteria
     * @param op contains the second criterion*/
    public DefineBinaryCriterion(String name, Criterion criterion, String op, Criterion criterion2){ super(name, criterion, op, criterion2); }

    /**Method to create a Binary Criterion
     * This method expects an instruction string and a Map
     * that stores criteria to create Binary criteria
     *
     * @param instruction A string representing the entire user input
     * @param criterionMap stores the user's criterion information*/
    @Override
    public void create(String instruction, Map <String, Criterion> criterionMap){
        String[] tokens = instruction.split(" ");
        String name = tokens[1], name2 = tokens[2], logicOp = tokens[3], name3 = tokens[4];
        if (tokens.length != 5) {
            System.out.println("Invalid DefineBinaryCriterion command format.");
            return;
        }
        if (criterionMap.containsKey(name)){
            System.out.println("Task with the same name already exists: " + name);
            return;
        }
        if(!isName(name)){
            System.out.println("Invalid DefineBinaryCriterion name format.");
            return;
        }
        if(!logicOp.equals("&&") && !logicOp.equals("||")){
            System.out.println("Invalid DefineBinaryCriterion logical operator format.");
            return;
        }
        if (!criterionMap.containsKey(name2)) {
            System.out.println("Key " + name2 + " not found in criterionMap");
            return;
        }
        if (!criterionMap.containsKey(name3)) {
            System.out.println("Key " + name3 + " not found in criterionMap");
            return;
        }

        Criterion criterion = criterionMap.get(name2);
        Criterion criterion2 = criterionMap.get(name3);

        DefineBinaryCriterion binaryCriterion = new DefineBinaryCriterion(name, criterion, logicOp, criterion2);
        criterionMap.put(name, binaryCriterion);
        System.out.println("Binary criteria of " + name2 + " and " + name3 + " created: " + name);
    }

    /**Method to search Tasks based on Binary Criteria
     * This method expects an instruction string, a Map that stores tasks,
     * and another Map that stores criteria in order to search tasks based on the given criteria
     *
     * @param instruction A string representing the entire user input
     * @param taskMap A map that stores the user's tasks, mapped by their names.
     * The task to be modified should be present in this map.
     * @param criterionMap stores the user's criterion information */
    @Override
    public void search(String instruction, Map<String, TMS> taskMap, Map <String, Criterion> criterionMap) {
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
        Criterion criterion1 = criterion.getCriterion();
        Criterion criterion2 = criterion.getCriterion2();

        String logicOp = criterion.getOp();
        String op1 = criterion1.getOp(), op2 = criterion2.getOp();
        String property1 = criterion1.getProperty(), property2 = criterion2.getProperty();

        ArrayList<TMS> matching = new ArrayList<>();

        for (Map.Entry<String, TMS> entry : taskMap.entrySet()) {
            TMS task = entry.getValue();
            boolean criteriaMet1 = false, criteriaMet2 = false;

            if(op1.equals("contains") && containsCriterion(criterion1, task)){
                criteriaMet1 = true;
            } else if(op1.equals("notContains") && !containsCriterion(criterion1, task)){
                criteriaMet1 = true;
            } else if(property1.equals("duration") && checkDurationCriterion(criterion1, task)){
                criteriaMet1 = true;
            }

            if(op2.equals("contains") && containsCriterion(criterion2, task)){
                criteriaMet2 = true;
            } else if(op2.equals("notContains") && !containsCriterion(criterion2, task)){
                criteriaMet2 = true;
            } else if(property2.equals("duration") && checkDurationCriterion(criterion2, task)){
                criteriaMet2 = true;
            }

            if(logicOp.equals("&&") && (criteriaMet1 && criteriaMet2)){
                matching.add(task);
            } else if(logicOp.equals("||") && (criteriaMet1 || criteriaMet2)){
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

    /**Method to print a Binary criterion
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
            DefineBinaryCriterion criterion = (DefineBinaryCriterion) criterionMap.get(name);
            Criterion criterion1 = (Criterion) criterion.getCriterion();
            Criterion criterion2 = (Criterion) criterion.getCriterion2();

            System.out.println("Name: " + criterion.getName());
            System.out.println("Criteria 1 : " + criterion1.getName());
            System.out.println("Operator: " + criterion.getOp());
            System.out.println ("Criteria 2: " + criterion2.getName() + "\n");
        }
    }
}