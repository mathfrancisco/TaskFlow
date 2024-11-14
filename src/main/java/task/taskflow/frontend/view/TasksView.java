package task.taskflow.frontend.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.datepicker.DatePicker;
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
        kanbanBoard.setClassName("kanban-board");

        // Exemplo de estrutura de colunas do Kanban
        Div todoColumn = new Div();
        todoColumn.setText("To Do");
        Div inProgressColumn = new Div();
        inProgressColumn.setText("In Progress");
        Div doneColumn = new Div();
        doneColumn.setText("Done");

        // Adicionar colunas ao quadro Kanban
        kanbanBoard.add(todoColumn, inProgressColumn, doneColumn);

        // Aqui você pode carregar tarefas do serviço e distribui-las nas colunas
        taskService.getAllTasks().forEach(task -> {
            Div taskCard = createTaskCard(task);
            switch (task.getStatus()) {
                case TODO -> todoColumn.add(taskCard);
                case IN_PROGRESS -> inProgressColumn.add(taskCard);
                case DONE -> doneColumn.add(taskCard);
            }
        });

        return kanbanBoard;
    }

    private Div createTaskCard(Task task) {
        Div taskCard = new Div();
        taskCard.setText(task.getTitle());
        taskCard.addClassName("task-card");
        return taskCard;
    }

    private FormLayout createTaskForm() {
        FormLayout formLayout = new FormLayout();

        TextField titleField = new TextField("Title");
        TextArea descriptionField = new TextArea("Description");
        DatePicker dueDateField = new DatePicker("Due Date");

        Button saveButton = new Button("Save", event ->
                saveTask(titleField.getValue(), descriptionField.getValue(), dueDateField.getValue())
        );

        formLayout.add(titleField, descriptionField, dueDateField, saveButton);
        return formLayout;
    }

    private void saveTask(String title, String description, LocalDate dueDate) {
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setDueDate(dueDate);
        taskService.createTask(task);

        // Atualizar o quadro Kanban após salvar a nova tarefa
        removeAll();
        add(createKanbanBoard(), createTaskForm());
    }
}
