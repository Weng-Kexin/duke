public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override //this override will override the Task.java override
    public String toString() {
        if (this.isDone) {
            return ("[D][\u2713] " + this.description + " (by: " + this.by + ")");
        } else {
            return ("[D][\u2718] " + this.description + " (by: " + this.by + ")");
        }
    }

    @Override //this override will override the Task.java override
    public String textFormat() { //format is "Deadline.isDone .description"
        return ("deadline " + super.textFormat() + " /by " + this.by);
    }
}
