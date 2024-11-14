package task.taskflow.model;

import java.time.LocalDate;

public class Task {
    private Long id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private Priority priority;
    private Status status;
    private User assignee;

    // Getters, setters, construtores
    public enum Priority { LOW, MEDIUM, HIGH }
    public enum Status { TODO, IN_PROGRESS, DONE }
}
