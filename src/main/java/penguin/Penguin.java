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
    private boolean loadedTasksExist;

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
        loadedTasksExist = loadTasks();
        Command command = new Command(taskList, storage);
        parser = new Parser(command);
    }

    /**
     * Shows welcome message upon starting the application.
     * @return the welcome message string
     */
    public String welcome() {
        return ui.start();
    }

    /**
     * Shows the list of tasks loaded from disk if the file exists.
     * Else, displays message that new task list has been created.
     * @return the confirmation message that tasks have been initialised
     */
    public String showLoadedTasks() {
        if (loadedTasksExist) {
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

    /**
     * Parses the user input to check if it is a valid command and responds accordingly.
     * @param input the user input
     * @return the response message to user input
     */
    public String respond(String input) {
        try {
            return parser.parseUserInput(input);
        } catch (Exception e) {
            return ui.echo(e.getMessage());
        }
    }
}
