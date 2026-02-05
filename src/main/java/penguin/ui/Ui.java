package penguin.ui;

import penguin.task.Task;
import penguin.task.TaskList;

public class Ui {
    private final String DIVIDER = "____________________________________________________";

    public void echo(String s) {
        System.out.println(DIVIDER);
        System.out.println(s);
        System.out.println(DIVIDER + "\n");
    }

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

    public void exit() {
        echo("Bye. Hope to see you again soon!");
    }

    public void showTaskAdded(Task t, TaskList taskList) {
        echo("Got it. I've added this task:\n\t" + t + "\nNow you have " + taskList.getSize()
                + (taskList.getSize() > 1 ? " tasks" : " task") + " in the list.");
    }

    public void showTaskRemoved(Task t, TaskList taskList) {
        echo("Noted. I've removed this task:\n\t" + t + "\nNow you have " + taskList.getSize()
                + (taskList.getSize() > 1 ? " tasks" : " task") + " in the list.");
    }

    public void showTaskList(TaskList taskList) {
        echo("Here are the tasks in your list:\n" + taskList);
    }

    public void showTaskMarked(Task t) {
        echo("Nice! I've marked this task as done:\n\t" + t);
    }

    public void showTaskUnmarked(Task t) {
        echo("OK, I've marked this task as not done yet:\n\t" + t);
    }
}
