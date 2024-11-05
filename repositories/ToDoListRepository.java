package repositories;

import entities.ToDoList;

public interface ToDoListRepository {
    ToDoList[] getAll();

    void add (ToDoList toDoList);
    Boolean remove (Integer id);
    Boolean edit (ToDoList toDoList);
}
