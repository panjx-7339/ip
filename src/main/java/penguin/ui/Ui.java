package penguin.ui;

import penguin.task.Task;
import penguin.task.TaskList;

/**
 * The {@code Ui} class handles all user-facing output.
 * <p>
 * It is responsible for displaying messages, task information,
 * and application status updates to the user via standard output.
 */
public class Ui {
    /**
     * Displays a message to the user surrounded by dividers.
     *
     * @param s the message to be displayed
     */
    public String echo(String s) {
        return s;
    }

    /**
     * Displays the welcome message.
     */
    public String start() {
        return "Hello! I'm Penguin, your personal task tracker. What can I do for you?";
    }

    /**
     * Displays a confirmation message after a task is added.
     *
     * @param t the task that was added
     * @param taskList the updated task list
     */
    public String showTaskAdded(Task t, TaskList taskList) {
        return "Got it. I've added this task:\n\t" + t + "\nNow you have " + taskList.getSize()
                + (taskList.getSize() > 1 ? " tasks" : " task") + " in the list cheep!";
    }

    /**
     * Displays a confirmation message after a task is removed.
     *
     * @param t the task that was removed
     * @param taskList the updated task list
     */
    public String showTaskRemoved(Task t, TaskList taskList) {
        return "Got it. I've removed this task:\n\t" + t + "\nNow you have " + taskList.getSize()
                + (taskList.getSize() > 1 ? " tasks" : " task") + " in the list cheep!";
    }

    /**
     * Displays all tasks in the task list.
     *
     * @param taskList the task list to be displayed
     */
    public String showTaskList(TaskList taskList) {
        return "Here are the tasks in your list cheep!\n" + taskList;
    }

    /**
     * Displays a confirmation message after a task is marked as completed.
     *
     * @param t the task that was marked as completed
     */
    public String showTaskMarked(Task t) {
        return "Got it. I've marked this task as done cheep!\n\t" + t;
    }

    /**
     * Displays a confirmation message after a task is marked as not completed.
     *
     * @param t the task that was marked as not completed
     */
    public String showTaskUnmarked(Task t) {
        return "Got it. I've marked this task as not done yet cheep!\n\t" + t;
    }

    public String showMatchingTasks(TaskList matchingTasks) {
        return "Here are the matching tasks in your list cheep!\n" + matchingTasks;
    }
}
