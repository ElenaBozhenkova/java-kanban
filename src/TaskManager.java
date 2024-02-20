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
    public Epic addNewEpic(Epic epic) {
        epic.setId(ID.generateId());
        epics.put(epic.getId(),epic);
        return epic;
    }
    public SubTask addNewSubTask(Epic epic, SubTask subTask) {
        subTask.setId(ID.generateId());
        subTask.setEpicId(epic.getId());
        epic.addSubTask(subTask);
        subTasks.put(subTask.getId(), subTask);
        return subTask;
    }
    public void printAllTask() {
        if (tasks.isEmpty())
            System.out.println("Ваш список задач пока пустой");

        for (int id : tasks.keySet()) {
            System.out.print("id=" + id + " ");
            Task task = tasks.get(id);
            System.out.println(task);
        }
    }
}
