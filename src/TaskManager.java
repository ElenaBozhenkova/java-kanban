import java.util.ArrayList;
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
    public void printAllEpic() {
        if (epics.isEmpty())
            System.out.println("Ваш список эпиков пока пустой");

        for (int id : epics.keySet()) {
            System.out.print("id=" + id + " ");
            Epic epic = epics.get(id);
            System.out.println(epic);
        }
    }
    public void printAllSubTasks() {
        if (subTasks.isEmpty())
            System.out.println("Ваш список подзадач пока пустой");

        for (int id : subTasks.keySet()) {
            System.out.print("id=" + id + " ");
            SubTask subTask = subTasks.get(id);
            System.out.println(subTask);
        }
    }
    public void removeAllTasks() {
        tasks.clear();
        epics.clear();
        subTasks.clear();
    }
    public Task removeTask(int id) {
        if (tasks.containsKey(id)) {
            Task task = tasks.get(id);
            tasks.remove(id);
            return task;
        }
        return null;
    }
    public Epic removeEpic(int id) {
        if (epics.containsKey(id)) {
            Epic epic = epics.get(id);
            ArrayList<SubTask> epicSubTasks = epic.getEpicSubTasks();
            for (SubTask subTask : epicSubTasks) {
                subTasks.remove(subTask);
            }
            epics.remove(id);
            return epic;
        }
        return null;
    }
    public SubTask removeSubTask(int id) {
        if (subTasks.containsKey(id)) {
            SubTask subTask = subTasks.get(id);
            Epic epic = epics.get(subTask.getEpicId());
            ArrayList<SubTask> epicSubTasks = epic.getEpicSubTasks();
            epicSubTasks.remove(subTask);
            subTasks.remove(id);
            return subTask;
        }
        return null;
    }
    public Task getTask(int id) {
        if (tasks.containsKey(id)) {
            return tasks.get(id);
        }
        return null;
    }
    public Task getEpic(int id) {
        if (epics.containsKey(id)) {
            return epics.get(id);
        }
        return null;
    }

}
