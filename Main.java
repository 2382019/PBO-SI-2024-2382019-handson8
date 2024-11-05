import repositories.ToDoListRepository;
import repositories.ToDoListRepositoryImpl;
import services.ToDoListService;
import services.ToDoListServiceImpl;
import views.ToDoListTerminalView;
import views.ToDoListView;

public class Main {
    public static void main(String[] args) {
        ToDoListRepository toDoListRepository= new ToDoListRepositoryImpl();
        ToDoListService toDoListService= new ToDoListServiceImpl(toDoListRepository);
        ToDoListView toDoListView = new ToDoListTerminalView(toDoListService);
        toDoListView.run();
    }
}
