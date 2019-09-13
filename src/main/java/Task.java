/**
 * Class represents a task, consisting of a description and isDone
 */
public class Task {
    /** The Description. */
    protected String description; //protected access is like public but not all can access

    /** The Is done. */
    protected boolean isDone;

    /**
     * Instantiates a new Task.
     *
     * @param description the description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /** Mark as done. */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Method overrides the default toString method and returns the isDone symbol with the task description
     * @return task description in customized format
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return ("[\u2713] " + this.description);
        } else {
            return ("[\u2718] " + this.description);
        }
    }

    /**
     * Method to convert task to text form, to be stored in the text file on hard drive
     *
     * @return this.isDone + this.description
     */
    public String textFormat() {
        if (this.isDone) {
            return ("1 " + this.description);
        } else {
            return ("0 " + this.description);
        }
    }

    /**
     * Gets description.
     *
     * @return description of task
     */
    public String getDescription() {
        return description;
    }
}
