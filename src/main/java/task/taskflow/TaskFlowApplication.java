package task.taskflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TaskFlowApplication {
	public static void main(String[] args) {
		SpringApplication.run(TaskFlowApplication.class, args);
	}
}