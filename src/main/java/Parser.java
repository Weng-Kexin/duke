import java.util.ArrayList;
import java.util.Scanner;

/*
 * deals with making sense of the user command
 */
public class Parser {

    Ui ui = new Ui();
    TaskList t = new TaskList();

    /*
     * done, delete, todo, deadline, event, find functions
     */
    void done(ArrayList<Task> myArray, String[] splitted) {
        try {
            String temp = splitted[1];
            int taskNum = Integer.parseInt(temp) - 1;
            myArray.get(taskNum).markAsDone(); //use get to modify/access the element
            System.out.println(ui.getLine() + "\n" + "Nice! I've marked this task as done:");
            System.out.println(myArray.get(taskNum) + "\n" + ui.getLine());
        } catch (Exception e) {
            if (splitted.length == 1) {
                ui.emptyMsg(splitted[0]);
            } else {
                ui.outOfRange();
            }
        }
    }

    void delete(ArrayList<Task> myArray, String[] splitted, int i) {
        try {
            int taskNum = Integer.parseInt(splitted[1]) - 1;
            if ((taskNum + 1) > myArray.size() || taskNum < 0) {
                ui.outOfRange();
            } else {
                i--;
                System.out.println(ui.getLine() + "\n" + "Noted. I've removed this task:");
                System.out.println(" " + myArray.get(taskNum));
                myArray.remove(taskNum);
                ui.numOfTasksMsg(myArray.size());
            }
        } catch (Exception e) {
            if (splitted.length == 1) {
                ui.emptyMsg(splitted[0]);
            } else {
                ui.outOfRange();
            }
        }

    }

    void todo(ArrayList<Task> myArray, String[] splitted, int i) {
        try {
            String temp = "";
            for (int k = 1; k < splitted.length; k++) {
                temp += splitted[k];
                temp += " ";
            }
            if (splitted.length == 1) { //the exception handling for todo
                ui.emptyMsg(splitted[0]);
            } else {
                myArray.add(new ToDo(temp));
                ui.addedTask(myArray.get(i));
                ui.numOfTasksMsg(myArray.size());
                i++;
            }
        } catch (Exception e) {
            ui.errorMsg();
        }
    }

    void deadline(ArrayList<Task> myArray, String[] splitted, int i) {
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
                ui.emptyMsg(splitted[0]);
            } else {
                tempBy = c.convert(tempBy);
                myArray.add(new Deadline(tempTask.trim(), tempBy.trim()));
                ui.addedTask(myArray.get(i));
                ui.numOfTasksMsg(myArray.size());
                i++;
            }
        } catch (Exception e) {
            ui.errorMsg();
        }
    }

    void event(ArrayList<Task> myArray, String[] splitted, int i) {
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
                ui.emptyMsg(splitted[0]);
            } else {
                tempAt = c.convert(tempAt);
                myArray.add(new Event(tempTask.trim(), tempAt.trim()));
                ui.addedTask(myArray.get(i));
                ui.numOfTasksMsg(myArray.size());
                i++;
            }
        } catch (Exception e) {
            ui.errorMsg();
        }
    }

    void find(ArrayList<Task> myArray, String[] splitted) {
        try {
            if (splitted.length == 1) {
                ui.emptyMsg((splitted[0]));
            } else {
                t.findInList(myArray, splitted);
            }
        } catch (Exception e) {
            ui.errorMsg();
        }
    }

    /*
     * The main method being used
     */
    void parse(ArrayList<Task> myArray) {
        int i = myArray.size();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equals("bye")) { //cant use "==" to compare strings **it only compares the memory address
                ui.msg("Bye. Hope to see you again soon!\n");
                break;
            } else if (input.equals("list")) { //list the array directly from Task[]
                t.list(myArray);
            } else {
                String[] splitted = input.split(" ");
                switch (splitted[0]) {
                    case "done":
                        done(myArray, splitted);
                        break;
                    case "delete":
                        delete(myArray, splitted, i);
                        break;
                    case "todo":
                        todo(myArray, splitted, i);
                        break;
                    case "deadline":
                        deadline(myArray, splitted, i);
                        break;
                    case "event":
                        event(myArray, splitted, i);
                        break;
                    case "find":
                        find(myArray, splitted);
                        break;
                    default:
                        ui.errorMsg();
                        break;
                }
            }
        }
    }
}
