public class Task {
    protected int id;
    protected String taskName;
    protected String taskDescription;
    protected TaskStatus taskStatus;
    protected TaskType taskType;
    public Task(String taskName, String taskDescription) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskStatus = TaskStatus.NEW;
        this.taskType = TaskType.TASK;
    }



}
