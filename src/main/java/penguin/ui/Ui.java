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
    private final String DIVIDER = "____________________________________________________";

    /**
     * Displays a message to the user surrounded by dividers.
     *
     * @param s the message to be displayed
     */
    public void echo(String s) {
        System.out.println(DIVIDER);
        System.out.println(s);
        System.out.println(DIVIDER + "\n");
    }

    /**
     * Displays the welcome message and application logo.
     */
    public void start() {
        String logo = """
                ⠀⠀⠀⠀⠀⠀⠀⣠⣠⣶⣿⣷⣿⣿⣿⣷⣷⣶⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⣤⣾⣿⢿⣻⡽⣞⣳⡽⠚⠉⠉⠙⠛⢿⣶⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⣼⣿⣿⢻⣟⣧⢿⣻⢿⠀⠀⠀⠀⠀⠀⠀⠻⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⢀⣾⣿⡿⠞⠛⠚⠫⣟⡿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠘⢿⣧⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⣼⣿⡟⠀⠀⠀⠀⠀⠈⢻⡽⣆⠀⠀⣴⣿⣿⡇⠀⠀⠘⣿⡆⡀⠀⣀⣠⣤⡄
                ⠀⠀⣿⣿⠁⠀⠀⠀⠀⠀⠀⠈⣿⠿⢷⡀⢸⣿⣯⣷⠠⠀⠀⣿⣅⣴⡶⠟⠋⢹⣿
                ⠀⠀⢻⣿⡀⠀⠀⠀⣾⣿⡿⡆⢿⣴⣴⡇⠀⠀⠉⠁⠀⠀⢠⡟⠋⠁⠀⠀⠀⢸⣿
                ⠀⠀⠈⢿⣇⠀⠀⠐⣿⣿⡷⡆⠀⠉⠉⠀⠀⠀⠀⠀⠀⢀⡾⠁⠀⠀⠀⠀⠀⣾⡏
                ⠀⠀⠀⠈⢿⣦⡀⠀⠉⠋⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠸⠁⠀⠀⠀⠀⠀⣼⡟⠀
                ⠀⠀⠀⠀⠀⣹⣿⣶⣤⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠂⠁⠀⠐⢧⡀⠀⢀⣾⠟⠀⠀
                ⠀⠀⢀⣰⣾⠟⠉⠀⠀⠉⠉⠀⠐⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢿⣶⡟⠋⠀⠀⠀
                ⣠⣶⡿⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⠀⢈⣿⡆⠀⠀⠀⠀
                ⢻⣧⣄⠀⠀⠀⣰⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⠀⠀⠀⠀
                ⠀⠉⠛⠿⣷⣶⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣤⣤⣾⣿⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⢹⣿⣿⣿⣿⣷⣦⡀⠀⢀⣀⠀⠀⠀⣠⣴⣿⣿⣿⣿⣷⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠻⢿⣿⣿⣿⣿⠿⠿⣿⡿⠿⠿⠿⠿⣿⣿⣿⠿⠟⠁⠀⠀⠀⠀""";

        echo("Hello! I'm Penguin, your personal task tracker.\n" + logo + "\nWhat can I do for you?");
    }

    /**
     * Displays the goodbye message when the application terminates.
     */
    public void exit() {
        echo("Bye. Hope to see you again soon!");
    }

    /**
     * Displays a confirmation message after a task is added.
     *
     * @param t the task that was added
     * @param taskList the updated task list
     */
    public void showTaskAdded(Task t, TaskList taskList) {
        echo("Got it. I've added this task:\n\t" + t + "\nNow you have " + taskList.getSize()
                + (taskList.getSize() > 1 ? " tasks" : " task") + " in the list.");
    }

    /**
     * Displays a confirmation message after a task is removed.
     *
     * @param t the task that was removed
     * @param taskList the updated task list
     */
    public void showTaskRemoved(Task t, TaskList taskList) {
        echo("Noted. I've removed this task:\n\t" + t + "\nNow you have " + taskList.getSize()
                + (taskList.getSize() > 1 ? " tasks" : " task") + " in the list.");
    }

    /**
     * Displays all tasks in the task list.
     *
     * @param taskList the task list to be displayed
     */
    public void showTaskList(TaskList taskList) {
        echo("Here are the tasks in your list:\n" + taskList);
    }

    /**
     * Displays a confirmation message after a task is marked as completed.
     *
     * @param t the task that was marked as completed
     */
    public void showTaskMarked(Task t) {
        echo("Nice! I've marked this task as done:\n\t" + t);
    }

    /**
     * Displays a confirmation message after a task is marked as not completed.
     *
     * @param t the task that was marked as not completed
     */
    public void showTaskUnmarked(Task t) {
        echo("OK, I've marked this task as not done yet:\n\t" + t);
    }

    public void showMatchingTasks(TaskList matchingTasks) {
        echo("Here are the matching tasks in your list:\n" + matchingTasks);
    }
}
