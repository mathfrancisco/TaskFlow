package task.taskflow.frontend.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.dnd.DragSource;
import com.vaadin.flow.component.dnd.DropTarget;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import task.taskflow.backend.service.TaskService;
import task.taskflow.model.Task;
import task.taskflow.model.User;

import java.time.LocalDate;

@SpringComponent
@UIScope
@Route("tasks")
@PermitAll
public class TasksView extends VerticalLayout implements BeforeEnterObserver {

    private final TaskService taskService;
    private Dialog taskDialog;
    private HorizontalLayout kanbanColumns;

    @Autowired
    public TasksView(TaskService taskService) {
        this.taskService = taskService;
        setupLayout();
        setupStyles();
    }

    private void setupStyles() {
        getStyle()
                .set("padding", "20px")
                .set("background-color", "#f5f5f5");

        addClassName("tasks-view");
    }

    private void setupLayout() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Header
        HorizontalLayout header = new HorizontalLayout();
        header.setWidthFull();
        header.setJustifyContentMode(JustifyContentMode.BETWEEN);
        header.setAlignItems(Alignment.CENTER);

        H2 welcome = new H2("Bem-vindo, " + currentUser.getName());
        Button addTaskButton = new Button("Nova Tarefa", new Icon(VaadinIcon.PLUS));
        addTaskButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addTaskButton.addClickListener(e -> showTaskDialog());

        header.add(welcome, addTaskButton);
        add(header);

        // Kanban Board
        kanbanColumns = createKanbanBoard();
        add(kanbanColumns);

        // Task Dialog
        taskDialog = createTaskDialog();
    }

    private HorizontalLayout createKanbanBoard() {
        HorizontalLayout board = new HorizontalLayout();
        board.setWidthFull();
        board.getStyle()
                .set("gap", "20px")
                .set("padding", "20px");

        // Create columns
        Div todoColumn = createKanbanColumn("A Fazer", "todo-column", Task.Status.TODO);
        Div inProgressColumn = createKanbanColumn("Em Progresso", "in-progress-column", Task.Status.IN_PROGRESS);
        Div doneColumn = createKanbanColumn("Concluído", "done-column", Task.Status.DONE);

        board.add(todoColumn, inProgressColumn, doneColumn);
        return board;
    }

    private Div createKanbanColumn(String title, String className, Task.Status status) {
        Div column = new Div();
        column.addClassName("kanban-column");
        column.addClassName(className);
        column.getStyle()
                .set("background-color", "white")
                .set("border-radius", "8px")
                .set("padding", "16px")
                .set("width", "300px")
                .set("min-height", "400px");

        H3 header = new H3(title);
        header.getStyle().set("margin-top", "0");
        column.add(header);

        // Make column a drop target
        DropTarget<Div> dropTarget = DropTarget.create(column);
        dropTarget.addDropListener(event -> {
            if (event.getDragData().isPresent() && event.getDragData().get() instanceof Task) {
                Task task = (Task) event.getDragData().get();
                task.setStatus(status);
                taskService.updateTask(task);
                refreshBoard();
            }
        });

        // Add tasks
        taskService.getAllTasks().stream()
                .filter(task -> task.getStatus() == status)
                .forEach(task -> column.add(createTaskCard(task)));

        return column;
    }

    private Div createTaskCard(Task task) {
        Div card = new Div();
        card.addClassName("task-card");
        card.getStyle()
                .set("background-color", "#ffffff")
                .set("border", "1px solid #e0e0e0")
                .set("border-radius", "4px")
                .set("padding", "12px")
                .set("margin-bottom", "8px")
                .set("cursor", "move")
                .set("transition", "box-shadow 0.3s");

        // Make card draggable
        DragSource<Div> dragSource = DragSource.create(card);
        dragSource.setDragData(task);

        // Task content
        H3 title = new H3(task.getTitle());
        title.getStyle().set("margin", "0 0 8px 0");

        Div description = new Div();
        description.setText(task.getDescription());
        description.getStyle().set("margin-bottom", "8px");

        Div dueDate = new Div();
        dueDate.setText("Vencimento: " + task.getDueDate().toString());
        dueDate.getStyle()
                .set("font-size", "0.9em")
                .set("color", "#666");

        card.add(title, description, dueDate);

        // Hover effect
        card.getElement().addEventListener("mouseover",
                e -> card.getStyle().set("box-shadow", "0 2px 4px rgba(0,0,0,0.1)"));
        card.getElement().addEventListener("mouseout",
                e -> card.getStyle().set("box-shadow", "none"));

        return card;
    }

    private Dialog createTaskDialog() {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Nova Tarefa");

        FormLayout form = new FormLayout();
        TextField titleField = new TextField("Título");
        TextArea descriptionField = new TextArea("Descrição");
        DatePicker dueDateField = new DatePicker("Data de Vencimento");

        form.add(titleField, descriptionField, dueDateField);

        Button saveButton = new Button("Salvar", e -> {
            saveTask(titleField.getValue(), descriptionField.getValue(), dueDateField.getValue());
            dialog.close();
        });
        Button cancelButton = new Button("Cancelar", e -> dialog.close());
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        HorizontalLayout buttons = new HorizontalLayout(saveButton, cancelButton);
        buttons.setJustifyContentMode(JustifyContentMode.END);

        dialog.add(form, buttons);
        return dialog;
    }

    private void showTaskDialog() {
        taskDialog.open();
    }

    private void saveTask(String title, String description, LocalDate dueDate) {
        if (title == null || title.trim().isEmpty()) {
            Notification.show("O título é obrigatório");
            return;
        }

        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setDueDate(dueDate);
        task.setStatus(Task.Status.TODO);

        taskService.createTask(task);
        refreshBoard();
        Notification.show("Tarefa criada com sucesso!");
    }

    private void refreshBoard() {
        remove(kanbanColumns);
        kanbanColumns = createKanbanBoard();
        add(kanbanColumns);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (SecurityContextHolder.getContext().getAuthentication() == null
                || !SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            event.forwardTo(LoginView.class);
        }
    }
}