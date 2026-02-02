import java.util.ArrayList;
import java.util.Scanner;

public class Penguin {
    private static TaskList taskList;

    private static void loadTasks() {
        try {
            ArrayList<Task> loadedTasks = Storage.loadData();
            taskList = new TaskList(loadedTasks);
            echo("Loaded task list from file.");
        } catch (PenguinException e) {
            echo(e.getMessage() + "\nInitialising new list.");
            taskList = new TaskList();
        }
    }

    private static void echo(String s) {
        System.out.println("____________________________________________________");
        System.out.println(s);
        System.out.println("____________________________________________________\n");
    }

    private static void start() {
        String logo = "⠀⠀⠀⠀⠀⠀⠀⣠⣠⣶⣿⣷⣿⣿⣿⣷⣷⣶⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⣤⣾⣿⢿⣻⡽⣞⣳⡽⠚⠉⠉⠙⠛⢿⣶⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⣼⣿⣿⢻⣟⣧⢿⣻⢿⠀⠀⠀⠀⠀⠀⠀⠻⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⢀⣾⣿⡿⠞⠛⠚⠫⣟⡿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠘⢿⣧⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⣼⣿⡟⠀⠀⠀⠀⠀⠈⢻⡽⣆⠀⠀⣴⣿⣿⡇⠀⠀⠘⣿⡆⡀⠀⣀⣠⣤⡄\n" +
                "⠀⠀⣿⣿⠁⠀⠀⠀⠀⠀⠀⠈⣿⠿⢷⡀⢸⣿⣯⣷⠠⠀⠀⣿⣅⣴⡶⠟⠋⢹⣿\n" +
                "⠀⠀⢻⣿⡀⠀⠀⠀⣾⣿⡿⡆⢿⣴⣴⡇⠀⠀⠉⠁⠀⠀⢠⡟⠋⠁⠀⠀⠀⢸⣿\n" +
                "⠀⠀⠈⢿⣇⠀⠀⠐⣿⣿⡷⡆⠀⠉⠉⠀⠀⠀⠀⠀⠀⢀⡾⠁⠀⠀⠀⠀⠀⣾⡏\n" +
                "⠀⠀⠀⠈⢿⣦⡀⠀⠉⠋⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠸⠁⠀⠀⠀⠀⠀⣼⡟⠀\n" +
                "⠀⠀⠀⠀⠀⣹⣿⣶⣤⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠂⠁⠀⠐⢧⡀⠀⢀⣾⠟⠀⠀\n" +
                "⠀⠀⢀⣰⣾⠟⠉⠀⠀⠉⠉⠀⠐⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢿⣶⡟⠋⠀⠀⠀\n" +
                "⣠⣶⡿⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⠀⢈⣿⡆⠀⠀⠀⠀\n" +
                "⢻⣧⣄⠀⠀⠀⣰⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⠀⠀⠀⠀\n" +
                "⠀⠉⠛⠿⣷⣶⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣤⣤⣾⣿⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⢹⣿⣿⣿⣿⣷⣦⡀⠀⢀⣀⠀⠀⠀⣠⣴⣿⣿⣿⣿⣷⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠻⢿⣿⣿⣿⣿⠿⠿⣿⡿⠿⠿⠿⠿⣿⣿⣿⠿⠟⠁⠀⠀⠀⠀";

        echo("Hello! I'm Penguin, your personal task tracker.\n" + logo + "\nWhat can I do for you?");
    }

    private static void exit() {
        echo("Bye. Hope to see you again soon!");
    }

    private static void addTask(Task t) {
        taskList.addTask(t);
        echo("Got it. I've added this task:\n\t" + t + "\nNow you have " + taskList.getSize() +
                (taskList.getSize() > 1 ? " tasks" : " task") + " in the list.");
    }

    private static void removeTask(int index) throws PenguinException {
        Task t = taskList.removeTask(index);
        echo("Noted. I've removed this task:\n\t" + t + "\nNow you have " + taskList.getSize() +
                (taskList.getSize() > 1 ? " tasks" : " task") + " in the list.");
    }

    private static void listTasks() {
        echo("Here are the tasks in your list:\n" + taskList);
    }

    private static void markTask(int index) throws PenguinException {
        Task t = taskList.markTask(index);
        echo("Nice! I've marked this task as done:\n\t" + t);
    }

    private static void unmarkTask(int index) throws PenguinException {
        Task t = taskList.unmarkTask(index);
        echo("OK, I've marked this task as not done yet:\n\t" + t);
    }


    public static void main(String[] args) {

            start();
            loadTasks();
            Scanner sc = new Scanner(System.in);
            String input;
            run:
            while (true) {
                try {
                    input = sc.nextLine();
                    String[] inputs = input.split("\\s+", 2);
                    String action = inputs[0];

                    String details = inputs.length > 1 ? inputs[1] : "";

                    switch (action) {
                    case "bye":
                        break run;  // Exit conversation if user types the command "bye"
                    case "list":
                        listTasks();  // List all tasks if user types "list"
                        break;
                    case "mark": {
                        if (!details.matches("\\d+")) {
                            throw new PenguinException("Please enter a valid number!");
                        }
                        int index = Integer.parseInt(details) - 1;

                        markTask(index);  // Mark task as done if user types "mark"
                        Storage.saveData(taskList);
                        break;
                    }
                    case "unmark": {
                        if (!details.matches("\\d+")) {
                            throw new PenguinException("Please enter a valid number!");
                        }
                        int index = Integer.parseInt(details) - 1;

                        unmarkTask(index);  // Mark task as done if user types "unmark"
                        Storage.saveData(taskList);
                        break;
                    }
                    case "delete": {
                        if (!details.matches("\\d+")) {
                            throw new PenguinException("Please enter a valid number!");
                        }
                        int index = Integer.parseInt(details) - 1;

                        removeTask(index);
                        Storage.saveData(taskList);
                        break;
                    }
                    case "todo": {
                        if (details.isEmpty()) {
                            throw new PenguinException("The description of a todo task cannot be empty!");
                        }

                        addTask(new ToDo(details));
                        Storage.saveData(taskList);
                        break;
                    }
                    case "deadline": {
                        if (details.isEmpty()) {
                            throw new PenguinException("The description of a deadline task cannot be empty!");
                        }

                        String[] bySplit = details.split("/by ");
                        String description = bySplit[0].trim();
                        String by = bySplit[1].trim();

                        addTask(new Deadline(description, by));
                        Storage.saveData(taskList);
                        break;
                    }
                    case "event": {
                        if (details.isEmpty()) {
                            throw new PenguinException("The description of an event task cannot be empty!");
                        }

                        String[] fromSplit = details.split("/from ");
                        String description = fromSplit[0].trim();
                        String[] toSplit = fromSplit[1].split("/to ");
                        String from = toSplit[0].trim();
                        String to = toSplit[1].trim();

                        addTask(new Event(description, from, to));
                        Storage.saveData(taskList);
                        break;
                    }
                    default:
                        throw new PenguinException("Please enter a valid command!");
                    }
                } catch (Exception e) {
                    echo(e.getMessage());
                }
            }
            sc.close();
            exit();
    }
}
