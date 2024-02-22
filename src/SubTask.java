public class SubTask extends Task {
    private int epicId;

    SubTask(String taskName, String taskDescription, TaskStatus taskstatus, Epic epic) {
        super(taskName,taskDescription,taskstatus);
        this.epicId = epic.getId();
        this.taskType = TaskType.SUBTASK;
    }
    SubTask(Integer id,String taskName, String taskDescription,TaskStatus taskStatus,Epic epic) {
        super(id, taskName, taskDescription, taskStatus);
        this.id = id;
        this.taskStatus = taskStatus;
        this.epicId = epic.getId();
        this.taskType = TaskType.SUBTASK;
    }
    public int getEpicId() {
        return epicId;
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "epicId=" + epicId +
                ", subTaskID=" + id +
                ", subTaskName='" + taskName + '\'' +
                ", subTaskDescription='" + taskDescription + '\'' +
                ", subTaskStatus=" + taskStatus +
                '}';
    }
}
