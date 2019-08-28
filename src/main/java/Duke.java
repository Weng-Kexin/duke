import java.util.Scanner; //import scanner to read in input

public class Duke {
    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String horzline = "____________________________________________________________";
        System.out.println(horzline + "\n" + "Hello! I'm Duke \n" + "What can I do for you? \n" + horzline);

        Task[] myArray = new Task[100];
        int i = 1;
        boolean br = false;
        while (!br) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if (input.equals("bye")) { //cant use "==" to compare strings **find out why
                br = true;
                System.out.println(horzline + "\n" + "Bye. Hope to see you again soon!\n" + horzline);
            } else if (input.equals("list")) { //list the array
                System.out.println(horzline + "\n" + "Here are the tasks in your list:");
                for (int j = 1; j < i; j++) {
                    System.out.println(j + "." + myArray[j]);
                }
                System.out.println(horzline);
            } else {
                //add to array or mark as done
                String[] splitted = input.split(" ");
                if (splitted[0].equals("done")) {
                    String temp = splitted[1];
                    int taskNum = Integer.parseInt(temp);
                    myArray[taskNum].markAsDone();
                    System.out.println(horzline + "\n" + "Nice! I've marked this task as done:");
                    System.out.println(myArray[taskNum] + "\n" + horzline);
                } else if (splitted[0].equals("todo")) {
                    String temp = "";
                    for (int k = 1; k < splitted.length; k++) {
                        temp += splitted[k];
                        temp += " ";
                    }
                    myArray[i] = new ToDo(temp);
                    System.out.println(horzline + "\n" + "Got it. I've added this task:\n" + " " + myArray[i]);
                    System.out.println("Now you have " + i + " tasks in the list.\n" + horzline);
                    i++;
                } else if (splitted[0].equals("deadline")) {
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
                    tempBy = tempBy.substring(0, tempBy.length() - 1); //to remove the last space
                    myArray[i] = new Deadline(tempTask, tempBy);
                    System.out.println(horzline + "\n" + "Got it. I've added this task:\n" + " " + myArray[i]);
                    System.out.println("Now you have " + i + " tasks in the list.\n" + horzline);
                    i++;
                } else if (splitted[0].equals("event")) {
                    String tempTask = "", tempAt = "";
                    boolean isAt = false;
                    for (int k = 1; k < splitted.length; k++) {
                        if (splitted[k].equals("/by")) isAt = true;
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
                    tempAt = tempAt.substring(0, tempAt.length() - 1); //to remove the last space
                    myArray[i] = new Event(tempTask, tempAt);
                    System.out.println(horzline + "\n" + "Got it. I've added this task:\n" + " " + myArray[i]);
                    System.out.println("Now you have " + i + " tasks in the list.\n" + horzline);
                    i++;
                }
            }
        }
    }
}