public class Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");
        TaskManager taskManager = new TaskManager();

        Task task1 = new Task("Задача1", "Описание1",TaskStatus.NEW);
        taskManager.addNewTask(task1);
        Task task2 = new Task("Задача2","Описание2",TaskStatus.IN_PROGRESS);
        taskManager.addNewTask(task2);

        Epic epic1 = new Epic("Эпик1", "Описание Эпика1");
        taskManager.addNewEpic(epic1);
        Epic epic2 = new Epic("Эпик2","Описание Эпика2");
        taskManager.addNewEpic(epic2);

        SubTask subTask1 = new SubTask("Подзадача1","Описание Подзадачи1", TaskStatus.NEW,epic1);
        taskManager.addNewSubTask(subTask1);
        SubTask subTask2 = new SubTask("Подзадача2","Описание Подзадачи2", TaskStatus.NEW,epic1);
        taskManager.addNewSubTask(subTask2);
        SubTask subTask3 = new SubTask("Подзадача3","Описание Подзадачи3", TaskStatus.NEW,epic2);
        taskManager.addNewSubTask(subTask3);

        System.out.println(taskManager.getAllTask());
        System.out.println(taskManager.getAllEpic());
        System.out.println(taskManager.getAllSubTasks());
        System.out.println(taskManager.getAllEpicSubTasks(epic1));
        System.out.println(taskManager.getAllEpicSubTasks(epic2));
        System.out.println();

        Task task3 = new Task(task2.getId(),"Задача3","Описание3",TaskStatus.DONE);
        taskManager.updateTask(task3);

        SubTask subTask4 = new SubTask(subTask3.getId(), "Подзадача4","Описание Подзадачи4", TaskStatus.IN_PROGRESS,epic2);
        taskManager.updateSubTask(subTask4);

        System.out.println(taskManager.getAllTask());
        System.out.println(taskManager.getAllEpic());
        System.out.println(taskManager.getAllSubTasks());
        System.out.println(taskManager.getAllEpicSubTasks(epic2));
        System.out.println();

        taskManager.removeSubTask(subTask3.getId());
        taskManager.removeEpic(epic1.getId());
        taskManager.removeTask(task1.getId());

        System.out.println(taskManager.getAllTask());
        System.out.println(taskManager.getAllEpic());
        System.out.println(taskManager.getAllSubTasks());
        System.out.println(taskManager.getAllEpicSubTasks(epic2));
        System.out.println();
    }
}
