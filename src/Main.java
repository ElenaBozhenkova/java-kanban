public class Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");
        TaskManager taskManager = new TaskManager();

        Task task = new Task("Пойти на бал", "чтобы беспрепятственно пойти на бал нужно отделить " +
                "горох от риса");
        taskManager.addNewTask(task);
        Task task1 = new Task("Жениться на принцессе","Чтобы это исполнить нужно пробраться " +
                "через густой лес и поцеловать мертвую девушку");
        taskManager.addNewTask(task1);


        Epic epic = new Epic("Засунуть слона в холодильник", "Поместить слона в холодильник - " +
                "чрезвычайно сложная задача, учитывая несоответствие размеров");
        taskManager.addNewEpic(epic);
        SubTask subTask = new SubTask("Открыть холодильник","Убедиться, что холодильник работает");
        taskManager.addNewSubTask(epic,subTask);
        SubTask subTask2 = new SubTask("Засунуть слона","Использовать специальную технику " +
                "при необходимости");
        taskManager.addNewSubTask(epic,subTask2);
        SubTask subTask3 = new SubTask("Закрыть холодильник","Убедиться, что не прижат хвостик");
        taskManager.addNewSubTask(epic,subTask3);

        Epic epic1 = new Epic("Засунуть жирафа в холодильник","Продолжаем толкать животных " +
                "куда попало");
        taskManager.addNewEpic(epic1);
        SubTask subTask4 = new SubTask("Заменить слона жирафом","проделываем все манипуляции" +
                "как в ситуации со слоном");
        taskManager.addNewSubTask(epic1,subTask4);
        taskManager.printAllTask();
        taskManager.printAllEpic();
        taskManager.printAllSubTasks();
        System.out.println();

        taskManager.updateTaskStatus(task, TaskStatus.IN_PROGRESS);
        taskManager.updateSubTaskStatus(subTask4, TaskStatus.DONE);
        taskManager.updateSubTaskStatus(subTask3, TaskStatus.IN_PROGRESS);
        taskManager.printAllTask();
        taskManager.printAllEpic();
        taskManager.printAllSubTasks();
        System.out.println();

        taskManager.removeTask(task1.getId());
        taskManager.removeEpic(epic1.getId());
        taskManager.printAllTask();
        taskManager.printAllEpic();
        taskManager.printAllSubTasks();


    }
}
