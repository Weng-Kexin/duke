/**
 * Class inherits from task
 */
public class ToDo extends Task {

    /**
     * Instantiates a new ToDo.
     *
     * @param description the description
     */
    ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return ("[T][\u2713] " + this.description);
        } else {
            return ("[T][\u2718] " + this.description);
        }
    }

    @Override
    public String textFormat() {
        return ("todo " + super.textFormat());
    }
}