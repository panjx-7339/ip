package penguin.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    @Test
    void toString_withMockedTasks_returnsCorrectFormat() {
        // Mock tasks
        Task todo = mock(Task.class);
        Task deadline = mock(Task.class);
        Task event = mock(Task.class);

        // Define fixed return strings for toString()
        when(todo.toString()).thenReturn("[T][X] buy bread");
        when(deadline.toString()).thenReturn("[D][ ] do work (by: 1 Jan 2026 2pm)");
        when(event.toString()).thenReturn("[E][ ] project meeting (from: 2pm to: 3pm)");

        // Add mocks to task list
        taskList.addTask(todo);
        taskList.addTask(deadline);
        taskList.addTask(event);

        // Construct expected output
        String expected = "1.[T][X] buy bread\n"
                + "2.[D][ ] do work (by: 1 Jan 2026 2pm)\n"
                + "3.[E][ ] project meeting (from: 2pm to: 3pm)";

        // Assert
        assertEquals(expected, taskList.toString());
    }

    @Test
    void testToString_emptyList_returnsEmptyString() {
        // Assert
        assertEquals("", taskList.toString());
    }
}
