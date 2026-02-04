package task;
import penguin.exception.PenguinException;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, String by) throws PenguinException {
        super(description);
        parseBy(by);
    }

    public String getBy() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy ha");
        return by.format(formatter);
    }

    private void parseBy(String byString) throws PenguinException {
        String[] details = byString.split(" ");
        try {
            // Date is in yyyy-mm-dd format
            String dateString = details[0];
            LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            // Time is in HHmm format
            String timeString = details[1];
            LocalTime time = LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HHmm"));
            by = LocalDateTime.of(date, time);
        } catch (Exception e) {
            throw new PenguinException("Deadline given is incorrectly formatted!");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getBy() + ")";
    }
}
