import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    protected HashMap<Integer,Task> tasks;
    protected HashMap<Integer,Epic> epics;
    protected HashMap<Integer,SubTask> subTasks;
    protected static int id = 0;

    public TaskManager() {
        this.tasks = new HashMap<>();
        this.epics = new HashMap<>();
        this.subTasks = new HashMap<>();
    }
    private static int generateId() {
        return ++id;
    }
    public Task addNewTask(Task task) {
        task.setId(generateId());
        tasks.put(task.getId(),task);
        return task;
    }
    public Epic addNewEpic(Epic epic) {
        epic.setId(generateId());
        epics.put(epic.getId(),epic);
        return epic;
    }
    public SubTask addNewSubTask(SubTask subTask) {
        if (epics.containsKey(subTask.getEpicId())) {
            subTasks.put(subTask.getId(), subTask);
            Epic epic = epics.get(subTask.getEpicId());
            epic.addSubTask(subTask);
            updateEpicStatus(epic);
            return subTask;
        }
        return null;
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
                subTasks.remove(subTask.getId());
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
    public Task getSubTask(int id) {
        if (subTasks.containsKey(id)) {
            return subTasks.get(id);
        }
        return null;
    }
    public Task updateTask(Task updatedTask) {
        Task currentTask = tasks.get(updatedTask.getId());

        if (currentTask != null && tasks.containsKey(updatedTask.getId())) {
            currentTask.setTaskName(updatedTask.getTaskName());
            currentTask.setTaskDescription(updatedTask.getTaskDescription());
            currentTask.setTaskStatus(updatedTask.getTaskStatus());
            tasks.put(updatedTask.getId(),currentTask);
            return updatedTask;
        }
        return null;
    }
    public Epic updateEpic(Epic updatedEpic) {
        Epic currentEpic = epics.get(updatedEpic.getId());

        if (currentEpic != null && tasks.containsKey(updatedEpic.getId())) {
            currentEpic.setTaskName(updatedEpic.getTaskName());
            currentEpic.setTaskDescription(updatedEpic.getTaskDescription());
            currentEpic.setTaskStatus(updatedEpic.getTaskStatus());
            epics.put(updatedEpic.getId(), currentEpic);
            return updatedEpic;
        }
        return null;
    }
    public SubTask updateSubTask(SubTask updatedSubTask) {
        SubTask currentSubTask = subTasks.get(updatedSubTask.getId());

        if (currentSubTask != null && tasks.containsKey(updatedSubTask.getId())) {
            currentSubTask.setTaskName(updatedSubTask.getTaskName());
            currentSubTask.setTaskDescription(updatedSubTask.getTaskDescription());
            currentSubTask.setTaskStatus(updatedSubTask.getTaskStatus());
            tasks.put(updatedSubTask.getId(), currentSubTask);
            return updatedSubTask;
        }
        return null;
    }
    public Task updateTaskStatus(Task task, TaskStatus newTaskStatus) {
        Task currentTask = tasks.get(task.getId());
        currentTask.setTaskStatus(newTaskStatus);
        return task;
    }
    public Task updateSubTaskStatus(SubTask subtask, TaskStatus newTaskStatus) {
        SubTask currentSubTask = subTasks.get(subtask.getId());
        Epic epic = epics.get(currentSubTask.getEpicId());
        ArrayList<SubTask> epicSubTasks = epic.getEpicSubTasks();
        currentSubTask.setTaskStatus(newTaskStatus);

        if ( epicSubTasks.isEmpty() || epic.checkSubTasksStatusIsNEW()) {
            epic.setTaskStatus(TaskStatus.NEW);

        } else if (epic.checkSubTasksStatusIsDONE()) {
            epic.setTaskStatus(TaskStatus.DONE);

        } else {
            epic.setTaskStatus(TaskStatus.IN_PROGRESS);
        }
        return subtask;
    }

}
