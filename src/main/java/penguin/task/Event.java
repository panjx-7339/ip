package penguin.task;

import java.time.LocalDateTime;

import penguin.exception.PenguinException;

/**
 * The {@code Event} class represents a task that occurs over a specified
 * period of time.
 * <p>
 * It extends {@link Task} and stores a from and to string.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs an {@code Event} task with the given description and period.
     * <p>
     *
     * @param description the description of the deadline task
     * @param from string representing when the event starts.
     * @param to string representing when the event ends.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start time of the event.
     *
     * @return the from string
     */
    public String getFrom() {
        return from;
    }

    /**
     * Returns the end time of the event.
     *
     * @return the to string
     */
    public String getTo() {
        return to;
    }

    /**
     * Returns the string representation of this event task.
     *
     * @return the formatted string representation of the task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
