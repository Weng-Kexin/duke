/**
 * Class inherits from task
 */
public class ToDo extends Task {

    ToDo(String description) {
        super(description);
    }

    @Override //this override will override the Task.java override
    public String toString() {
        if (this.isDone) {
            return ("[T][\u2713] " + this.description);
        } else {
            return ("[T][\u2718] " + this.description);
        }
    }

    @Override //this override will override the Task.java override
    public String textFormat() {
        return ("todo " + super.textFormat());
    }
}