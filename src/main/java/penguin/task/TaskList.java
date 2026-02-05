package penguin.task;

import java.util.ArrayList;

import penguin.exception.PenguinException;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public Task removeTask(int index) throws PenguinException {
        checkTaskExists(index);
        return tasks.remove(index);
    }

    public Task markTask(int index) throws PenguinException {
        checkTaskExists(index);
        Task t = tasks.get(index);
        t.markDone();
        return t;
    }

    public Task unmarkTask(int index) throws PenguinException {
        checkTaskExists(index);
        Task t = tasks.get(index);
        t.markUndone();
        return t;
    }

    public void checkTaskExists(int index) throws PenguinException {
        if (index < 0 || index >= tasks.size()) {
            throw new PenguinException("This task does not exist in my list!");
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getSize() {
        return tasks.size();
    }

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
