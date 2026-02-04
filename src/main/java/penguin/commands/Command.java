package penguin.commands;

import penguin.exception.PenguinException;
import penguin.task.Task;
import penguin.task.TaskList;
import penguin.ui.Ui;
import penguin.storage.Storage;

public class Command {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    public Command(TaskList tasks, Storage storage) {
        taskList = tasks;
        ui = new Ui();
        this.storage = storage;
    }

    public void addTask(Task t) throws PenguinException {
        taskList.addTask(t);
        ui.showTaskAdded(t, taskList);
        storage.saveData(taskList);
    }

    public void removeTask(int index) throws PenguinException {
        Task t = taskList.removeTask(index);
        ui.showTaskRemoved(t, taskList);
        storage.saveData(taskList);
    }

    public void listTasks() {
        ui.showTaskList(taskList);
    }

    public void markTask(int index) throws PenguinException {
        Task t = taskList.markTask(index);
        ui.showTaskMarked(t);
        storage.saveData(taskList);
    }

    public void unmarkTask(int index) throws PenguinException {
        Task t = taskList.unmarkTask(index);
        ui.showTaskUnmarked(t);
        storage.saveData(taskList);
    }
}
