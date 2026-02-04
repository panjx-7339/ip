package penguin.commands;

import penguin.commands.exception.PenguinException;
import penguin.commands.storage.Storage;
import penguin.commands.ui.Ui;
import penguin.commands.parser.Parser;
import penguin.commands.task.Task;
import penguin.commands.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Penguin {
    private Storage storage;
    protected static TaskList taskList;
    private Ui ui;
    private Command command;
    private Parser parser;

    public Penguin(Path filePath) {
        ui = new Ui();
        loadTasks(filePath);
        command = new Command(taskList, ui);
        parser = new Parser();
    }

    private void loadTasks(Path filePath) {
        storage = new Storage(filePath);
        try {
            ArrayList<Task> loadedTasks = storage.loadData();
            taskList = new TaskList(loadedTasks);
            ui.echo("Loaded penguin.commands.task list from file.");
        } catch (PenguinException e) {
            ui.echo(e.getMessage() + "\nInitialising new list.");
            taskList = new TaskList();
        }
    }

    private void run() {
        ui.start();
        String input;
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                input = sc.nextLine().trim();
                // Exit conversation if user types the command "bye"
                if (input.equals("bye")) {
                    break;
                }
                parser.parseUserInput(ui, command, input);
            }
        } catch (PenguinException e) {
            ui.echo(e.getMessage());
        }
        ui.exit();
    }

    public static void main(String[] args) {
        new Penguin(Paths.get("data", "Penguin.txt")).run();
    }
}
