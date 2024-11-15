package task.taskflow.frontend.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.security.crypto.password.PasswordEncoder;
import task.taskflow.backend.service.UserService;
import task.taskflow.model.User;

@Route("register")
@AnonymousAllowed
public class RegisterView extends VerticalLayout {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public RegisterView(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;

        addClassName("register-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        TextField nameField = new TextField("Nome");
        TextField surnameField = new TextField("Sobrenome");
        EmailField emailField = new EmailField("Email");
        PasswordField passwordField = new PasswordField("Senha");
        Button registerButton = new Button("Registrar", event -> register(
                nameField.getValue(),
                surnameField.getValue(),
                emailField.getValue(),
                passwordField.getValue()
        ));

        add(
                new H2("Criar Conta"),
                nameField,
                surnameField,
                emailField,
                passwordField,
                registerButton
        );
    }

    private void register(String name, String surname, String email, String password) {
        if (userService.getUserByEmail(email).isPresent()) {
            Notification.show("Email já cadastrado");
            return;
        }

        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("USER");

        userService.createUser(user);
        Notification.show("Conta criada com sucesso!");
        getUI().ifPresent(ui -> ui.navigate("login"));
    }
}