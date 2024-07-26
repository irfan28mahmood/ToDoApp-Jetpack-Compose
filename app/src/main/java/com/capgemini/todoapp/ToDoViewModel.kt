package com.capgemini.todoapp

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capgemini.todoapp.db.TodoDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class ToDoViewModel : ViewModel() {

    val todoDao = MainApplication.todoDatabase.getTodoDao()

    val todoList : LiveData<List<Todo>> = todoDao.getAllTodo()



    fun addTodo(title:String) {
        viewModelScope.launch (Dispatchers.IO){
            todoDao.addTodo(Todo(title = title, createdAt = Date.from(Instant.now())))
        }
    }

    fun updateTodo(id: Int, newTitle: String){
        viewModelScope.launch(Dispatchers.IO) {
            val todo = todoDao.getTodoById(id) // Method to get the Todo object by id
            if (todo != null) {
                todo.title = newTitle
                todo.createdAt=Date.from(Instant.now())
                todoDao.updateTodo(todo)
            }

        }

    }

    fun deleteTodo(id:Int) {
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.deleteTodo(id)
        }
    }
}