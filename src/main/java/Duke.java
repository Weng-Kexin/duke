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
            String word = "done";
            boolean isDone = input.indexOf("done") == 0; //returns 0 if done is the first word

            if (input.equals("bye")) { //cant use "==" to compare strings **find out why
                br = true;
                System.out.println(horzline + "\n" + "Bye. Hope to see you again soon!\n" + horzline);
            } else if (input.equals("list")) { //list the array
                System.out.println(horzline + "\n" + "Here are the tasks in your list:");
                for (int j = 1; j < i; j++) {
                    System.out.println(j + "." + myArray[j]);
                }
                System.out.println(horzline);
            } else { //add to array or mark as done
                if (isDone) {
                    String[] splitted = input.split(" ");
                    String temp = splitted[1];
                    int taskNum = Integer.parseInt(temp);
                    myArray[taskNum].markAsDone();
                    System.out.println(horzline + "\n" + "Nice! I've marked this task as done:");
                    System.out.println(myArray[taskNum] + "\n" + horzline);
                } else {
                    Task t = new Task(input);
                    myArray[i] = t;
                    i++;
                    System.out.println(horzline + "\n" + "added: " + input + "\n" + horzline);
                }
            }
        }
    }
}