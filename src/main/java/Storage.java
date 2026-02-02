import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Storage {
    private static final Path FILE_PATH = Paths.get("data", "Penguin.txt");

    public static void saveData(TaskList tasks) throws PenguinException{
        // Save list of tasks to /data/Penguin.txt
        try {
            // Create directory if it does not exist
            Files.createDirectories(FILE_PATH.getParent());
            try (BufferedWriter writer = Files.newBufferedWriter(FILE_PATH)) {
                for (Task t : tasks.getTasks()) {
                    writer.write(encode(t));
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            throw new PenguinException("Error saving data to disk!" + e.getMessage());
        }
    }

    public static ArrayList<Task> loadData() throws PenguinException {
        // Create new list if data file does not exist
        if (!Files.exists(FILE_PATH)) {
            return new ArrayList<>();
        }
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(FILE_PATH);

            for (String line : lines) {
                tasks.add(decode(line));
            }
            return tasks;
        } catch (IOException e) {
            throw new PenguinException("Error loading data from disk!");
        } catch (PenguinException e) {
            throw new PenguinException("Error saving data to disk! " + e.getMessage());
        }
    }

    private static String encode(Task t) {
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

    private static Task decode(String line) throws PenguinException {
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
            }
        } catch (Exception e) {
            throw new PenguinException("Error parsing line!");
        }
        return null;
    }
}
