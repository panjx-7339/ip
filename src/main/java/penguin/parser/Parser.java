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

    @FunctionalInterface
    private interface IndexedCommand {
        String execute(int index) throws PenguinException;
    }

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
            throw new PenguinException("Noot noot <(`^´)>! Please enter a number corresponding to a task~");
        }
        return Integer.parseInt(args) - 1;
    }

    private String handleIndexedCommand(String args, IndexedCommand action) throws PenguinException {
        int index = parseIndex(args);
        return action.execute(index);
    }

    private void validateContainsDelimiter(String input, String delimiter, String errorMessage)
            throws PenguinException {
        if (!input.contains(delimiter)) {
            throw new PenguinException(errorMessage);
        }
    }

    private void validateArgsNotEmpty(String value, String errorMessage) throws PenguinException {
        if (value.isEmpty()) {
            throw new PenguinException(errorMessage);
        }
    }

    private String[] splitAndValidate(String input, String delimiter, String errorMessage)
            throws PenguinException {
        String[] parts = input.split(delimiter);
        if (parts.length < 2) {
            throw new PenguinException(errorMessage);
        }
        return parts;
    }

    private String prepareTodo(String args) throws PenguinException {
        String description = args.trim();
        // Check that fields are not empty
        validateArgsNotEmpty(description, "The description of a todo task cannot be empty!");
        return command.addTask(new ToDo(description));
    }


    private String prepareDeadline(String args) throws PenguinException {
        validateContainsDelimiter(args, "/by", "Noot noot <(`^´)>! Deadline task must include '/by' delimiter.");

        // Parse relevant fields
        String[] bySplit = splitAndValidate(args, "/by", "Noot noot <(`^´)>! "
                + "Please include a date and time deadline~");
        String description = bySplit[0].trim();
        String deadline = bySplit[1].trim();

        // Check that fields are not empty
        validateArgsNotEmpty(bySplit[0].trim(), "Noot noot <(`^´)>! The description of a "
                + "deadline task cannot be empty.");
        validateArgsNotEmpty(bySplit[1].trim(), "Noot noot <(`^´)>! The date and time deadline "
                + "cannot be empty!");

        return command.addTask(new Deadline(description, deadline));
    }

    private String prepareEvent(String args) throws PenguinException {
        validateContainsDelimiter(args, "/from", "Noot noot <(`^´)>! Event task must include '/from' delimiter.");
        validateContainsDelimiter(args, "/to", "Noot noot <(`^´)>! Event task must include '/to' delimiter.");

        // Parse relevant fields
        String[] fromSplit = splitAndValidate(args, "/from", "Noot noot <(`^´)>! Please include a start time (/from)~");
        String description = fromSplit[0].trim();
        String[] toSplit = splitAndValidate(fromSplit[1], "/to", "Noot noot <(`^´)>! Please include "
                + "an end time (/to)~");
        String fromTime = toSplit[0].trim();
        String toTime = toSplit[1].trim();

        // Check that fields are not empty
        validateArgsNotEmpty(description, "Noot noot <(`^´)>! The description of an event task cannot be empty.");
        validateArgsNotEmpty(fromTime, "Noot noot <(`^´)>! Please include a start time (/from)~");
        validateArgsNotEmpty(toTime, "Noot noot <(`^´)>! Please include an end time (/to)~");

        return command.addTask(new Event(description, fromTime, toTime));
    }

    private String prepareFind(String args) throws PenguinException {
        validateArgsNotEmpty(args, "Noot noot <(`^´)>! Please enter a task keyword~");
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

        return switch (action) {
        case "list" -> command.listTasks();
        case "mark" -> handleIndexedCommand(args, command::markTask);
        case "unmark" -> handleIndexedCommand(args, command::unmarkTask);
        case "delete" -> handleIndexedCommand(args, command::removeTask);
        case "todo" -> prepareTodo(args);
        case "deadline" -> prepareDeadline(args);
        case "event" -> prepareEvent(args);
        case "find" -> prepareFind(args);
        default -> throw new PenguinException("Noot noot <(`^´)>! Please enter a valid command~");
        };
    }
}
