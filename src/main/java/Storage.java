import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class to store the task array on the hard drive
 */
public class Storage {

    /**
     * Method reads data from file and stores it in an ArrayList with the correct format
     * @param filePath
     * @param myArray
     * @throws IOException
     */
    void readFromFile(String filePath, ArrayList<Task> myArray) throws IOException {
        File f = new File(filePath);
        if (!f.exists()) {
            f.createNewFile();
        }
        Scanner sf = new Scanner(f); //reads content in f
        String temp = "";
        int j = 0;
        while (sf.hasNextLine()) { //keep reading new line of duke.txt and add to Task
            temp = sf.nextLine();
            String[] splitted = temp.split(" ");
            if (splitted[0].equals("todo")) {
                String tempStr = "";
                for (int i = 2; i < splitted.length; i++) { //start from 2 because udw the task type and bool value
                    tempStr += splitted[i];
                    tempStr += " ";
                }
                myArray.add(new ToDo(tempStr.trim()));
            } else if (splitted[0].equals("deadline")) {
                String tempStr = "", tempBy = "";
                boolean isBy = false;
                for (int i = 2; i < splitted.length; i++) {
                    if (splitted[i].equals("/by")) isBy = true;
                    if (isBy) {
                        if (!splitted[i].equals("/by")) { //to remove /by
                            tempBy += splitted[i];
                            tempBy += " ";
                        }
                    } else {
                        tempStr += splitted[i];
                        tempStr += " ";
                    }
                }
                myArray.add(new Deadline(tempStr.trim(), tempBy.trim()));
            } else {
                String tempStr = "", tempAt = "";
                boolean isAt = false;
                for (int k = 2; k < splitted.length; k++) {
                    if (splitted[k].equals("/at")) isAt = true;
                    if (isAt) {
                        if (!splitted[k].equals("/at")) { //need to do this to get rid of "/at"
                            tempAt += splitted[k];
                            tempAt += " ";
                        }
                    } else {
                        tempStr += splitted[k];
                        tempStr += " ";
                    }
                }
                myArray.add(new Event(tempStr.trim(), tempAt.trim()));
            }
            //mark as done
            if (splitted[1].equals("1")) {
                myArray.get(j).markAsDone();
            }
            j++;
        }
    }

    /**
     * Method saves array to a file located in filepath with the format given in textFormat()
     * @param myArray
     * @param filePath
     * @throws IOException
     */
    void saveToFile(ArrayList<Task> myArray, String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task t: myArray) {
            fw.write(t.textFormat());
            fw.write("\n");
        }
        fw.close();
    }
}
