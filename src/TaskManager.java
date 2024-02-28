import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    protected HashMap<Integer,Task> tasks;
    protected HashMap<Integer,Epic> epics;
    protected HashMap<Integer,SubTask> subTasks;
    private int id = 0;

    public TaskManager() {
        this.tasks = new HashMap<>();
        this.epics = new HashMap<>();
        this.subTasks = new HashMap<>();
    }
    private int generateId() {
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
            subTask.setId(generateId());
            subTasks.put(subTask.getId(), subTask);
            Epic epic = epics.get(subTask.getEpicId());
            epic.addSubTask(subTask.getId());
            updateEpicStatus(epic);
            return subTask;
        }
        return null;
    }
    public ArrayList<Task> getAllTask() {
        return new ArrayList<>(tasks.values());
    }
    public ArrayList<Epic>  getAllEpic() {
        return new ArrayList<>(epics.values());
    }
    public ArrayList<SubTask> getAllSubTasks() {
        return new ArrayList<>(subTasks.values());
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
        for (Epic epic : epics.values()) {
            epic.removeEpicSubTasks();
            updateEpicStatus(epic);
        }
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
            epics.remove(id);
            return true;
        }
        return false;
    }
    public Boolean removeSubTask(int id) {
        if (subTasks.containsKey(id)) {
            SubTask subTask = subTasks.get(id);
            Epic epic = epics.get(subTask.getEpicId());
            epic.removeEpicSubTasksItem(Integer.valueOf(id));
            subTasks.remove(id);
            updateEpicStatus(epic);
            return true;
        }
        return false;
    }
    public Task getTask(int id) {
        return tasks.get(id);
    }
    public Task getEpic(int id) {
        return epics.get(id);
    }
    public Task getSubTask(int id) {
        return subTasks.get(id);
    }
    public Boolean updateTask(Task updatedTask) {
        if (tasks.containsKey(updatedTask.getId())) {
            tasks.put(updatedTask.getId(),updatedTask);
            return true;
        }
        return false;
    }
    public Boolean updateEpic(Epic updatedEpic) {
        Epic currentEpic = epics.get(updatedEpic.getId());

        if (epics.containsKey(updatedEpic.getId())) {
            currentEpic.setTaskName(updatedEpic.getTaskName());
            currentEpic.setTaskDescription(updatedEpic.getTaskDescription());
            return true;
        }
        return false;
    }
    public Boolean updateSubTask(SubTask updatedSubTask) {

        if (subTasks.containsKey(updatedSubTask.getId())) {
            SubTask currentSubTask = subTasks.get(updatedSubTask.getId());
            if(currentSubTask.getEpicId() == updatedSubTask.getEpicId()) {
                subTasks.put(updatedSubTask.getId(), updatedSubTask);
                Epic epic = epics.get(updatedSubTask.getEpicId());
                updateEpicStatus(epic);
                return true;
            }
        }
        return false;
    }
    private void updateEpicStatus(Epic epic) {
        ArrayList<SubTask> allEpicSubTasks = getAllEpicSubTasks(epic);
        epic.checkSubTasksStatus(allEpicSubTasks);
    }
}
