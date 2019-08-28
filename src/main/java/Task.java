public class Task {
    protected String description; //protected access is like public but not all can access
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ?  "\u2713" : "\u2718");
    }

    public String getTaskName() {
        return (this.description);
    }

    public void markAsDone(String description) {
        this.isDone = true;
    }
}
