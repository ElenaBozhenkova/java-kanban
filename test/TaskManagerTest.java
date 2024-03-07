import managers.*;
import tasks.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

class TaskManagerTest {
    private TaskManager taskManager;

    @BeforeEach
    void init() {
        taskManager = Managers.getDefaultTaskManager();
    }

    @Test
    void addNewTask_shouldCheckTasksEqualsIfEqualsTheirId() {
        Task task = new Task("Задача 1", "Описание 1");
        taskManager.addNewTask(task);
        final int taskId = task.getId();

        final Task savedTask = taskManager.getTask(taskId);

        assertEquals(task,savedTask, "Задачи не совпадают.");
    }

    @Test
    void addNewEpic_shouldCheckEpicsEqualsIfEqualsTheirId() {
        Epic epic = new Epic("Эпик 1","Описание эпика 1");
        taskManager.addNewEpic(epic);
        final int epicId = epic.getId();

        final Task savedEpic = taskManager.getEpic(epicId);

        assertEquals(epic,savedEpic, "Задачи не совпадают.");
    }

    @Test
    void addNewSubTask_shouldCheckSubTasksEqualsIfEqualsTheirId() {
        Epic epic = new Epic("Эпик 2","Описание эпика 2");
        taskManager.addNewEpic(epic);
        SubTask subTask = new SubTask("Подзадача 1","Описание подзадачи 1", TaskStatus.NEW, epic.getId());
        taskManager.addNewSubTask(subTask);
        final int subTaskId = subTask.getId();

        final Task savedSubTask = taskManager.getSubTask(subTaskId);

        assertEquals(subTask,savedSubTask, "Задачи не совпадают.");
    }

    @Test
    void addNewEpic_AddEpicAsSubTaskInHimself() {
        Epic epic = new Epic("Эпик 3","Описание эпика 3");
        taskManager.addNewEpic(epic);
        SubTask subTask = new SubTask(epic.getId(),"Подзадача 2","Описание подзадачи 2",TaskStatus.NEW, epic.getId());
        taskManager.addNewSubTask(subTask);

        assertNotEquals(epic.getId(),subTask.getId(), "Эпик добавлен сам в себя");
    }

    @Test
    void managersReturnInitializedInMemoryTaskManager() {
        assertNotNull(taskManager);
    }
    @Test
    void addNewTask_shouldGenerateIdAndSaveTask() {
        Task newTask = new Task("Задача 2","Описание 2",TaskStatus.NEW);
        taskManager.addNewTask(newTask);

        ArrayList<Task> tasksList = taskManager.getAllTask();

        assertEquals(1,tasksList.size(), "Задача не добавлена.");
    }
    @Test
    void addNewTask_shouldReturnTask() {
        Task expected = new Task(1,"Задача 2","Описание 2",TaskStatus.NEW);
        Task newTask = new Task("Задача 2","Описание 2",TaskStatus.NEW);

        taskManager.addNewTask(newTask);
        Task actual = taskManager.getTask(1);

        assertEquals(expected,actual, "Задача не добавлена.");
    }

    @Test
    void addNewEpic_shouldGenerateIdAndSaveEpic() {
        Epic newEpic = new Epic("Эпик 4","Описание эпика 4");
        taskManager.addNewEpic(newEpic);

        ArrayList<Epic> epicList = taskManager.getAllEpic();

        assertEquals(1,epicList.size(), "Эпик не добавлен.");
    }
    @Test
    void addNewEpic_shouldReturnEpic() {
        Epic expected = new Epic(1,"Эпик 4","Описание эпика 4");
        Epic newEpic = new Epic("Эпик 4","Описание эпика 4");

        taskManager.addNewEpic(newEpic);
        Task actual = taskManager.getEpic(1);

        assertEquals(expected,actual, "Эпик не добавлен.");
    }

    @Test
    void addNewSubTask_shouldGenerateIdAndSaveSubTask() {
        Epic newEpic = new Epic("Эпик 6","Описание эпика 6");
        taskManager.addNewEpic(newEpic);
        SubTask newSubTask = new SubTask("Подзадача 7","Описание подзадачи 7",TaskStatus.NEW, newEpic.getId());

        taskManager.addNewSubTask(newSubTask);

        ArrayList<SubTask> subTasksList = taskManager.getAllSubTasks();

        assertEquals(1,subTasksList.size(), "Подзадача не добавлена.");
    }
    @Test
    void addNewSubTask_shouldReturnSubTask() {
        SubTask expected = new SubTask(2,"Подзадача 4","Описание подзадачи 4",TaskStatus.NEW,1);
        Epic newEpic = new Epic("Эпик 5","Описание эпика 5");
        taskManager.addNewEpic(newEpic);
        SubTask newSubTask = new SubTask("Подзадача 4","Описание подзадачи 4",TaskStatus.NEW, newEpic.getId());

        taskManager.addNewSubTask(newSubTask);
        Task actual = taskManager.getSubTask(2);

        assertEquals(expected,actual, "Подзадача не добавлена.");
    }

    @Test
    void addNewTask_AssignedIdTaskNotEqualsGenerateIdTask() {
        Task asssignedIdTask = new Task(1,"Задача 8", "Описание задачи 8",TaskStatus.NEW);
        taskManager.addNewTask(asssignedIdTask);
        Task generateIdTask = new Task("Задача 8", "Описание задачи 8");
        taskManager.addNewTask(generateIdTask);

        assertNotEquals(asssignedIdTask.getId(),generateIdTask.getId(),"У задач одинаковый ID");
    }

    @Test
    void addNewEpic_AssignedIdEpicNotEqualsGenerateIdEpic() {
        Epic asssignedIdEpic = new Epic(1,"Эпик 9", "Описание эпика 9");
        taskManager.addNewEpic(asssignedIdEpic);
        Epic generateIdEpic = new Epic("Эпик 9", "Описание эпика 9");
        taskManager.addNewTask(generateIdEpic);

        assertNotEquals(asssignedIdEpic.getId(),generateIdEpic.getId(),"У эпиков одинаковый ID");
    }

    @Test
    void addNewSubTask_AssignedIdSubTaskNotEqualsGenerateIdSubTask() {
        Epic epic = new Epic("Эпик 10", "Описание эпика 10");
        taskManager.addNewEpic(epic);
        SubTask asssignedIdSubTask = new SubTask(2,"Подзадача 1","Описание подзадачи 1",TaskStatus.NEW, epic.getId());
        taskManager.addNewTask(asssignedIdSubTask);
        SubTask generateIdSubTask = new SubTask("Подзадача 1","Описание подзадачи 1",TaskStatus.NEW, epic.getId());

        assertNotEquals(asssignedIdSubTask.getId(),generateIdSubTask.getId(),"У подзадач одинаковый ID");
    }

    @Test
    void getHistory_shouldSaveOldVersionTask(){
        Task task = new Task("Задача1","Описание1");
        taskManager.addNewTask(task);
        taskManager.getTask(task.getId());
        Task updatedTask = new Task(task.getId(),"Задача2","Описание2",TaskStatus.IN_PROGRESS);
        taskManager.updateTask(updatedTask);
        taskManager.getTask(updatedTask.getId());

        Task[] history = new Task[2];
        history[0] = task;
        history[1] = new Task(1,"Задача2","Описание2",TaskStatus.IN_PROGRESS);
        Task[] getHistory = taskManager.getHistory().toArray(new Task[2]);

        assertArrayEquals(history,getHistory,"Массивы не равны");
    }
}