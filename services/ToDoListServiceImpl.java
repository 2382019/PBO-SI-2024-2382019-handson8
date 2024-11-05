package services;

import entities.ToDoList;
import repositories.ToDoListRepository;

public class ToDoListServiceImpl implements ToDoListService{
    private final ToDoListRepository toDoListRepository;

    public ToDoListServiceImpl(final ToDoListRepository toDoListRepository) {
        this.toDoListRepository = toDoListRepository;
    }

    @Override
    public ToDoList[] getTodoList() {
        return toDoListRepository.getAll();
    }

    @Override
    public void addTodoList(String todo) {
        if (todo.isEmpty() || todo.isBlank()) {
            System.out.println("Masukkan todo dengan benar");
            return;
        }

        ToDoList toDoList =new ToDoList();
        toDoList.setTodo(todo);
        toDoListRepository.add(toDoList);
    }

    @Override
    public Boolean removeTodoList(Integer number) {
        return toDoListRepository.remove(number);
    }

    @Override
    public Boolean editTodoList(Integer number, String todo) {
        ToDoList toDoList = new ToDoList();
        toDoList.setTodo(todo);
        toDoList.setId(number);
        return toDoListRepository.edit(toDoList);
    }
}
