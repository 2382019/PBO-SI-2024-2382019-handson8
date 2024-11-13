package services;

import entities.ToDoList;

public interface ToDoListService {
    ToDoList[] getTodoList();
    void addTodoList(String todo);
    Boolean removeTodoList(Integer number);
    Boolean editTodoList(Integer number, String todo);
}
