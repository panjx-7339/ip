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

    private static void addTask(String description) {
        taskList.add(new Task(description));
        System.out.println("added: " + description);
    }

    private static void listTasks() {
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
        while (true) {
            input = sc.nextLine();
            String action = input.split("\\s+")[0];

            System.out.println("____________________________________________________");
            if (action.equals("bye")) {
                break;  // Exit conversation if user types the command "bye"
            } else if (action.equals("list")) {
                listTasks();  // List all tasks if user types "list"
                System.out.println("____________________________________________________\n");
            } else if (action.equals("mark")) {
                int index = Integer.parseInt(input.split("\\s+")[1]) - 1;
                markTask(index);  // Mark task as done if user types "mark"
                System.out.println("____________________________________________________\n");
            } else if (action.equals("unmark")) {
                int index = Integer.parseInt(input.split("\\s+")[1]) - 1;
                UnmarkTask(index);  // Mark task as done if user types "unmark"
                System.out.println("____________________________________________________\n");
            } else {
                addTask(input);  // Else, add task with the given input as its description
                System.out.println("____________________________________________________\n");
            }
        }
        sc.close();
        exit();
        System.out.println("____________________________________________________\n");
    }
}
