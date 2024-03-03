import java.util.ArrayList;

public interface TaskManager {
    Task addNewTask(Task task);

    Epic addNewEpic(Epic epic);

    SubTask addNewSubTask(SubTask subTask);

    ArrayList<Task> getAllTask();

    ArrayList<Epic> getAllEpic();

    ArrayList<SubTask> getAllSubTasks();

    ArrayList<SubTask> getAllEpicSubTasks(Epic epic);

    void removeAll();

    void removeAllTasks();

    void removeAllEpics();

    void removeAllSubTasks();

    Boolean removeTask(int id);

    Boolean removeEpic(int id);

    Boolean removeSubTask(int id);

    Task getTask(int id);

    Task getEpic(int id);

    Task getSubTask(int id);

    Boolean updateTask(Task updatedTask);

    Boolean updateEpic(Epic updatedEpic);

    Boolean updateSubTask(SubTask updatedSubTask);
}
