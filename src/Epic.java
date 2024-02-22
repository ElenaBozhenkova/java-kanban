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
    Epic(int id, String taskName, String taskDescription) {
        super(taskName,taskDescription);
        this.setId(id);
        this.taskType = TaskType.EPIC;
        epicSubTasks = new ArrayList<>();
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
    public void removeEpicSubTasks() {
        epicSubTasks.clear();
    }

    public TaskStatus checkSubTasksStatus(ArrayList<SubTask> allEpicSubTasks) {//нужно объединить методы по статусам, чтобы не гонять один и тот же список дважды, а так же дополнить его логикой определения статуса IN_PROGRESS
        boolean isNew = true;
        boolean isDone = true;

        for (SubTask subTask : allEpicSubTasks) {
            if(!subTask.getTaskStatus().equals(TaskStatus.NEW)) {
                isNew = false;
            }
            if(!subTask.getTaskStatus().equals(TaskStatus.DONE)){
                isDone = false;
            }
        }
        if (isNew) {
            setTaskStatus(TaskStatus.NEW);
        } else if(isDone) {
            setTaskStatus(TaskStatus.DONE);
        } else {
            setTaskStatus(TaskStatus.IN_PROGRESS);
        }
        return getTaskStatus();
    }

    @Override
    public String toString() {
        return "Epic{" +
                "id=" + id +
                ", Name='" + taskName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", taskStatus=" + taskStatus +
                ", taskType=" + taskType +
                ", epicSubTasks=" + epicSubTasks +
                '}';
    }
}
