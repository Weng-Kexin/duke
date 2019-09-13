/**
 * Class inherits from task, with additional information about /at
 */
public class Event extends Task {
    /** The At. */
    protected String at;

    /**
     * Instantiates a new Event.
     *
     * @param description the description
     * @param at          the at
     */
    Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return ("[E][\u2713] " + this.description + " (at: " + this.at + ")");
        } else {
            return ("[E][\u2718] " + this.description + " (at: " + this.at + ")");
        }
    }

    @Override
    public String textFormat() {
        return ("event " + super.textFormat() + " /at " + this.at);
    }
}
