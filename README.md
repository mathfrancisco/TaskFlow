🚀 Task Flow App
Show Image Show Image Show Image Show Image Show Image
📋 Kanban-style Task Management Application
Backend • Frontend • Installation • Roadmap • License
📋 About
This task management application helps users organize their activities in a Kanban-style interface. It's ideal for professionals and teams tracking tasks, priorities, and deadlines visually.
🔙 Backend
📋 Key Features
📁 Task Boards

Create and manage multiple boards
Customizable columns (e.g., "To Do", "In Progress", "Done")

➕ Task Creation & Editing

Create tasks with title, description, due date, priority
Assign team members

🤖 Drag-and-Drop

Move tasks between columns, updating status

🔍 Filtering & Search

Filter by priority, status, assignee, due date
Search by title or keywords

🔔 Notifications & Alerts

Notify users of due/overdue tasks
Alert assignees of changes

📊 Analytics Dashboard

Charts for completed, in progress, overdue tasks
Progress overview (daily, weekly, monthly)

📜 Activity History

Track task creation, movement, and edits

🛠 Backend Stack

Framework: Spring Boot 2.7
Data: Hibernate, PostgreSQL
Notifications: JavaMailSender, Spring Events, WebSocket
Utilities: Lombok, ModelMapper, SLF4J

📁 Backend Structure
Copytask-app-meta/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── taskappmeta/
│   │   │               ├── backend/
│   │   │               │   ├── config/
│   │   │               │   ├── controller/
│   │   │               │   ├── repository/
│   │   │               │   └── service/
│   │   │               ├── frontend/
│   │   │               │   └── view/
│   │   │               └── model/
│   │   └── resources/
│   └── test/
│       └── java/
│           └── com/
│               └── example/
│                   └── taskappmeta/
├── pom.xml
└── README.md
🔜 Frontend
📱 Vaadin Architecture
📁 Frontend Structure
Copytask-app/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── taskapp/
│   │   │               ├── ui/
│   │   │               │   ├── components/
│   │   │               │   └── views/
│   │   │               └── service/
│   │   └── resources/
│   └── test/
│       └── java/
│           └── com/
│               └── example/
│                   └── taskapp/
└── pom.xml
🧩 Main Components
🖼️ KanbanBoardComponent

Drag-and-drop task movement
Customizable columns
Real-time updates

📅 CalendarComponent

Task scheduling and availability
Notification integration
Reminders

📊 AnalyticsComponent

Progress charts and graphs
Overdue task monitoring
Team workload visualization

📝 Form Validations
📋 Task Creation Form
typescriptCopyconst taskForm = {
  title: ['', [Validators.required, Validators.minLength(3)]],
  description: [''],
  dueDate: ['', Validators.required],
  priority: ['', Validators.required],
  assignees: [[], Validators.required]
}
🔄 Services
🔔 NotificationService
typescriptCopy@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  sendTaskReminder()
  notifyAssigneeUpdates()
  manageUserPreferences()
}
🚀 Installation
Backend
bashCopy# Clone the repository
git clone https://github.com/your-username/task-app-meta.git

# Enter the backend directory
cd task-app-meta/src/main/java/com/example/taskappmeta/backend

# Set up the environment
cp .env.example .env

# Install dependencies
./mvnw install

# Start the application
./mvnw spring-boot:run
Frontend
bashCopy# Enter the frontend directory
cd ../../../../../../../task-app

# Install dependencies
mvn clean install

# Start the application
mvn vaadin:run
⚙️ Required Configurations
propertiesCopy# application.properties
spring.datasource.url=jdbc:postgresql://localhost:5432/task_db
spring.datasource.username=your_username
spring.datasource.password=your_password

# Email Configurations
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_email@gmail.com
spring.mail.password=your_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
📈 Roadmap
Backend

 Caching
 Swagger docs
 Integration tests
 Email templates
 SMS integration

Frontend

 Hilla state management
 Push notifications
 Admin dashboard
 Advanced reporting
 Mobile app

📄 License
This project is licensed under the MIT License.
