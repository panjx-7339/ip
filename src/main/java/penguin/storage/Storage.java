package penguin.storage;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
        // Save list of tasks to /data/Penguin.txt
        try {
            // Create directory if it does not exist
            Files.createDirectories(filePath.getParent());
            try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
                for (Task t : tasks.getTasks()) {
                    writer.write(encode(t));
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            throw new PenguinException("Error saving data to disk!" + e.getMessage());
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
        // Create new list if data file does not exist
        if (!Files.exists(filePath)) {
            throw new PenguinException("File does not exist.");
        }
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(filePath);

            for (String line : lines) {
                tasks.add(decode(line));
            }
            return tasks;
        } catch (Exception e) {
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
        String isDone = t.isDone() ? "1" : "0";
        String description = t.getDescription();
        String taskType;
        if (t instanceof ToDo) {
            taskType = "T";
            return taskType + " | " + isDone + " | " + description;
        } else if (t instanceof Deadline) {
            taskType = "D";
            return taskType + " | " + isDone + " | " + description + " | " + ((Deadline) t).getBy();
        } else if (t instanceof Event) {
            taskType = "E";
            return taskType + " | " + isDone + " | " + description + " | " + ((Event) t).getFrom() + " | "
                    + ((Event) t).getTo();
        }
        return "";
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

            String taskType = parts[0];
            boolean isDone = Objects.equals(parts[1], "1");
            String description = parts[2];
            switch (taskType) {
            case "T": {
                Task t = new ToDo(description);
                if (isDone) {
                    t.markDone();
                }
                return t;
            }
            case "D": {
                String by = parts[3];
                // Change by string to correct LocalDateTime format
                LocalDateTime dateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("d MMM yyyy ha"));
                by = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                Task t = new Deadline(description, by);
                if (isDone) {
                    t.markDone();
                }
                return t;
            }
            case "E": {
                String from = parts[3];
                String to = parts[4];
                Task t = new Event(description, from, to);
                if (isDone) {
                    t.markDone();
                }
                return t;
            }
            default:
                return null;
            }
        } catch (Exception e) {
            throw new PenguinException("Error parsing line!");
        }
    }
}
