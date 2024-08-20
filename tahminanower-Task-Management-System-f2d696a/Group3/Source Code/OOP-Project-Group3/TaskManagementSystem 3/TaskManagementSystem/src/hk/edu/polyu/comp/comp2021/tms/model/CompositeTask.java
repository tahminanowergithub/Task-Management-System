package hk.edu.polyu.comp.comp2021.tms.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * Class used to create Composite Tasks. It inherits from the abstract class TMS
 * The class also implements the Serializable interface which is used
 * for saving and loading its contents to a file.
 */
public class CompositeTask extends TMS implements Serializable {
    /**
     * Default constructor for the CompositeTask class.
     * This no-argument constructor calls the parent class's no-argument constructor.
     */
    public CompositeTask (){
        super ();
    }

    /** Constructors for the composite task
     * @param name contains the name of the Primitive Task
     * @param description contains the description of the task
     * @param subtasks contains the time needed to complete the task*/
    public CompositeTask(String name, String description, String[] subtasks){
        super(name, description, subtasks);
    }

    /**Method to create a Composite Task
     * This method expects an instruction string and a Map that stores tasks
     * the instruction is split to obtain the required information for task creation
     *
     * @param instruction contains string representation of entire user input
     * @param taskMap contains a map that stores the user information*/
    @Override
    public void create(String instruction, Map<String,TMS>taskMap) {
        // add code
        String[] tokens = instruction.split(" ");
        if (tokens.length >= 4 && isName(tokens[1])) {
            String name = tokens[1];
            String description = tokens[2];
            String[] subtaskNames = tokens[3].split(",");
            if (!taskMap.containsKey(name)) {
                TMS TMS = new CompositeTask (name, description, subtaskNames); // Duration is 0 for composite tasks
                for (String subtaskName : subtaskNames) {
                    if (!taskMap.containsKey(subtaskName)) {
                        System.out.println("Failed to Create Composite Task.\nSubtask not found: " + subtaskName);
                        return;
                    }
                }
                taskMap.put(name, TMS);
                System.out.println("Composite task created: " + name);
            } else {
                System.out.println("Task with the same name already exists: " + name);
            }
        } else {
            System.out.println("Invalid CreateCompositeTask command format.");
        }
    }
    /**Method to delete a Composite Task
     * This method expects an instruction string and a Map that stores tasks
     *
     * @param instruction contains string representation of entire user input
     * @param taskMap contains a map that stores the user information
     * @return String representation of deleted task name */
    @Override
    public String delete (String instruction, Map<String,TMS>taskMap) {
        String[] tokens = instruction.split(" ");
        String taskName = tokens[1];

        if (taskMap.containsKey(taskName)) {
            TMS task = taskMap.get(taskName);
            if (!(task instanceof CompositeTask)) {
                return "Task is not a CompositeTask: " + taskName;
            }

            CompositeTask compositeTask = (CompositeTask) task;
            List<String> subTasks = compositeTask.getPrerequisites();

            // Check if any subtask is a prerequisite for other tasks
            for (String subtask : subTasks) {
                for (TMS otherTask : taskMap.values()) {
                    if (otherTask.getPrerequisites() != null && otherTask.getPrerequisites().contains(subtask) && !otherTask.getName().equals(taskName)) {
                        return "Cannot delete: Subtask " + subtask + " is a prerequisite for another task";
                    }
                }
            }

            // Delete the composite task and its subtasks
            for (String subtask : subTasks) {
                taskMap.remove(subtask);
            }
            taskMap.remove(taskName);
            return "Composite task and its sub-tasks deleted: " + taskName;
        } else {
            return "Task not found: " + taskName;
        }
    }

