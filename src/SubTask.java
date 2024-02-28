public class SubTask extends Task {
    private int epicId;

    public SubTask(String taskName, String taskDescription, TaskStatus taskstatus, Integer epicId) {
        super(taskName,taskDescription,taskstatus);
        this.epicId = epicId;
        this.taskType = TaskType.SUBTASK;
    }
    public SubTask(Integer id, String taskName, String taskDescription, TaskStatus taskStatus, Integer epicId) {
        super(id, taskName, taskDescription, taskStatus);
        this.taskStatus = taskStatus;
        this.epicId = epicId;
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
