package task.taskflow.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "board")  // Relationship with the Task entity
    private List<Task> tasks = new ArrayList<>();

    public Board() {}

    public Board(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    // Helper method to add tasks
    public void addTask(Task task) {
        tasks.add(task);
        task.setBoard(this);  // Set the board for the task (important for bidirectional mapping)
    }
}
