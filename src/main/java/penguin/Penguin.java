package penguin;

import penguin.commands.Command;
import penguin.exception.PenguinException;
import penguin.storage.Storage;
import penguin.ui.Ui;
import penguin.parser.Parser;
import task.Task;
import task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Penguin {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

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

    public static void main(String[] args) {
        new Penguin(Paths.get("data", "Penguin.txt")).run();
    }
}
