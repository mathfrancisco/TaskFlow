package task.taskflow.backend.service;

import org.springframework.stereotype.Service;
import task.taskflow.model.Task;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class NotificationService {
    public void checkTaskDeadline(Task task) {
        LocalDateTime dueDateTime = task.getDueDate().atTime(23, 59, 59); // Deve funcionar agora
        Duration timeRemaining = Duration.between(LocalDateTime.now(), dueDateTime);

        if (timeRemaining.getSeconds() <= 86400) { // 24 horas
            notifyUser(task.getAssignee(), task);
        }
    }

    private void notifyUser(task.taskflow.model.User user, Task task) {
        // Notificação por e-mail
        System.out.println("Notificando o usuário " + user.getEmail() + " sobre a tarefa " + task.getTitle());
    }
}
