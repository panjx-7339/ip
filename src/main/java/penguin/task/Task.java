package penguin.task;

/**
 * The {@code Task} class represents a generic task.
 * <p>
 * It serves as the base class for all specific task types and
 * stores common attributes such as description and completion status.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a {@code Task} with the given description.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Returns the description of this task.
     *
     * @return the task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns whether this task has been completed.
     *
     * @return {@code true} if the task is completed, {@code false} otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns a status icon representing the completion state of the task.
     *
     * @return {@code "X"} if the task is completed, or a blank space otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark completed task with X
    }

    /**
     * Marks this task as completed.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Marks this task as not completed.
     */
    public void markUndone() {
        isDone = false;
    }

    /**
     * Returns the string representation of this task.
     *
     * @return the formatted string representation of the task
     */
    @Override
    public String toString() {
        return ("[" + getStatusIcon() + "] " + description);
    }
}
