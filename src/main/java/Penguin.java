import java.util.Scanner;
import java.util.ArrayList;

public class Penguin {
    private static ArrayList<Task> taskList = new ArrayList<>();

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
        System.out.println("____________________________________________________");
        System.out.println("Hello! I'm Penguin, your personal task tracker.\n" + logo + "\nWhat can I do for you?");
        System.out.println("____________________________________________________\n");
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void addTask(Task t) {
        taskList.add(t);
        System.out.println("Got it. I've added this task:\n\t" + t + "\nNow you have " + taskList.size() +
                (taskList.size() > 1 ? " tasks" : " task") + " in the list.");
    }

    private static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            Task t = taskList.get(i-1);
            System.out.println(i + "." + t);
        }
    }

    private static void markTask(int index) {
        Task t = taskList.get(index);
        t.markDone();
        System.out.println("Nice! I've marked this task as done:\n\t" + t);
    }

    private static void UnmarkTask(int index) {
        Task t = taskList.get(index);
        t.markUndone();
        System.out.println("OK, I've marked this task as not done yet:\n\t" + t);
    }

    public static void main(String[] args) {
        start();
        Scanner sc = new Scanner(System.in);
        String input = "";
        label:
        while (true) {
            input = sc.nextLine();
            String action = input.split("\\s+")[0];

            System.out.println("____________________________________________________");
            switch (action) {
                case "bye":
                    break label;  // Exit conversation if user types the command "bye"
                case "list":
                    listTasks();  // List all tasks if user types "list"

                    System.out.println("____________________________________________________\n");
                    break;
                case "mark": {
                    int index = Integer.parseInt(input.split("\\s+")[1]) - 1;
                    markTask(index);  // Mark task as done if user types "mark"

                    System.out.println("____________________________________________________\n");
                    break;
                }
                case "unmark": {
                    int index = Integer.parseInt(input.split("\\s+")[1]) - 1;
                    UnmarkTask(index);  // Mark task as done if user types "unmark"

                    System.out.println("____________________________________________________\n");
                    break;
                }
                case "todo": {
                    String description = input.split("\\s+")[1];
                    addTask(new ToDo(description));

                    System.out.println("____________________________________________________\n");
                    break;
                }
                case "deadline": {
                    input = input.replace(action, "");
                    String[] bySplit = input.split("/by ");
                    String description = bySplit[0].trim();
                    String by = bySplit[1].trim();
                    addTask(new Deadline(description, by));

                    System.out.println("____________________________________________________\n");
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

                    System.out.println("____________________________________________________\n");
                    break;
                }
                default:
                    System.out.println("Please enter a valid input.");

                    System.out.println("____________________________________________________\n");
                    break;
            }
        }
        sc.close();
        exit();
        System.out.println("____________________________________________________\n");
    }
}
