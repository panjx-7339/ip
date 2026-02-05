package penguin.commands;

import java.util.ArrayList;

import penguin.exception.PenguinException;
import penguin.storage.Storage;
import penguin.task.Task;
import penguin.task.TaskList;
import penguin.ui.Ui;

/**
 * The {@code Command} class encapsulates operations that modify or query
 * the task list.
 * <p>
 * It acts as an intermediary between the parser and core components
 * such as {@link TaskList}, {@link Storage}, and {@link Ui}, ensuring
 * that task updates are reflected in both persistent storage and user
 * output.
 */
public class Command {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * Constructs a {@code Command} object with the given task list and storage.
     *
     * @param tasks   the task list to be operated on
     * @param storage the {@code Storage} object used for persisting task data
     */
    public Command(TaskList tasks, Storage storage) {
        taskList = tasks;
        ui = new Ui();
        this.storage = storage;
    }

    /**
     * Adds a task to the task list and saves the updated list to storage.
     *
     * @param t the task to be added
     * @throws PenguinException if an error occurs while saving task data
     */
    public void addTask(Task t) throws PenguinException {
        taskList.addTask(t);
        ui.showTaskAdded(t, taskList);
        storage.saveData(taskList);
    }

    /**
     * Removes a task at the specified index from the task list and saves
     * the updated list to storage.
     *
     * @param index the index of the task to be removed
     * @throws PenguinException if the index is invalid or saving fails
     */
    public void removeTask(int index) throws PenguinException {
        Task t = taskList.removeTask(index);
        ui.showTaskRemoved(t, taskList);
        storage.saveData(taskList);
    }

    /**
     * Displays all tasks currently in the task list.
     */
    public void listTasks() {
        ui.showTaskList(taskList);
    }

    /**
     * Marks the task at the specified index as completed and saves
     * the updated list to storage.
     *
     * @param index the index of the task to be marked
     * @throws PenguinException if the index is invalid or saving fails
     */
    public void markTask(int index) throws PenguinException {
        Task t = taskList.markTask(index);
        ui.showTaskMarked(t);
        storage.saveData(taskList);
    }

    /**
     * Unmarks the task at the specified index as not completed and saves
     * the updated list to storage.
     *
     * @param index the index of the task to be unmarked
     * @throws PenguinException if the index is invalid or saving fails
     */
    public void unmarkTask(int index) throws PenguinException {
        Task t = taskList.unmarkTask(index);
        ui.showTaskUnmarked(t);
        storage.saveData(taskList);
    }

    public void findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task t : taskList.getTasks()) {
            if (t.getDescription().contains(keyword)) {
                matchingTasks.add(t);
            }
        }
        ui.showMatchingTasks(new TaskList(matchingTasks));
    }
}
