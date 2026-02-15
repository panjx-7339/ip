package penguin.storage;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import penguin.exception.PenguinException;
import penguin.task.Deadline;
import penguin.task.Event;
import penguin.task.Task;
import penguin.task.TaskList;
import penguin.task.ToDo;

/**
 * The {@code Storage} class handles persistence of tasks to disk.
 * <p>
 * It is responsible for saving tasks to a file and loading tasks
 * from a file at a specified path.
 */
public class Storage {
    private Path filePath;

    /**
     * Constructs a {@code Storage} object using the given file path.
     *
     * @param filePath the path to the file used for storing task data
     */
    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the given task list to disk.
     * <p>
     * Each task is encoded into a single line before being written
     * to the storage file.
     *
     * @param tasks the {@link TaskList} containing tasks to be saved
     * @throws PenguinException if an I/O error occurs while saving data
     */
    public void saveData(TaskList tasks) throws PenguinException {
        try {
            createDirectoryIfNeeded();
            writeTasks(tasks);
        } catch (IOException e) {
            throw new PenguinException("Error saving data to disk!" + e.getMessage());
        }
    }

    private void createDirectoryIfNeeded() throws IOException {
        Files.createDirectories(filePath.getParent());
    }

    private void writeTasks(TaskList tasks) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Task t : tasks.getTasks()) {
                writer.write(encode(t));
                writer.newLine();
            }
        }
    }

    /**
     * Loads tasks from disk.
     * <p>
     * Each line in the storage file is decoded into a {@link Task}
     * object and added to a task list.
     *
     * @return an {@link ArrayList} of loaded tasks
     * @throws PenguinException if the file does not exist or an error
     *                          occurs while loading data
     */
    public ArrayList<Task> loadData() throws PenguinException {
        if (!Files.exists(filePath)) {
            throw new PenguinException("File does not exist.");
        }
        return parseTasks();
    }

    private ArrayList<Task> parseTasks() throws PenguinException {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            List<String> lines = Files.readAllLines(filePath);

            for (String line : lines) {
                tasks.add(decode(line));
            }
            return tasks;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new PenguinException("Error loading data from disk!");
        }
    }

    /**
     * Encodes a task into a string representation suitable for storage.
     *
     * @param t the task to be encoded
     * @return a string representation of the task
     */
    private String encode(Task t) {
        return t.encode();
    }

    private String formatDeadline(String by) {
        // Convert deadline to correct format
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("d MMM yyyy ha")
                .toFormatter(Locale.ENGLISH);
        LocalDateTime dateTime = LocalDateTime.parse(by, formatter);
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    private Task createTask(String taskType, String description, String[] parts) throws PenguinException {
        switch (taskType) {
        case "T": {
            return new ToDo(description);

        }
        case "D": {
            String by = parts[3];
            return new Deadline(description, formatDeadline(by));
        }
        case "E": {
            String from = parts[3];
            String to = parts[4];
            return new Event(description, from, to);
        }
        default:
            throw new PenguinException("Unknown task type");
        }
    }

    /**
     * Decodes a stored task string into a {@link Task} object.
     *
     * @param line the encoded task string
     * @return the decoded {@link Task}
     * @throws PenguinException if the line cannot be parsed correctly
     */
    private Task decode(String line) throws PenguinException {
        try {
            String[] parts = line.split(" \\| ");

            // Parse relevant fields
            String taskType = parts[0];
            boolean isDone = Objects.equals(parts[1], "1");
            String description = parts[2];

            Task t = createTask(taskType, description, parts);
            if (isDone) {
                t.markDone();
            }

            return t;
        } catch (Exception e) {
            String msg = "Error parsing line: " + e.getMessage();
            System.out.println(msg);
            throw new PenguinException(msg);
        }
    }
}
