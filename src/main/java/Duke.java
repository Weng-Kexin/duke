import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner; //import scanner to read in input

public class Duke {
    public static void main(String[] args) throws IOException {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String horzline = "____________________________________________________________";
        System.out.println(horzline + "\n" + "Hello! I'm Duke \n" + "What can I do for you? \n" + horzline);

        ArrayList<Task> myArray = new ArrayList<>();
        Storage storage = new Storage();

        String filePath = "C:\\Users\\Asus\\Desktop\\duke\\src\\main\\java\\data\\duke.txt";
        storage.readFromFile(filePath, myArray); //read from file
        int i = myArray.size();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if (input.equals("bye")) { //cant use "==" to compare strings **it only compares the memory address
                System.out.println(horzline + "\n" + "Bye. Hope to see you again soon!\n" + horzline);
                break;
            } else if (input.equals("list")) { //list the array directly from Task[]
                System.out.println(horzline + "\n" + "Here are the tasks in your list:");
                for (int j = 0; j < myArray.size(); j++) {
                    System.out.println((j + 1) + "." + myArray.get(j));
                }
                System.out.println(horzline);
            } else {
                DateAndTimeConverter c = new DateAndTimeConverter();
                String[] splitted = input.split(" ");
                switch (splitted[0]) {
                    case "done":
                        try {
                            String temp = splitted[1];
                            int taskNum = Integer.parseInt(temp) - 1;
                            myArray.get(taskNum).markAsDone(); //use get to modify/access the element
                            System.out.println(horzline + "\n" + "Nice! I've marked this task as done:");
                            System.out.println(myArray.get(taskNum) + "\n" + horzline);
                        } catch (Exception e) {
                            if (splitted.length == 1) {
                                System.out.println(horzline + "\n" + "☹ OOPS!!! The description of a done cannot be empty.\n" + horzline);
                            } else {
                                System.out.println(horzline + "\n" + "☹ OOPS!!! The number is out of range.\n" + horzline);
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
                                System.out.println(horzline + "\n" + "☹ OOPS!!! The description of a todo cannot be empty.\n" + horzline);
                            } else {
                                myArray.add(new ToDo(temp));
                                System.out.println(horzline + "\n" + "Got it. I've added this task:\n" + " " + myArray.get(i));
                                System.out.println("Now you have " + myArray.size() + " tasks in the list.\n" + horzline);
                                i++;
                            }
                        } catch (Exception e) {
                            System.out.println(horzline + "\n" + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" + horzline);
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
                                System.out.println(horzline + "\n" + "☹ OOPS!!! The description of a deadline cannot be empty.\n" + horzline);
                            } else {
                                tempBy = c.convert(tempBy);
                                myArray.add(new Deadline(tempTask.trim(), tempBy.trim()));
                                System.out.println(horzline + "\n" + "Got it. I've added this task:\n" + " " + myArray.get(i));
                                System.out.println("Now you have " + myArray.size() + " tasks in the list.\n" + horzline);
                                i++;
                            }
                        } catch (Exception e) {
                            System.out.println(horzline + "\n" + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" + horzline);
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
                                System.out.println(horzline + "\n" + "☹ OOPS!!! The description of an event cannot be empty.\n" + horzline);
                            } else {
                                tempAt = c.convert(tempAt);
                                myArray.add(new Event(tempTask.trim(), tempAt.trim()));
                                System.out.println(horzline + "\n" + "Got it. I've added this task:\n" + " " + myArray.get(i));
                                System.out.println("Now you have " + myArray.size() + " tasks in the list.\n" + horzline);
                                i++;
                            }
                        } catch (Exception e) {
                            System.out.println(horzline + "\n" + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" + horzline);
                        }
                        break;
                    default:
                        System.out.println(horzline + "\n" + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" + horzline);
                        break;
                }
            }
        }
        storage.saveToFile(myArray, filePath);
    }
}