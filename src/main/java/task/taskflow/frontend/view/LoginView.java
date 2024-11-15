package task.taskflow.frontend.view;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("login")
@PageTitle("Login | TaskFlow")
@AnonymousAllowed
public class LoginView extends VerticalLayout {

    private final LoginForm login = new LoginForm();

    public LoginView() {
        addClassName("login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        login.setAction("login");
        login.setForgotPasswordButtonVisible(false);

        getStyle()
                .set("display", "flex")
                .set("flex-direction", "column")
                .set("align-items", "center");

        login.getElement().getThemeList().add("dark");

        Button registerButton = new Button("Criar Conta",
                event -> getUI().ifPresent(ui -> ui.navigate("register")));

        add(
                new H2("TaskFlow"),
                login,
                registerButton
        );
    }
}