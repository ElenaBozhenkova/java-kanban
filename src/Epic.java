import java.util.ArrayList;

public class Epic extends Task {
    private final ArrayList<SubTask> epicSubTasks;

    Epic(String taskName, String taskDescription) {
        super(taskName,taskDescription);
        epicSubTasks = new ArrayList<>();
        this.taskType = TaskType.EPIC;
        this.taskStatus = TaskStatus.NEW;
    }

    Epic(Integer id,String taskName, String taskDescription, TaskStatus taskStatus) {
        super(id,taskName,taskDescription,taskStatus);
        this.taskType = TaskType.EPIC;
        epicSubTasks = new ArrayList<>();
        this.id = id;
        this.taskStatus = taskStatus;
    }

    public ArrayList<SubTask> getEpicSubTasks() {
        return epicSubTasks;
    }

    public void addSubTask(SubTask subTask) {
        epicSubTasks.add(subTask);
    }

    public boolean checkSubTasksStatusIsDONE() {
        for (SubTask subTask : epicSubTasks) {
            if(!subTask.getTaskStatus().equals(TaskStatus.DONE)){
                return false;
            }
        }
        return true;
    }

}
