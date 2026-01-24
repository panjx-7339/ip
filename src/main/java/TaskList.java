import java.util.ArrayList;

public class TaskList {
    protected static ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(Task t) {
        tasks.add(t);
    }

    public Task markTask(int index) {
        Task t = tasks.get(index);
        t.markDone();
        return t;
    }

    public Task UnmarkTask(int index) {
        Task t = tasks.get(index);
        t.markUndone();
        return t;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            Task t = tasks.get(i-1);
            sb.append(i).append(".").append(t);
            if (i < tasks.size()) sb.append("\n");
        }
        return sb.toString();
    }

}
