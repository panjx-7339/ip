package penguin.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import penguin.exception.PenguinException;

public class DeadlineTest {

    // Tests for parseBy method (via constructor)

    @Test
    public void constructor_validDateTime_success() throws PenguinException {
        Deadline deadline = new Deadline("return book", "2020-01-01 1400");
        assertEquals("1 JAN 2020 2PM", deadline.getBy());
    }

    @Test
    public void constructor_invalidDate_throwsPenguinException() {
        PenguinException exception = assertThrows(PenguinException.class, () -> {
            new Deadline("return book", "2020-02-30 1400");
        });
        assertTrue(exception.getMessage().contains("incorrectly formatted"));
    }

    @Test
    public void constructor_invalidTime_throwsPenguinException() {
        PenguinException exception = assertThrows(PenguinException.class, () -> {
            new Deadline("return book", "2020-01-01 2500");
        });
        assertTrue(exception.getMessage().contains("incorrectly formatted"));
    }

    @Test
    public void constructor_missingTime_throwsPenguinException() {
        PenguinException exception = assertThrows(PenguinException.class, () -> {
            new Deadline("return book", "2020-01-01");
        });
        assertTrue(exception.getMessage().contains("both date and time"));
    }

    // Tests for getBy method

    @Test
    public void getBy_validDeadline_returnsFormattedString() throws PenguinException {
        Deadline deadline = new Deadline("submit assignment", "2024-12-25 0900");
        assertEquals("25 DEC 2024 9AM", deadline.getBy());
    }

    @Test
    public void getBy_afternoonTime_returnsFormattedWithPM() throws PenguinException {
        Deadline deadline = new Deadline("meeting", "2024-06-15 1530");
        assertEquals("15 JUN 2024 3PM", deadline.getBy());
    }

    @Test
    public void getBy_midnightTime_returnsFormattedCorrectly() throws PenguinException {
        Deadline deadline = new Deadline("midnight task", "2024-01-01 0000");
        assertEquals("1 JAN 2024 12AM", deadline.getBy());
    }

    // Tests for encode method

    @Test
    public void encode_uncompletedTask_returnsCorrectFormat() throws PenguinException {
        Deadline deadline = new Deadline("buy milk", "2020-01-15 1400");
        assertEquals("D | 0 | buy milk | 15 JAN 2020 2PM", deadline.encode());
    }

    @Test
    public void encode_completedTask_returnsCorrectFormat() throws PenguinException {
        Deadline deadline = new Deadline("return book", "2024-03-20 0900");
        deadline.markDone();
        assertEquals("D | 1 | return book | 20 MAR 2024 9AM", deadline.encode());
    }

    @Test
    public void markAsDone_changesStatus_reflectedInEncode() throws PenguinException {
        Deadline deadline = new Deadline("task", "2024-06-01 1200");
        assertFalse(deadline.isDone());
        assertEquals("D | 0 | task | 1 JUN 2024 12PM", deadline.encode());

        deadline.markDone();
        assertTrue(deadline.isDone());
        assertEquals("D | 1 | task | 1 JUN 2024 12PM", deadline.encode());
    }

    @Test
    public void markAsNotDone_changesStatus_reflectedInEncode() throws PenguinException {
        Deadline deadline = new Deadline("task", "2024-06-01 1200");
        deadline.markDone();
        assertTrue(deadline.isDone());

        deadline.markUndone();
        assertFalse(deadline.isDone());
        assertEquals("D | 0 | task | 1 JUN 2024 12PM", deadline.encode());
    }

    // Tests for toString method

    @Test
    public void toString_uncompletedTask_returnsCorrectFormat() throws PenguinException {
        Deadline deadline = new Deadline("homework", "2024-05-10 1800");
        assertEquals("[D][ ] homework (by: 10 MAY 2024 6PM)", deadline.toString());
    }

    @Test
    public void toString_completedTask_returnsCorrectFormat() throws PenguinException {
        Deadline deadline = new Deadline("project", "2024-11-30 2359");
        deadline.markDone();
        assertEquals("[D][X] project (by: 30 NOV 2024 11PM)", deadline.toString());
    }

    // Tests for constructor

    @Test
    public void constructor_firstDayOfYear_success() throws PenguinException {
        Deadline deadline = new Deadline("new year task", "2025-01-01 0001");
        assertEquals("1 JAN 2025 12AM", deadline.getBy());
    }

    @Test
    public void constructor_lastDayOfYear_success() throws PenguinException {
        Deadline deadline = new Deadline("year end task", "2024-12-31 2359");
        assertEquals("31 DEC 2024 11PM", deadline.getBy());
    }

    @Test
    public void constructor_invalidMonth_throwsPenguinException() {
        PenguinException exception = assertThrows(PenguinException.class, () -> {
            new Deadline("task", "2024-13-01 1200");
        });
        assertTrue(exception.getMessage().contains("incorrectly formatted"));
    }

    @Test
    public void constructor_invalidDay_throwsPenguinException() {
        PenguinException exception = assertThrows(PenguinException.class, () -> {
            new Deadline("task", "2024-04-31 1200");
        });
        assertTrue(exception.getMessage().contains("incorrectly formatted"));
    }
}
