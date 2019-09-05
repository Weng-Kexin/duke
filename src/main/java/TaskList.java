import java.util.ArrayList;

public class TaskList {

    Ui ui = new Ui();

    public void list(ArrayList<Task> myArray) {
        if (myArray.size() == 0) {
            ui.msg("There are no tasks in your list.\n");
        } else {
            System.out.println(ui.getLine() + "\n" + "Here are the tasks in your list:");
            for (int j = 0; j < myArray.size(); j++) {
                System.out.println((j + 1) + "." + myArray.get(j));
            }
            System.out.println(ui.getLine());
        }
    }

    public void findInList(ArrayList<Task> myArray, String[] splitted) {
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
        ui.list(findArray);
    }
}
