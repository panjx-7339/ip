package penguin;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import penguin.commands.Command;
import penguin.exception.PenguinException;
import penguin.parser.Parser;
import penguin.storage.Storage;
import penguin.task.Task;
import penguin.task.TaskList;
import penguin.ui.Ui;

/**
 * The {@code Penguin} class is the main entry point of the Penguin task
 * management application. It is responsible for initialising core
 * components such as storage, task list, user interface, and parser,
 * and for running the main application loop.
 */
public class Penguin {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs a {@code Penguin} application instance.
     * <p>
     * This constructor initialises the user interface, loads tasks from
     * the specified file path, and prepares the parser and command
     * handler for processing user input.
     *
     * @param filePath the path to the file used for loading and storing tasks
     */
    public Penguin(Path filePath) {
        ui = new Ui();
        ui.start();
        loadTasks(filePath);

        Command command = new Command(taskList, storage);
        parser = new Parser(command);
    }

    private void loadTasks(Path filePath) {
        storage = new Storage(filePath);
        try {
            ArrayList<Task> loadedTasks = storage.loadData();
            taskList = new TaskList(loadedTasks);
            ui.echo("Loaded task list from file.");
        } catch (PenguinException e) {
            ui.echo(e.getMessage() + "\nInitialising new list.");
            taskList = new TaskList();
        }
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        String input;

        while (true) {
            try {
                input = sc.nextLine().trim();
                // Exit conversation if user types the command "bye"
                if (input.equals("bye")) {
                    ui.exit();
                    break;
                }
                parser.parseUserInput(input);
            } catch (Exception e) {
                ui.echo(e.getMessage());
            }
        }
        sc.close();
    }

    /**
     * The main entry point of the Penguin application.
     * <p>
     * This method creates a {@code Penguin} instance using the default
     * data file location and starts the application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Penguin(Paths.get("data", "Penguin.txt")).run();
    }
}
