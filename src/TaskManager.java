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
    public ArrayList<Task> getAllTask() {
        ArrayList<Task> listOfAllTasks= new ArrayList<>();
        for (Task task : tasks.values()) {//???????можно ли здесь воспользоваться listOfAllTasks.addAll(tasks.values());
            listOfAllTasks.add(task);
        }
        return listOfAllTasks;
    }
    public ArrayList<Epic>  getAllEpic() {
        ArrayList<Epic> listOfAllEpics= new ArrayList<>();
        for (Epic epic : epics.values()) {
            listOfAllEpics.add(epic);
        }
        return listOfAllEpics;
    }
    public ArrayList<SubTask> getAllSubTasks() {
        ArrayList<SubTask> listOfAllSubTasks= new ArrayList<>();
        for (SubTask subTask : subTasks.values()) {
            listOfAllSubTasks.add(subTask);
        }
        return listOfAllSubTasks;
    }
    public ArrayList<SubTask> getAllEpicSubTasks(Epic epic) {
        ArrayList<Integer> epicSubTasksId = epic.getEpicSubTasks();
        ArrayList<SubTask> epicSubTasks = new ArrayList<>();
        for (Integer subTaskId : epicSubTasksId) {
            epicSubTasks.add(subTasks.get(subTaskId));
        }
        return epicSubTasks;
    }
    public void removeAll() {
        tasks.clear();
        epics.clear();
        subTasks.clear();
    }
    public void removeAllTasks() {
        tasks.clear();
    }
    public void removeAllEpics() {
        epics.clear();
        subTasks.clear();
    }
    public void removeAllSubTasks() {
        subTasks.clear();
    }
    public Boolean removeTask(int id) {
        if (tasks.containsKey(id)) {
            tasks.remove(id);
            return true;
        }
        return false;
    }
    public Boolean removeEpic(int id) {
        if (epics.containsKey(id)) {
            Epic epic = epics.get(id);
            ArrayList<Integer> epicSubTasks = epic.getEpicSubTasks();
            for (Integer subTaskId : epicSubTasks) {
                subTasks.remove(subTaskId);
            }
            epic.removeEpicSubTasks();
            epics.remove(id);
            return true;
        }
        return false;
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
