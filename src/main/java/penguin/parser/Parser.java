package penguin.parser;

import penguin.commands.Command;
import penguin.exception.PenguinException;
import penguin.task.Deadline;
import penguin.task.Event;
import penguin.task.ToDo;

/**
 * The {@code Parser} class interprets user input and translates it into
 * executable commands.
 * <p>
 * It parses the user's raw input string, validates arguments, and
 * delegates the corresponding actions to the {@link Command} class.
 */
public class Parser {
    private Command command;

    /**
     * Constructs a {@code Parser} with the given command handler.
     *
     * @param command the {@code Command} object responsible for executing parsed user commands
     */
    public Parser(Command command) {
        this.command = command;
    }

    private int parseIndex(String args) throws PenguinException {
        if (!args.matches("\\d+")) {
            throw new PenguinException("Please enter a valid number!");
        }
        return Integer.parseInt(args) - 1;
    }

    private void prepareMark(String args) throws PenguinException {
        int index = parseIndex(args);
        command.markTask(index);
    }

    private void prepareUnmark(String args) throws PenguinException {
        int index = parseIndex(args);
        command.unmarkTask(index);
    }

    private void prepareRemove(String args) throws PenguinException {
        int index = parseIndex(args);
        command.removeTask(index);
    }

    private void prepareTodo(String args) throws PenguinException {
        if (args.isEmpty()) {
            throw new PenguinException("The description of a todo task cannot be empty!");
        }
        command.addTask(new ToDo(args));
    }

    private void prepareDeadline(String args) throws PenguinException {
        if (args.isEmpty()) {
            throw new PenguinException("The description of a deadline task cannot be empty!");
        }

        String[] bySplit = args.split("/by ");
        String description = bySplit[0].trim();
        String by = bySplit[1].trim();

        command.addTask(new Deadline(description, by));
    }

    private void prepareEvent(String args) throws PenguinException {
        if (args.isEmpty()) {
            throw new PenguinException("The description of an event task cannot be empty!");
        }

        String[] fromSplit = args.split("/from ");
        String description = fromSplit[0].trim();
        String[] toSplit = fromSplit[1].split("/to ");
        String from = toSplit[0].trim();
        String to = toSplit[1].trim();

        command.addTask(new Event(description, from, to));
    }

    /**
     * Parses and executes a user input command.
     * <p>
     * The input is split into a command keyword and its arguments.
     * Supported commands include {@code list}, {@code mark},
     * {@code unmark}, {@code delete}, {@code todo}, {@code deadline},
     * and {@code event}.
     *
     * @param input the raw user input string
     * @throws PenguinException if the command or arguments are invalid
     */
    public void parseUserInput(String input) throws PenguinException {
        String[] inputs = input.split("\\s+", 2);
        String action = inputs[0];

        String args = inputs.length > 1 ? inputs[1] : "";

        switch (action) {
        case "list":
            command.listTasks(); // List all tasks if user types "list"
            break;
        case "mark": {
            prepareMark(args); // Mark task as done if user types "mark"
            break;
        }
        case "unmark": {
            prepareUnmark(args); // Mark task as undone if user types "unmark"
            break;
        }
        case "delete": {
            prepareRemove(args);
            break;
        }
        case "todo": {
            prepareTodo(args);
            break;
        }
        case "deadline": {
            prepareDeadline(args);
            break;
        }
        case "event": {
            prepareEvent(args);
            break;
        }
        default:
            throw new PenguinException("Please enter a valid command!");
        }
    }
}


