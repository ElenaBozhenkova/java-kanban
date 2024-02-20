public class SubTask extends Task {
    protected int epicId;

    SubTask(String taskName, String taskDescription) {
        super(taskName,taskDescription);
        this.taskType = TaskType.SUBTASK;
        this.taskStatus = TaskStatus.NEW;
    }

    SubTask(Integer id,String taskName, String taskDescription,TaskStatus taskStatus) {
        super(id, taskName, taskDescription, taskStatus);
        this.taskType = TaskType.SUBTASK;
        this.id = id;
        this.taskStatus = taskStatus;
    }

}
