package penguin.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import penguin.exception.PenguinException;
import penguin.task.Task;

public class StorageTest {
    @TempDir
    Path tempDir;

    @Test
    void loadData_fileExists_success() throws Exception {
        // Set up file and Storage instance
        Path filePath = tempDir.resolve("data").resolve("Penguin.txt");
        Files.createDirectories(filePath.getParent());

        List<String> fileLines = List.of(
                "T | 0 | read book",
                "D | 0 | write report | 1 May 2026 2pm",
                "E | 1 | project meeting | Monday | Thursday"
        );
        Files.write(filePath, fileLines);

        Storage storage = new Storage(filePath);

        // Access private decode()
        Method decodeMethod = Storage.class.getDeclaredMethod("decode", String.class);
        decodeMethod.setAccessible(true);

        // Expected tasks produced by decode()
        ArrayList<Task> expected = new ArrayList<>();
        for (String line : fileLines) {
            expected.add((Task) decodeMethod.invoke(storage, line));
        }

        // Execute method
        ArrayList<Task> actual = storage.loadData();

        // Expect tasks loaded to match
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).toString(), actual.get(i).toString());
        }
    }

    @Test
    void loadData_fileDoesNotExist_throwsPenguinExceptionWithCorrectMessage() {
        // Set up file and Storage instance
        Path nonExistentFile = tempDir.resolve("does_not_exist.txt");
        Storage storage = new Storage(nonExistentFile);

        // Expect exception to be thrown
        PenguinException exception = org.junit.jupiter.api.Assertions.assertThrows(
                PenguinException.class,
                storage::loadData
        );

        assertEquals("File does not exist.", exception.getMessage());
    }

    @Test
    void loadData_invalidFilePath_throwsPenguinExceptionWithCorrectMessage() throws Exception {
        // Set up invalid file path and Storage instance
        Path directoryPath = tempDir.resolve("data");
        Files.createDirectories(directoryPath); // exists, but is a directory

        Storage storage = new Storage(directoryPath);

        // Expect exception to be thrown
        PenguinException exception = org.junit.jupiter.api.Assertions.assertThrows(
                PenguinException.class,
                storage::loadData
        );

        assertEquals("Error loading data from disk!", exception.getMessage());
    }

}


