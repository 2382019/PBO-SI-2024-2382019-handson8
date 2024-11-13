package repositories;

import config.Database;
import entities.ToDoList;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
    public void add(final ToDoList todolist) {
        String sqlStatement = "INSERT INTO todo(todo) values(?)";
        Connection conn = database.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement);
            preparedStatement.setString(1, todolist.getTodo());

            int rowsEffected = preparedStatement.executeUpdate();
            if (rowsEffected > 0) {
                System.out.println("Insert successful !");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Boolean remove(Integer id) {
        String sqlStatement = "DELETE FROM todo WHERE id = ?";
        Connection conn = database.getConnection();
        var dbId = getDbId(id);
        if (dbId == null) {
            return false;
        }
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, dbId);

            int rowsEffected = preparedStatement.executeUpdate();
            if (rowsEffected > 0) {
                System.out.println("Delete successful !");
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private Integer getDbId(final Integer id) {
        ToDoList [] todolists = getAll();
        Long countElement = Arrays.stream(todolists).filter(Objects::nonNull).count();
        if (countElement.intValue() == 0) {
            return null;
        }
        var dbId = todolists[id - 1].getId();
        return dbId;
    }

    @Override
    public Boolean edit(final ToDoList todolist) {
        String sqlStatement = "UPDATE todo set todo = ? WHERE id = ?";
        Connection conn = database.getConnection();
        var dbId = getDbId(todolist.getId());
        if (dbId == null) {
            return false;
        }
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement);
            preparedStatement.setString(1, todolist.getTodo());
            preparedStatement.setInt(2, dbId);

            int rowsEffected = preparedStatement.executeUpdate();
            if (rowsEffected > 0) {
                System.out.println("Update successful !");
                return false;
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
