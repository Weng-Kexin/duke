import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class deals with making sense of the user command
 */
public class Parser {

    private Ui ui = new Ui();
    private TaskList t = new TaskList();

    /**
     * Method updates task splitted[] as done
     * @param myArray ArrayList storing the tasks
     * @param splitted task to be marked done
     */
    private void done(ArrayList<Task> myArray, String[] splitted) {
        try {
            t.markAsDone(myArray, splitted);
            System.out.print(ui.getLine() + "\n" + "Nice! I've marked this task as done:\n ");
            System.out.println(myArray.size() - 1 + "\n" + ui.getLine()); //-1 cause indexing
        } catch (Exception e) {
            if (splitted.length == 1) {
                ui.emptyMsgPrinter(splitted[0]);
            } else {
                ui.outOfRangePrinter();
            }
        }
    }

    /**
     * Method deletes task splitted[]
     * @param myArray ArrayList storing the tasks
     * @param splitted task to be deleted
     */
    private void delete(ArrayList<Task> myArray, String[] splitted) {
        try {
            int taskNum = Integer.parseInt(splitted[1]) - 1;
            if ((taskNum + 1) > myArray.size() || taskNum < 0) {
                ui.outOfRangePrinter();
            } else {
                System.out.println(ui.getLine() + "\n" + "Noted. I've removed this task:");
                System.out.println(" " + myArray.get(taskNum));
                myArray.remove(taskNum);
                ui.numOfTasksPrinter(myArray.size());
            }
        } catch (Exception e) {
            if (splitted.length == 1) {
                ui.emptyMsgPrinter(splitted[0]);
            } else {
                ui.outOfRangePrinter();
            }
        }

    }

    /**
     * Method splits todo into description
     * @param myArray ArrayList storing the tasks
     * @param splitted todo description
     */
    private void todo(ArrayList<Task> myArray, String[] splitted) {
        try {
            String temp = "";
            for (int k = 1; k < splitted.length; k++) {
                temp += splitted[k];
                temp += " ";
            }
            if (splitted.length == 1) { //the exception handling for todo
                ui.emptyMsgPrinter(splitted[0]);
            } else {
                myArray.add(new ToDo(temp));
                ui.addedTaskPrinter(myArray.get(myArray.size() - 1));
                ui.numOfTasksPrinter(myArray.size());
            }
        } catch (Exception e) {
            ui.errorMsgPrinter();
        }
    }

    /**
     * Method splits deadline into description and date and time
     * @param myArray ArrayList storing the tasks
     * @param splitted deadline to be split into description and date and time
     */
    private void deadline(ArrayList<Task> myArray, String[] splitted) {
        DateAndTimeConverter c = new DateAndTimeConverter();
        try {
            String tempTask = "", tempBy = "";
            boolean isBy = false;
            for (int k = 1; k < splitted.length; k++) {
                if (splitted[k].equals("/by")) isBy = true;
                if (isBy) {
                    if (!splitted[k].equals("/by")) { //need to do this to get rid of "/by"
                        tempBy += splitted[k];
                        tempBy += " ";
                    }
                } else {
                    tempTask += splitted[k];
                    tempTask += " ";
                }
            }
            if (!isBy || splitted.length == 1) {
                ui.emptyMsgPrinter(splitted[0]);
            } else {
                tempBy = c.timeAndDateConverter(tempBy);
                myArray.add(new Deadline(tempTask.trim(), tempBy.trim()));
                ui.addedTaskPrinter(myArray.get(myArray.size() - 1));
                ui.numOfTasksPrinter(myArray.size());

            }
        } catch (Exception e) {
            ui.errorMsgPrinter();
        }
    }

    /**
     * Method splits event into event and date and time
     * @param myArray ArrayList storing the tasks
     * @param splitted input to be split into event and date and time
     */
    private void event(ArrayList<Task> myArray, String[] splitted) {
        DateAndTimeConverter c = new DateAndTimeConverter();
        try {
            String tempTask = "", tempAt = "";
            boolean isAt = false;
            for (int k = 1; k < splitted.length; k++) {
                if (splitted[k].equals("/at")) isAt = true;
                if (isAt) {
                    if (!splitted[k].equals("/at")) { //need to do this to get rid of "/at"
                        tempAt += splitted[k];
                        tempAt += " ";
                    }
                } else {
                    tempTask += splitted[k];
                    tempTask += " ";
                }
            }
            if (!isAt || splitted.length == 1) {
                ui.emptyMsgPrinter(splitted[0]);
            } else {
                tempAt = c.timeAndDateConverter(tempAt);
                myArray.add(new Event(tempTask.trim(), tempAt.trim()));
                ui.addedTaskPrinter(myArray.get(myArray.size() - 1));
                ui.numOfTasksPrinter(myArray.size());
            }
        } catch (Exception e) {
            ui.errorMsgPrinter();
        }
    }

    /**
     * Method finds matching strings with splitted[] in myArray and stores it in printArray to be printed
     * @param myArray ArrayList storing the tasks
     * @param splitted string to find the matching tasks
     * @param printArray ArrayList storing the matching tasks
     */
    private void find(ArrayList<Task> myArray, String[] splitted, ArrayList<Task> printArray) {
        try {
            if (splitted.length == 1) {
                ui.emptyMsgPrinter((splitted[0]));
            } else {
                t.findInList(myArray, splitted, printArray);
            }
        } catch (Exception e) {
            ui.errorMsgPrinter();
        }
    }

    /**
     * Method makes sense of user input and calls the corresponding method
     *
     * @param myArray ArrayList storing the tasks
     */
    void parse(ArrayList<Task> myArray) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equals("bye")) { //cant use "==" to compare strings **it only compares the memory address
                ui.msgPrinter("Bye. Hope to see you again soon!\n");
                break;
            } else if (input.equals("list")) { //list the array directly from Task[]
                ui.taskLister(myArray);
            } else {
                String[] splitted = input.split(" ");
                switch (splitted[0]) {
                    case "done":
                        done(myArray, splitted);
                        break;
                    case "delete":
                        delete(myArray, splitted);
                        break;
                    case "todo":
                        todo(myArray, splitted);
                        break;
                    case "deadline":
                        deadline(myArray, splitted);
                        break;
                    case "event":
                        event(myArray, splitted);
                        break;
                    case "find":
                        ArrayList<Task> printArray = new ArrayList<>();
                        find(myArray, splitted, printArray);
                        ui.matchingTasksPrinter(printArray);
                        break;
                    default:
                        ui.errorMsgPrinter();
                        break;
                }
            }
        }
    }
}
