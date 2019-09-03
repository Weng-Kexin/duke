public class Task {
    protected String description; //protected access is like public but not all can access
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return ("[\u2713] " + this.description);
        } else {
            return ("[\u2718] " + this.description);
        }
    }

    public String textFormat() { //to store in text file, format is ".isDone .description"
        if (this.isDone) {
            return ("1 " + this.description);
        } else {
            return ("0 " + this.description);
        }
    }

    public String getDescription() {
        return description;
    }
}
