import java.util.Scanner;

public class Penguin {
    private static TaskList taskList = new TaskList();

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

    private static void listTasks() {
        echo("Here are the tasks in your list:\n" + taskList);
    }

    private static void markTask(int index) {
        Task t = taskList.markTask(index);
        echo("Nice! I've marked this task as done:\n\t" + t);
    }

    private static void UnmarkTask(int index) {
        Task t = taskList.UnmarkTask(index);
        echo("OK, I've marked this task as not done yet:\n\t" + t);
    }

    public static void main(String[] args) {
        start();
        Scanner sc = new Scanner(System.in);
        String input;
        label:
        while (true) {
            input = sc.nextLine();
            String action = input.split("\\s+")[0];

            switch (action) {
                case "bye":
                    break label;  // Exit conversation if user types the command "bye"
                case "list":
                    listTasks();  // List all tasks if user types "list"
                    break;
                case "mark": {
                    int index = Integer.parseInt(input.split("\\s+")[1]) - 1;
                    markTask(index);  // Mark task as done if user types "mark"

                    break;
                }
                case "unmark": {
                    int index = Integer.parseInt(input.split("\\s+")[1]) - 1;
                    UnmarkTask(index);  // Mark task as done if user types "unmark"

                    break;
                }
                case "todo": {
                    String description = input.replace(action, "").trim();
                    addTask(new ToDo(description));

                    break;
                }
                case "deadline": {
                    input = input.replace(action, "");
                    String[] bySplit = input.split("/by ");
                    String description = bySplit[0].trim();
                    String by = bySplit[1].trim();
                    addTask(new Deadline(description, by));

                    break;
                }
                case "event": {
                    input = input.replace(action, "");
                    String[] fromSplit = input.split("/from ");
                    String description = fromSplit[0].trim();
                    String[] toSplit = fromSplit[1].split("/to ");
                    String from = toSplit[0].trim();
                    String to = toSplit[1].trim();

                    addTask(new Event(description, from, to));

                    break;
                }
                default:
                    echo("Please enter a valid input.");
                    break;
            }
        }
        sc.close();
        exit();
    }
}
