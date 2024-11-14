package task.taskflow.frontend.view;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import task.taskflow.backend.service.TaskService;
import task.taskflow.model.Task;

import java.time.LocalDate;

@SpringComponent
@UIScope
@Route("tasks")
public class TasksView extends Div {

    private final TaskService taskService;

    @Autowired
    public TasksView(TaskService taskService) {
        this.taskService = taskService;

        add(createKanbanBoard());
        add(createTaskForm());
    }

    private Div createKanbanBoard() {
        Div kanbanBoard = new Div();
        // Implementar quadro Kanban usando Hilla para comunicação em tempo real
        return kanbanBoard;
    }

    private Div createTaskForm() {
        // Implementar formulário de criação de tarefas
        return null;
    }

    private void saveTask(String title, String description, LocalDate dueDate) {
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setDueDate(dueDate); // Remover conversão desnecessária
        taskService.createTask(task);
    }
}
