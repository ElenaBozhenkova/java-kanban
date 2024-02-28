import java.util.ArrayList;
import java.util.Objects;

public class Epic extends Task {
    private final ArrayList<Integer> epicSubTasks;

    public Epic(String taskName, String taskDescription) {
        super(taskName,taskDescription);
        epicSubTasks = new ArrayList<>();
        this.taskType = TaskType.EPIC;
        this.taskStatus = TaskStatus.NEW;
    }
    public Epic(int id, String taskName, String taskDescription) {
        super(taskName,taskDescription);
        this.id = id;
        this.taskType = TaskType.EPIC;
        epicSubTasks = new ArrayList<>();
    }

    public Epic(Integer id,String taskName, String taskDescription, TaskStatus taskStatus) {
        super(id,taskName,taskDescription,taskStatus);
        this.taskType = TaskType.EPIC;
        epicSubTasks = new ArrayList<>();
        this.id = id;
        this.taskStatus = taskStatus;
    }

    public ArrayList<Integer> getEpicSubTasks() {
        return new ArrayList<>(epicSubTasks);
    }

    public void addSubTask(int id) {
        epicSubTasks.add(id);
    }
    public void removeEpicSubTasksItem(Integer id) {
        epicSubTasks.remove(id);
    }


    public void removeEpicSubTasks() {
        epicSubTasks.clear();
    }

    public void checkSubTasksStatus(ArrayList<SubTask> allEpicSubTasks) {
        if(allEpicSubTasks.isEmpty()) {
            this.taskStatus = TaskStatus.NEW;
        } else {
            boolean isNew = true;
            boolean isDone = true;

            for (SubTask subTask : allEpicSubTasks) {
                if(!subTask.getTaskStatus().equals(TaskStatus.NEW)) {
                    isNew = false;
                }
                if(!subTask.getTaskStatus().equals(TaskStatus.DONE)) {
                    isDone = false;
                }
            }
            if (isNew) {
                this.taskStatus = TaskStatus.NEW;
            } else if(isDone) {
                this.taskStatus = TaskStatus.DONE;
            } else {
            this.taskStatus = TaskStatus.IN_PROGRESS;
            }
        }
    }

    @Override
    public String toString() {
        return "Epic{" +
                "id=" + id +
                ", epicName='" + taskName + '\'' +
                ", epicDescription='" + taskDescription + '\'' +
                ", epicStatus=" + taskStatus +
                ", epicSubTasks=" + epicSubTasks.size() +
                '}';
    }
}
