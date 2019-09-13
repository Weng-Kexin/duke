import java.util.ArrayList;

/**
 * Class that has operations to mark as done/find items in list
 */
public class TaskList {

    /**
     * Method marks task as done
     *
     * @param myArray  ArrayList storing the tasks
     * @param splitted task to be marked as done
     */
    void markAsDone(ArrayList<Task> myArray, String[] splitted) {
        String temp = splitted[1];
        int taskNum = Integer.parseInt(temp) - 1;
        myArray.get(taskNum).markAsDone(); //use get to modify/access the element
    }

    /**
     * Method finds matching tasks in arraylist and stores it in findArray
     *
     * @param myArray   ArrayList storing the tasks
     * @param splitted  string to find the matching tasks
     * @param findArray ArrayList storing the matching tasks
     */
    void findInList(ArrayList<Task> myArray, String[] splitted, ArrayList<Task> findArray) {
        String toFind = "";
        for (int x = 1; x < splitted.length; x++) {
            toFind += splitted[x] + " ";
        }
        toFind = " " + toFind;
        for (Task task : myArray) {
            String toMatch = " " + task.getDescription() + " ";
            if (toMatch.contains(toFind)) {
                findArray.add(task);
            }
        }
    }
}
