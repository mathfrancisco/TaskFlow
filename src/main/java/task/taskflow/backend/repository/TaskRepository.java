package task.taskflow.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task.taskflow.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
