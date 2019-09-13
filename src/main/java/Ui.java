import java.util.ArrayList;

/**
 * Class that deals with interactions with the user
 */
public class Ui {

    private String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private String line = "____________________________________________________________";

    /**
     * Gets line.
     *
     * @return line line
     */
    String getLine() {
        return line;
    }

    /** Method greets the user */
    void greeter() {
        System.out.println("Hello from\n" + logo + "Hello! I'm Duke \nWhat can I do for you?\n" + line);
    }

    /**
     * Method to print a message
     *
     * @param message the message
     */
    void msgPrinter(String message) {
        System.out.println(line + "\n" + message + line);
    }

    /**
     * Method prints an empty task message
     *
     * @param whichTask the which task
     */
    void emptyMsgPrinter(String whichTask) {
        msgPrinter("☹ OOPS!!! The description of a " + whichTask + " cannot be empty.\n");
    }

    /**
     * Method to print an error message
     */
    void errorMsgPrinter() {
        msgPrinter("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
    }

    /**
     * Method to print the number of tasks in the list
     *
     * @param i the
     */
    void numOfTasksPrinter(int i) {
        String t = (i == 1 ? " task" : " tasks");
        System.out.println("Now you have " + i + t + " in the list.\n" + line);
    }

    /**
     * Method to print a successfully added task message
     *
     * @param t the task that was added
     */
    void addedTaskPrinter(Task t) {
        System.out.println(line + "\nGot it. I've added this task:\n " + t);
    }

    /**
     * Method to print an out of range error message
     */
    void outOfRangePrinter() {
        msgPrinter("☹ OOPS!!! The number is out of range.\n");
    }

    /**
     * Method to print the list of matching tasks
     *
     * @param findArray the array of tasks to be printed
     */
    void matchingTasksPrinter(ArrayList<Task> findArray) {
        if (findArray.size() == 0) {
            msgPrinter("Sorry. There are no matching tasks in your list.\n");
        } else {
            String is = (findArray.size() == 1 ? "is" : "are");
            String t = (findArray.size() == 1 ? "task" : "tasks");
            System.out.println(line + "\nHere " + is + " the matching " + t + " in your list:");
            for (int j = 0; j < findArray.size(); j++) {
                System.out.println((j + 1) + "." + findArray.get(j));
            }
            System.out.println(line);
        }
    }

    /**
     * Method to list all tasks in myArray
     *
     * @param myArray the array of tasks to be printed
     */
    void taskLister(ArrayList<Task> myArray) {
        if (myArray.size() == 0) {
            msgPrinter("There are no tasks in your list.\n");
        } else {
            System.out.println(line + "\n" + "Here are the tasks in your list:");
            for (int j = 0; j < myArray.size(); j++) {
                System.out.println((j + 1) + "." + myArray.get(j));
            }
            System.out.println(line);
        }
    }
}
