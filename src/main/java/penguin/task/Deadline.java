package penguin.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import penguin.exception.PenguinException;

/**
 * The {@code Deadline} class represents a task that must be completed
 * by a specific date and time.
 * <p>
 * It extends {@link Task} and stores a deadline as a {@link LocalDateTime}.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Constructs a {@code Deadline} task with the given description
     * and deadline.
     * <p>
     * The deadline string must be in the format {@code yyyy-MM-dd HHmm}.
     *
     * @param description the description of the deadline task
     * @param by the deadline date and time in {@code yyyy-MM-dd HHmm} format
     * @throws PenguinException if the deadline string is incorrectly formatted
     */
    public Deadline(String description, String by) throws PenguinException {
        super(description);
        parseBy(by);
    }

    /**
     * Returns the deadline in a user-friendly string format.
     *
     * @return the formatted deadline string
     */
    public String getBy() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy ha");
        return by.format(formatter);
    }


    /**
     * Parses the deadline string and converts it into a {@link LocalDateTime}.
     *
     * @param byString the deadline string in {@code yyyy-MM-dd HHmm} format
     * @throws PenguinException if the deadline string is incorrectly formatted
     */
    private void parseBy(String byString) throws PenguinException {
        String[] details = byString.split("\\s+");
        try {
            // Date should be in yyyy-mm-dd format
            String dateString = details[0];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd")
                    .withResolverStyle(ResolverStyle.STRICT); // Ensure strict validation of dates
            LocalDate date = LocalDate.parse(dateString, formatter);

            // Time should be in HHmm format
            String timeString = details[1];
            LocalTime time = LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HHmm"));
            by = LocalDateTime.of(date, time);
        } catch (IndexOutOfBoundsException e) {
            throw new PenguinException("Noot noot <(`^´)>! The deadline must have both date and time~");
        } catch (DateTimeException e) {
            throw new PenguinException("Noot noot <(`^´)>! Deadline given is incorrectly formatted~");
        }
    }

    /**
     * Encodes the task into a string representation suitable for storage.
     * @return the encoded string representation of the task
     */
    public String encode() {
        String isDone = isDone() ? "1" : "0";
        return "D" + " | " + isDone + " | " + getDescription() + " | " + getBy();
    }

    /**
     * Returns the string representation of this deadline task.
     *
     * @return the formatted string representation of the task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getBy() + ")";
    }
}
