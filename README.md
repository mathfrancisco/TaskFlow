# 🚀 Task Flow App

## 📋 Kanban-style Task Management Application  
Backend • Frontend • Installation • Roadmap • License

## 📋 About
Este aplicativo de gerenciamento de tarefas ajuda os usuários a organizar suas atividades em uma interface estilo Kanban. Ideal para profissionais e equipes acompanharem tarefas, prioridades e prazos de maneira visual.

## 🔙 Backend
### 📋 Key Features

#### 📁 Task Boards
- Criação e gerenciamento de múltiplos quadros
- Colunas personalizáveis (e.g., "A Fazer", "Em Progresso", "Concluído")

#### ➕ Task Creation & Editing
- Criação de tarefas com título, descrição, data de vencimento e prioridade
- Atribuição de membros da equipe

#### 🤖 Drag-and-Drop
- Mover tarefas entre colunas, atualizando status automaticamente

#### 🔍 Filtering & Search
- Filtros por prioridade, status, responsável e data de vencimento
- Busca por título ou palavras-chave

#### 🔔 Notifications & Alerts
- Notificação de tarefas vencidas ou próximas do prazo
- Alerta aos responsáveis sobre alterações

#### 📊 Analytics Dashboard
- Gráficos de tarefas concluídas, em progresso e atrasadas
- Visão geral de progresso (diário, semanal, mensal)

#### 📜 Activity History
- Rastreamento de criação, movimentação e edição de tarefas

### 🛠 Backend Stack
- **Framework**: Spring Boot 2.7
- **Data**: Hibernate, PostgreSQL
- **Notificações**: JavaMailSender, Spring Events, WebSocket
- **Utilitários**: Lombok, ModelMapper, SLF4J

## 📁 Backend Structure
```
task-app-meta/
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
```

## 🧩 Main Components

### 🖼️ KanbanBoardComponent
- Movimentação de tarefas com drag-and-drop
- Colunas personalizáveis
- Atualizações em tempo real

### 📅 CalendarComponent
- Agendamento de tarefas e visualização de disponibilidade
- Integração com notificações e lembretes

### 📊 AnalyticsComponent
- Gráficos de progresso e tarefas atrasadas
- Visualização de carga de trabalho da equipe

### 📝 Form Validations
### 📋 Task Creation Form
```typescript
const taskForm = {
  title: ['', [Validators.required, Validators.minLength(3)]],
  description: [''],
  dueDate: ['', Validators.required],
  priority: ['', Validators.required],
  assignees: [[], Validators.required]
}
```

### 🔄 Services
#### 🔔 NotificationService
```typescript
@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  sendTaskReminder() {}
  notifyAssigneeUpdates() {}
  manageUserPreferences() {}
}
```

## 🚀 Installation

### Backend
```bash
# Clone o repositório
git clone https://github.com/your-username/task-app-meta.git

# Entre no diretório do backend
cd task-app-meta/src/main/java/com/example/taskappmeta/backend

# Configure o ambiente
cp .env.example .env

# Instale as dependências
./mvnw install

# Inicie a aplicação
./mvnw spring-boot:run
```

### Frontend
```bash
# Entre no diretório do frontend
cd ../../../../../../../task-app

# Instale as dependências
mvn clean install

# Inicie a aplicação
mvn vaadin:run
```

## ⚙️ Required Configurations
```properties
# application.properties
spring.datasource.url=jdbc:postgresql://localhost:5432/task_db
spring.datasource.username=your_username
spring.datasource.password=your_password

# Configurações de email
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_email@gmail.com
spring.mail.password=your_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

## 📈 Roadmap

### Backend
- Caching
- Swagger docs
- Integration tests
- Email templates
- SMS integration

### Frontend
- Hilla state management
- Push notifications
- Admin dashboard
- Advanced reporting
- Mobile app

## 📄 License
This project is licensed under the MIT License.
