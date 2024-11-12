import config.Database;
import repositories.ToDoListRepository;
import repositories.ToDoListRepositoryImpl;
import repositories.TodoListRepositoryDbImpl;
import services.ToDoListService;
import services.ToDoListServiceImpl;
import views.ToDoListTerminalView;
import views.ToDoListView;

public class Main {
    public static void main(String[] args) {
        Database database = new Database("databaseku", "root", "", "localhost", "3306");
        database.setup();


        ToDoListRepository toDoListRepository= new TodoListRepositoryDbImpl(database);
        ToDoListService toDoListService= new ToDoListServiceImpl(toDoListRepository);
        ToDoListView toDoListView = new ToDoListTerminalView(toDoListService);
        toDoListView.run();
    }
}
