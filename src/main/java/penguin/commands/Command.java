package commands;

import exception.PenguinException;
import task.Task;
import task.TaskList;
import ui.Ui;

public class Command {
    private TaskList taskList;
    private Ui ui;

    public Command() {
        this.taskList = Penguin.taskList;
        ui = new Ui();
    }

    public void addTask(Task t) {
        taskList.addTask(t);
        ui.showTaskAdded();
        storage.saveData(taskList);
    }

    public void removeTask(int index) throws PenguinException {
        Task t = taskList.removeTask(index);
        ui.showTaskRemoved();
        storage.saveData(taskList);
    }

    public void listTasks() {
        ui.showTaskList();
    }

    public void markTask(int index) throws PenguinException {
        Task t = taskList.markTask(index);
        ui.showTaskMarked();
        storage.saveData(taskList);
    }

    public void unmarkTask(int index) throws PenguinException {
        Task t = taskList.unmarkTask(index);
        ui.showTaskUnmarked();
        storage.saveData(taskList);
    }
}
