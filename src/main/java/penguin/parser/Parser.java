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

    private String prepareMark(String args) throws PenguinException {
        int index = parseIndex(args);
        return command.markTask(index);
    }

    private String prepareUnmark(String args) throws PenguinException {
        int index = parseIndex(args);
        return command.unmarkTask(index);
    }

    private String prepareRemove(String args) throws PenguinException {
        int index = parseIndex(args);
        return command.removeTask(index);
    }

    private String prepareTodo(String args) throws PenguinException {
        if (args.isEmpty()) {
            throw new PenguinException("The description of a todo task cannot be empty!");
        }
        return command.addTask(new ToDo(args));
    }

    private String prepareDeadline(String args) throws PenguinException {
        if (args.isEmpty()) {
            throw new PenguinException("The description of a deadline task cannot be empty!");
        }

        String[] bySplit = args.split("/by ");
        String description = bySplit[0].trim();
        String by = bySplit[1].trim();

        return command.addTask(new Deadline(description, by));
    }

    private String prepareEvent(String args) throws PenguinException {
        if (args.isEmpty()) {
            throw new PenguinException("The description of an event task cannot be empty!");
        }

        String[] fromSplit = args.split("/from ");
        String description = fromSplit[0].trim();
        String[] toSplit = fromSplit[1].split("/to ");
        String from = toSplit[0].trim();
        String to = toSplit[1].trim();

        return command.addTask(new Event(description, from, to));
    }

    private String prepareFind(String args) throws PenguinException {
        if (args.isEmpty()) {
            throw new PenguinException("Please enter a task keyword!");
        }
        return command.findTasks(args);
    }

    /**
     * Parses and executes a user input command.
     * <p>
     * The input is split into a command keyword and its arguments.
     * Supported commands include {@code list}, {@code mark},
     * {@code unmark}, {@code delete}, {@code todo}, {@code deadline},
     * {@code event} and {@code find}.
     *
     * @param input the raw user input string
     * @throws PenguinException if the command or arguments are invalid
     */
    public String parseUserInput(String input) throws PenguinException {
        String[] inputs = input.split("\\s+", 2);
        String action = inputs[0];

        String args = inputs.length > 1 ? inputs[1] : "";

        switch (action) {
            // Todo: handle exit command
        case "list":
            return command.listTasks(); // List all tasks if user types "list"
        case "mark": {
            return prepareMark(args); // Mark task as done if user types "mark"
        }
        case "unmark": {
            return prepareUnmark(args); // Mark task as undone if user types "unmark"
        }
        case "delete": {
            return prepareRemove(args);
        }
        case "todo": {
            return prepareTodo(args);
        }
        case "deadline": {
            return prepareDeadline(args);
        }
        case "event": {
            return prepareEvent(args);
        }
        case "find": {
            return prepareFind(args);
        }
        default:
            throw new PenguinException("Please enter a valid command!");
        }
    }
}


