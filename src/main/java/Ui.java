import java.util.ArrayList;

public class Ui {

    String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    String line = "____________________________________________________________";

    String getLine() {
        return line;
    }

    void greetingMsg() {
        System.out.println("Hello from\n" + logo + "Hello! I'm Duke \nWhat can I do for you?\n" + line);
    }

    void msg(String message) {
        System.out.println(line + "\n" + message + line);
    }

    void emptyMsg(String whichTask) {
        msg("☹ OOPS!!! The description of a " + whichTask + " cannot be empty.\n");
    }

    void errorMsg() {
        msg("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
    }

    void numOfTasksMsg(int i) {
        String t = (i == 1 ? " task" : " tasks");
        System.out.println("Now you have " + i + t + " in the list.\n" + line);
    }

    void addedTask(Task t) {
        System.out.println(line + "\nGot it. I've added this task:\n" + " " + t);

    }

    void outOfRange() {
        msg("☹ OOPS!!! The number is out of range.\n");
    }

    void list(ArrayList<Task> findArray) {
        if (findArray.size() == 0) {
            msg("Sorry. There are no matching tasks in your list.\n");
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
}
