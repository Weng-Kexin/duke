import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

    private String line = "____________________________________________________________";

    private void msg(String message) {
        System.out.println(line + "\n" + message + line);
    }

    private void emptyMsg(String whichTask) {
        msg("☹ OOPS!!! The description of a " + whichTask + " cannot be empty.\n");
    }

    private void numOfTasksMsg(int i) {
        String t = (i == 1 ? " task" : " tasks");
        System.out.println("Now you have " + i + t + " in the list.\n" + line);
    }

    void parse(ArrayList<Task> myArray) {
        msg("Hello! I'm Duke \n" + "What can I do for you? \n");
        int i = myArray.size();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equals("bye")) { //cant use "==" to compare strings **it only compares the memory address
                msg("Bye. Hope to see you again soon!\n");
                break;
            } else if (input.equals("list")) { //list the array directly from Task[]
                if (myArray.size() == 0) {
                    msg("There are no tasks in your list.\n");
                } else {
                    System.out.println(line + "\n" + "Here are the tasks in your list:");
                    for (int j = 0; j < myArray.size(); j++) {
                        System.out.println((j + 1) + "." + myArray.get(j));
                    }
                    System.out.println(line);
                }
            } else {
                DateAndTimeConverter c = new DateAndTimeConverter();
                String[] splitted = input.split(" ");
                switch (splitted[0]) {
                    case "done":
                        try {
                            String temp = splitted[1];
                            int taskNum = Integer.parseInt(temp) - 1;
                            myArray.get(taskNum).markAsDone(); //use get to modify/access the element
                            System.out.println(line + "\n" + "Nice! I've marked this task as done:");
                            System.out.println(myArray.get(taskNum) + "\n" + line);
                        } catch (Exception e) {
                            if (splitted.length == 1) {
                                emptyMsg(splitted[0]);
                            } else {
                                msg("☹ OOPS!!! The number is out of range.\n");
                            }
                        }
                        break;
                    case "delete":
                        try {
                            int taskNum = Integer.parseInt(splitted[1]) - 1;
                            if ((taskNum + 1) > myArray.size() || taskNum < 0) {
                                msg("☹ OOPS!!! The number is out of range.\n");
                            } else {
                                i--;
                                System.out.println(line + "\n" + "Noted. I've removed this task:");
                                System.out.println(" " + myArray.get(taskNum));
                                myArray.remove(taskNum);
                                numOfTasksMsg(myArray.size());
                            }
                        } catch (Exception e) {
                            if (splitted.length == 1) {
                                emptyMsg(splitted[0]);
                            } else {
                                System.out.println(line + "\n" + "☹ OOPS!!! The number is out of range.\n" + line);
                            }
                        }
                        break;
                    case "todo":
                        try {
                            String temp = "";
                            for (int k = 1; k < splitted.length; k++) {
                                temp += splitted[k];
                                temp += " ";
                            }
                            if (splitted.length == 1) { //the exception handling for todo
                                emptyMsg(splitted[0]);
                            } else {
                                myArray.add(new ToDo(temp));
                                System.out.println(line + "\n" + "Got it. I've added this task:\n" + " " + myArray.get(i));
                                numOfTasksMsg(myArray.size());
                                i++;
                            }
                        } catch (Exception e) {
                            System.out.println(line + "\n" + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" + line);
                        }
                        break;
                    case "deadline":
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
                                System.out.println(line + "\n" + "☹ OOPS!!! The description of a deadline cannot be empty.\n" + line);
                            } else {
                                tempBy = c.convert(tempBy);
                                myArray.add(new Deadline(tempTask.trim(), tempBy.trim()));
                                System.out.println(line + "\n" + "Got it. I've added this task:\n" + " " + myArray.get(i));
                                numOfTasksMsg(myArray.size());
                                i++;
                            }
                        } catch (Exception e) {
                            System.out.println(line + "\n" + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" + line);
                        }
                        break;
                    case "event":
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
                                System.out.println(line + "\n" + "☹ OOPS!!! The description of an event cannot be empty.\n" + line);
                            } else {
                                tempAt = c.convert(tempAt);
                                myArray.add(new Event(tempTask.trim(), tempAt.trim()));
                                System.out.println(line + "\n" + "Got it. I've added this task:\n" + " " + myArray.get(i));
                                numOfTasksMsg(myArray.size());
                                i++;
                            }
                        } catch (Exception e) {
                            System.out.println(line + "\n" + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" + line);
                        }
                        break;
                    case "find":
                        try {
                            if (splitted.length == 1) {
                                emptyMsg((splitted[0]));
                            } else {
                                String toFind = "";
                                for (int x = 1; x < splitted.length; x++) {
                                    toFind += splitted[x] + " ";
                                }
                                toFind = " " + toFind;
                                ArrayList<Task> findArray = new ArrayList<>();
                                for (Task task : myArray) {
                                    String toMatch = " " + task.getDescription() + " ";
                                    if (toMatch.contains(toFind)) {
                                        findArray.add(task);
                                    }
                                }
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
                        } catch (Exception e) {
                            msg("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
                        }
                        break;
                    default:
                        msg("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
                        break;
                }
            }
        }
    }
}
