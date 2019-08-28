import java.util.Scanner; //import scanner to read in input

public class Duke {
    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String horzline = "____________________________________________________________ \n";
        System.out.println(horzline + "Hello! I'm Duke \n" + "What can I do for you? \n" + horzline);

        String[] myArray = new String[100];
        int i = 1;
        boolean br = false;
        while (!br) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equals("bye")) { //cant use "==" to compare strings **find out why
                br = true;
                System.out.println(horzline + "Bye. Hope to see you again soon!\n" + horzline);
            } else if (input.equals("list")) { //list the array
                System.out.println(horzline);
                for (int j = 1; j < i; j++) {
                    System.out.println(j + ". " + myArray[j]);
                }
                System.out.println(horzline);
            } else { //add to array
                myArray[i] = input;
                i++;
                System.out.println(horzline + "added: " + input + "\n" + horzline);
            }
        }
    }
}