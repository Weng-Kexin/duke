import java.io.IOException;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws IOException {

        greetingMsg();
        ArrayList<Task> myArray = new ArrayList<>();
        Storage storage = new Storage();
        Parser p = new Parser();

        String filePath = "C:\\Users\\Asus\\Desktop\\duke\\src\\main\\java\\data\\duke.txt";
        storage.readFromFile(filePath, myArray); //read from file
        p.parse(myArray);
        storage.saveToFile(myArray, filePath);
    }

    private static void greetingMsg() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
}