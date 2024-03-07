package managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tasks.*;

public class InMemoryTaskManager implements TaskManager {
    protected Map<Integer, Task> tasks = new HashMap<>();
    protected Map<Integer, Epic> epics = new HashMap<>();
    protected Map<Integer, SubTask> subTasks = new HashMap<>();
    private int id = 0;
    private HistoryManager historyManager;

    public InMemoryTaskManager(HistoryManager historyManager) {
        this.historyManager = historyManager;
    }

    private int generateId() {
        return ++id;
    }
    @Override
    public Task addNewTask(Task task) {
        task.setId(generateId());
        tasks.put(task.getId(),task);
        return task;
    }
    @Override
    public Epic addNewEpic(Epic epic) {
        epic.setId(generateId());
        epics.put(epic.getId(),epic);
        return epic;
    }
    @Override
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
    @Override
    public ArrayList<Task> getAllTask() {
        return new ArrayList<>(tasks.values());
    }
    @Override
    public ArrayList<Epic>  getAllEpic() {
        return new ArrayList<>(epics.values());
    }
    @Override
    public ArrayList<SubTask> getAllSubTasks() {
        return new ArrayList<>(subTasks.values());
    }
    @Override
    public ArrayList<SubTask> getAllEpicSubTasks(Epic epic) {
        ArrayList<Integer> epicSubTasksId = epic.getEpicSubTasks();
        ArrayList<SubTask> epicSubTasks = new ArrayList<>();
        for (Integer subTaskId : epicSubTasksId) {
            epicSubTasks.add(subTasks.get(subTaskId));
        }
        return epicSubTasks;
    }
    @Override
    public void removeAll() {
        tasks.clear();
        epics.clear();
        subTasks.clear();
    }
    @Override
    public void removeAllTasks() {
        tasks.clear();
    }
    @Override
    public void removeAllEpics() {
        epics.clear();
        subTasks.clear();
    }
    @Override
    public void removeAllSubTasks() {
        subTasks.clear();
        for (Epic epic : epics.values()) {
            epic.removeEpicSubTasks();
            updateEpicStatus(epic);
        }
    }
    @Override
    public Boolean removeTask(int id) {
        if (tasks.containsKey(id)) {
            tasks.remove(id);
            return true;
        }
        return false;
    }
    @Override
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
    @Override
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
    @Override
    public Task getTask(int id) {
        if (tasks.containsKey(id)) {
            historyManager.add(tasks.get(id));
        }
        return tasks.get(id);
    }
    @Override
    public Task getEpic(int id) {
        if (epics.containsKey(id)) {
            historyManager.add(epics.get(id));
        }
        return epics.get(id);
    }
    @Override
    public Task getSubTask(int id) {
        if (subTasks.containsKey(id)) {
            historyManager.add(subTasks.get(id));
        }
        return subTasks.get(id);
    }
    @Override
    public Boolean updateTask(Task updatedTask) {
        if (tasks.containsKey(updatedTask.getId())) {
            tasks.put(updatedTask.getId(),updatedTask);
            return true;
        }
        return false;
    }
    @Override
    public Boolean updateEpic(Epic updatedEpic) {
        Epic currentEpic = epics.get(updatedEpic.getId());

        if (epics.containsKey(updatedEpic.getId())) {
            currentEpic.setTaskName(updatedEpic.getTaskName());
            currentEpic.setTaskDescription(updatedEpic.getTaskDescription());
            return true;
        }
        return false;
    }
    @Override
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

    public List<Task> getHistory() {
        return historyManager.getHistory();
    }
}
