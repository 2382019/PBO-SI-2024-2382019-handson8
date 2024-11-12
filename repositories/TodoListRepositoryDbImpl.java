package repositories;

import config.Database;
import entities.ToDoList;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TodoListRepositoryDbImpl implements ToDoListRepository{
    private final Database database;

    public TodoListRepositoryDbImpl(Database database) {
        this.database = database;
    }

    @Override
    public ToDoList[] getAll() {
        Connection connection= database.getConnection();
        String sqlStatement = "SELECT * FROM todos";
        List<ToDoList> toDoLists = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ToDoList todoList = new ToDoList();
                int id = resultSet.getInt(1);
                String todo = resultSet.getString(2);
                todoList.setId(id);
                todoList.setTodo(todo);
                toDoLists.add(todoList);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return toDoLists.toArray(ToDoList[]::new);
    }

    @Override
    public void add(ToDoList toDoList) {

    }

    @Override
    public Boolean remove(Integer id) {
        return null;
    }

    @Override
    public Boolean edit(ToDoList toDoList) {
        return null;
    }
}
