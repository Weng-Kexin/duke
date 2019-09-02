public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override //this override will override the Task.java override
    public String toString() {
        if (this.isDone) {
            return ("[E][\u2713] " + this.description + " (at: " + this.at + ")");
        } else {
            return ("[E][\u2718] " + this.description + " (at: " + this.at + ")");
        }
    }

    @Override //this override will override the Task.java override
    public String textFormat() {
        return ("event " + super.textFormat() + " /at " + this.at);
    }
}