    /**
     * Modifies a CompositeTask based on the provided instruction.
     * This method expects an instruction string and a Map that stores existing tasks. Its main
     * function is to modify an existing task.
     *
     * @param instruction contains string representation of entire user input that dictates how the task should be modified.
     * @param taskMap contains a map that stores the user's tasks, mapped by their names. The task to be modified should be present in this map.
     */
    @Override
    public void changeTask (String instruction, Map<String,TMS>taskMap) {
        String[] tokens = instruction.split(" ");
        String name = tokens[1];
        String property = tokens[2];
        String newValue = tokens[3];

        if (taskMap.containsKey(name)) {
            CompositeTask task = (CompositeTask) taskMap.get(name);
            switch (property) {
                case "name":
                    task.setName(newValue);
                    break;
                case "description":
                    task.setDescription(newValue);
                    break;
                case "subtasks":
                    task.setPrerequisites(Arrays.asList(newValue.split(",")));
                    break;
                default:
                    System.out.println("Invalid property for a composite task");
                    return;
            }
            System.out.println("Task updated: " + name);
        } else {
            System.out.println("Task not found: " + name);
        }
    }

    /**Method to print a Composite task
     * This method expects an instruction string and a Map that stores tasks
     * It will format and print all the tasks currently found in the system
     *
     * @param instruction contains string representation of entire user input containing the required task name
     * @param taskMap contains map that stores the user information*/
    @Override
    public void printTask(String instruction, Map<String, TMS> taskMap) {
        String[] tokens = instruction.split(" ");
        if (tokens.length >= 2) {
            String taskName = tokens[1];
            TMS task = taskMap.get(taskName);
            if (taskMap.containsKey(taskName)) {
                if (task instanceof CompositeTask) {
                    CompositeTask compositeTask = (CompositeTask) task;
                    System.out.println("Task Name: " + taskName);
                    System.out.println("Description: " + compositeTask.getDescription());
                    System.out.println("Subtasks: " + String.join(", ", compositeTask.getPrerequisites()));
                } else {
                    System.out.println("Task Name: " + taskName);
                    System.out.println("Description: " + task.getDescription());
                }
            } else {
                System.out.println("Task not found: " + taskName);
            }
        }
    }

    /**Method to report the duration of a Composite task
     * This method expects an instruction string and a Map that stores tasks
     * It will calculate the time required to finish a task
     *
     * @param taskName contains string representation of entire user input
     * @param taskMap contains a map that stores the user information
     * @return Double representation of duration that reports the duration of the task*/
    @Override
    public double reportDuration(String taskName, Map<String, TMS> taskMap) {
        if (taskMap.containsKey(taskName)) {
            TMS task = taskMap.get(taskName);
            if (isPrimitive(taskName, taskMap)) {
                return ((PrimitiveTask) task).getDuration();
            } else if (!isPrimitive(taskName, taskMap)) {
                double duration = 0;
                for (String subtaskName : task.getPrerequisites()) {
                    TMS subtask = taskMap.get(subtaskName);
                    duration += reportDuration(subtaskName, taskMap);
                }
                duration += task.getDuration();
                return duration;
            }
        }
        return 0;
    }

    /**Defined Function: Method to calculate and report the earliest finish time for a given task
     *                   in a map of tasks.
     * Common function - for Primitive and Composite tasks.
     *
     * @param taskName contains string representation of task name
     * @param taskMap contains a map that stores the user information
     * @return Double representation of earliest finish time for given tasks*/
    public double reportEarliestFinishTime(String taskName, Map<String, TMS> taskMap) {
        double earliestFinishTime = 0.0;
        TMS task = taskMap.get(taskName);

        if (!isPrimitive(taskName, taskMap)) {
            CompositeTask compositeTask = (CompositeTask) task;
            for (String subtaskName : compositeTask.getPrerequisites()) {
                double subtaskFinishTime = compositeTask.reportEarliestFinishTime(subtaskName, taskMap);
                earliestFinishTime = Math.max(earliestFinishTime, subtaskFinishTime);
            }
        } else if (isPrimitive(taskName, taskMap)) {
            earliestFinishTime = task.getDuration();
        }

        earliestFinishTime += task.getDuration();
        return earliestFinishTime;
    }}