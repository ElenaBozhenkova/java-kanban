import java.util.ArrayList;
import java.util.Objects;

public class Epic extends Task {
    private final ArrayList<Integer> epicSubTasks;

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

    public ArrayList<Integer> getEpicSubTasks() {
        return epicSubTasks;
    }

    public void addSubTask(SubTask subTask) {
        epicSubTasks.add(subTask.getId());
    }
    public Boolean removeEpicSubTasksItem(int id) {
        for(Integer item : epicSubTasks ) {
            if (Objects.equals(id, item)){
                epicSubTasks.remove(item);
                return true;
            }
        }
        return false;
    }


    public boolean checkSubTasksStatusIsDONE() {
        for (SubTask subTask : epicSubTasks) {
            if(!subTask.getTaskStatus().equals(TaskStatus.DONE)){
                return false;
            }
        }
        return true;
    }
    public boolean checkSubTasksStatusIsNEW() {
        for (SubTask subTask : epicSubTasks) {
            if (!subTask.getTaskStatus().equals(TaskStatus.NEW)){
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", taskStatus=" + taskStatus +
                ", taskType=" + taskType +
                ", epicSubTasks=" + epicSubTasks +
                '}';
    }
}
