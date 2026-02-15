package penguin.task;

/**
 * The {@code ToDo} class represents a task that occurs over a specified
 * period of time.
 * <p>
 * It extends {@link Task}.
 */
public class ToDo extends Task {
    /**
     * Constructs an {@code ToDo} task with the given description.
     * <p>
     *
     * @param description the description of the deadline task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Encodes the task into a string representation suitable for storage.
     * @return the encoded string representation of the task
     */
    public String encode() {
        String isDone = isDone() ? "1" : "0";
        return "T" + " | " + isDone + " | " + getDescription();
    }

    /**
     * Returns the string representation of this todo task.
     *
     * @return the formatted string representation of the task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
