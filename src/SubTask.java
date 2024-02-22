public class SubTask extends Task {
    private int epicId;

    SubTask(String taskName, String taskDescription, TaskStatus taskstatus, Epic epic) {
        super(taskName,taskDescription,taskstatus);
        this.epicId = epic.getId();
        this.taskType = TaskType.SUBTASK;
    }

    SubTask(Integer id,String taskName, String taskDescription,TaskStatus taskStatus) {
        super(id, taskName, taskDescription, taskStatus);
        this.taskType = TaskType.SUBTASK;
        this.id = id;
        this.taskStatus = taskStatus;
    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "epicId=" + epicId +
                ", id=" + id +
                ", taskName='" + taskName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", taskStatus=" + taskStatus +
                ", taskType=" + taskType +
                '}';
    }
}
