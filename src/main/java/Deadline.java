/**
 * Class that inherits from Task with additional information about /by
 */
public class Deadline extends Task {
    /** The By. */
    protected String by;

    /**
     * Instantiates a new Deadline.
     *
     * @param description the description
     * @param by          the by
     */
    Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return ("[D][\u2713] " + this.description + " (by: " + this.by + ")");
        } else {
            return ("[D][\u2718] " + this.description + " (by: " + this.by + ")");
        }
    }

    @Override
    public String textFormat() { //format is "Deadline.isDone .description"
        return ("deadline " + super.textFormat() + " /by " + this.by);
    }
}
