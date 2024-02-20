import java.util.HashMap;

public class TaskManager {
    protected HashMap<Integer,Task> tasks;
    protected HashMap<Integer,Epic> epics;
    protected HashMap<Integer,SubTask> subTasks;

    public TaskManager() {
        this.tasks = new HashMap<>();
        this.epics = new HashMap<>();
        this.subTasks = new HashMap<>();
    }
    public Task addNewTask(Task task) {
        task.setId(ID.generateId());
        tasks.put(task.getId(),task);
        return task;
    }
}
