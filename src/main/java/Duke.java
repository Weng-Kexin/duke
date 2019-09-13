import java.io.IOException;
import java.util.ArrayList;

/**
 * Main class of duke, a personal assistant
 */
public class Duke {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String[] args) throws IOException {

        ArrayList<Task> myArray = new ArrayList<>();
        Storage storage = new Storage();
        Ui ui = new Ui();
        Parser p = new Parser();

        String filePath = "C:\\Users\\Asus\\Desktop\\duke\\src\\main\\java\\data\\duke.txt";
        storage.readFromFile(filePath, myArray); //read from file
        ui.greeter();
        p.parse(myArray);
        storage.saveToFile(myArray, filePath);
    }
}