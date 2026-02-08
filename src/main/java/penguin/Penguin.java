package penguin;

import java.nio.file.Path;
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
    private Path filePath;

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
        this.filePath = filePath;
        loadTasks();
        Command command = new Command(taskList, storage);
        parser = new Parser(command);
    }

    public String welcome() {
        return ui.start();
    }

    public String showLoadedTasks() {
        if (loadTasks()) {
            return ui.echo("I've loaded your task list from the previous session.");
        }
        return ui.echo("I've created a new task list.");
    }

    private boolean loadTasks() {
        storage = new Storage(filePath);
        try {
            ArrayList<Task> loadedTasks = storage.loadData();
            taskList = new TaskList(loadedTasks);
            return true;
        } catch (PenguinException e) {
            taskList = new TaskList();
            return false;
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

    public String respond(String input) {
        try {
            return parser.parseUserInput(input);
        } catch (Exception e) {
            return ui.echo(e.getMessage());
        }
    }
}
