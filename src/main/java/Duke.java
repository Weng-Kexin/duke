import java.util.Scanner; //import scanner to read in input

public class Duke {
    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //Level 1 echo
        String horzline = "____________________________________________________________ \n";
        System.out.println(horzline + "Hello! I'm Duke \n" + "What can I do for you? \n" + horzline);

        boolean br = false;
        while (!br) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equals("bye")) { //cant use "==" to compare strings **find out why
                br = true;
                System.out.println(horzline + "Bye. Hope to see you again soon!\n" + horzline);
            } else {
                System.out.println(horzline + input + "\n" + horzline);
            }
        }
    }
}