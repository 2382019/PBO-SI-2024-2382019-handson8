package repositories;

import entities.ToDoList;

public class ToDoListRepositoryImpl implements ToDoListRepository{
    public static ToDoList[] todos = new ToDoList[10];


    @Override
    public ToDoList[] getAll() {
        return todos;
    }

    @Override
    public void add(ToDoList toDoList) {
        resizeArrayIfFull();

        for (int i = 0; i < todos.length; i++) {
            if (todos[i] == null) {
                todos[i] = toDoList;
                break;
            }
        }
    }

    private static void resizeArrayIfFull() {
        // cek whether todos is full
        Boolean isFull = true;
        isFull = isArrayFull(isFull);

        // if full, resize current array two times bigger
        if (isFull) {
            resizeArrayToTwoTimesBigger();
        }
    }

    private static void resizeArrayToTwoTimesBigger() {
        ToDoList[] temp = todos;
        todos = new ToDoList[todos.length * 2];
        for (int i = 0; i < temp.length; i++) {
            todos[i] = temp[i];
        }
    }

    private static Boolean isArrayFull(Boolean isFull) {
        for (int i = 0; i < todos.length; i++) {
            if (todos[i] == null) {
                isFull = false;
                break;
            }
        }
        return isFull;
    }


    @Override
    public Boolean remove(Integer number) {
        if (isSelectedTodoNotValid(number)) {
            return false;
        }

        for (int i = number - 1; i < todos.length; i++) {
            // if todo is the last element
            if (i == (todos.length - 1)) {
                todos[i] = null;
            } else {
                // replace with the element on the right
                todos[i] = todos[i + 1];
            }
        }
        return true;
    }

    private static boolean isSelectedTodoNotValid(final Integer number) {
        // cek if the number is zero or less than zero
        if (number <= 0) {
            return true;
        }

        // check if the number is greater than the todos size/length
        if (number - 1 > todos.length - 1) {
            return true;
        }

        // check whether the element is already null
        if (todos[number - 1] == null) {
            return true;
        }
        return false;
    }


    @Override
    public Boolean edit(ToDoList toDoList) {
        if (isSelectedTodoNotValid(toDoList.getId())) {
            return false;
        }
        todos[toDoList.getId() - 1] = toDoList;
        return true;
    }
}
