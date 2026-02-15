package penguin.commands;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

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

    private void checkDuplicates(Task newTask) throws PenguinException {
        for (Task t : taskList.getTasks()) {
            if (Objects.equals(t.getDescription(), newTask.getDescription())) {
                throw new PenguinException("A task with this name already exists!");
            }
        }
    }

    /**
     * Adds a task to the task list and saves the updated list to storage.
     *
     * @param t the task to be added
     * @throws PenguinException if an error occurs while saving task data
     */
    public String addTask(Task t) throws PenguinException {
        checkDuplicates(t);
        taskList.addTask(t);
        storage.saveData(taskList);
        return ui.showTaskAdded(t, taskList);
    }

    /**
     * Removes a task at the specified index from the task list and saves
     * the updated list to storage.
     *
     * @param index the index of the task to be removed
     * @throws PenguinException if the index is invalid or saving fails
     */
    public String removeTask(int index) throws PenguinException {
        Task t = taskList.removeTask(index);
        storage.saveData(taskList);
        return ui.showTaskRemoved(t, taskList);
    }

    /**
     * Displays all tasks currently in the task list.
     */
    public String listTasks() {
        return ui.showTaskList(taskList);
    }

    /**
     * Marks the task at the specified index as completed and saves
     * the updated list to storage.
     *
     * @param index the index of the task to be marked
     * @throws PenguinException if the index is invalid or saving fails
     */
    public String markTask(int index) throws PenguinException {
        Task t = taskList.markTask(index);
        storage.saveData(taskList);
        return ui.showTaskMarked(t);
    }

    /**
     * Unmarks the task at the specified index as not completed and saves
     * the updated list to storage.
     *
     * @param index the index of the task to be unmarked
     * @throws PenguinException if the index is invalid or saving fails
     */
    public String unmarkTask(int index) throws PenguinException {
        Task t = taskList.unmarkTask(index);
        storage.saveData(taskList);
        return ui.showTaskUnmarked(t);
    }

    /**
     * Finds one or more tasks in the task list that match
     * the specified keyword(s)
     * @param keyword the keyword present in the desired task
     */
    public String findTasks(String keyword) {
        ArrayList<Task> matchingTasks = taskList.getTasks().stream()
                .filter(t -> t.getDescription().contains(keyword))
                .collect(Collectors.toCollection(ArrayList::new));

        return ui.showMatchingTasks(new TaskList(matchingTasks));
    }
}
