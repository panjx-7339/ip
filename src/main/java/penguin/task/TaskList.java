package penguin.task;

import java.util.ArrayList;

import penguin.exception.PenguinException;

/**
 * The {@code TaskList} class represents a collection of tasks.
 * <p>
 * It provides operations to add, remove, mark, unmark, and retrieve tasks
 * while ensuring index validity.
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    /**
     * Constructs a {@code TaskList} with an existing list of tasks.
     *
     * @param tasks the list of tasks to initialise this task list with
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param t the task to be added
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Removes the task at the specified index.
     *
     * @param index the index of the task to be removed
     * @return the removed task
     * @throws PenguinException if the index is invalid
     */
    public Task removeTask(int index) throws PenguinException {
        checkTaskExists(index);
        return tasks.remove(index);
    }

    /**
     * Marks the task at the specified index as completed.
     *
     * @param index the index of the task to be marked
     * @return the marked task
     * @throws PenguinException if the index is invalid
     */
    public Task markTask(int index) throws PenguinException {
        checkTaskExists(index);
        Task t = tasks.get(index);
        t.markDone();
        return t;
    }

    /**
     * Marks the task at the specified index as not completed.
     *
     * @param index the index of the task to be unmarked
     * @return the unmarked task
     * @throws PenguinException if the index is invalid
     */
    public Task unmarkTask(int index) throws PenguinException {
        checkTaskExists(index);
        Task t = tasks.get(index);
        t.markUndone();
        return t;
    }

    /**
     * Checks whether a task exists at the specified index.
     *
     * @param index the index to be checked
     * @throws PenguinException if the index is out of bounds
     */
    public void checkTaskExists(int index) throws PenguinException {
        if (index < 0 || index >= tasks.size()) {
            throw new PenguinException("This task does not exist in my list!");
        }
    }

    /**
     * Returns the list of tasks.
     *
     * @return the {@link ArrayList} containing all tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return the number of tasks
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the string representation of the task list.
     * <p>
     * Each task is displayed on a new line with a one-based index.
     *
     * @return the formatted string representation of the task list
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            Task t = tasks.get(i - 1);
            sb.append(i).append(".").append(t);
            if (i < tasks.size()) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
