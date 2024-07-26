package com.capgemini.todoapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.capgemini.todoapp.Todo

@Dao
interface TodoDao {

    @Query("SELECT * FROM TODO")
    fun getAllTodo() : LiveData<List<Todo>>

    @Insert
    fun addTodo(todo : Todo)

    @Query ("Delete FROM Todo where id = :id ")
    fun deleteTodo(id:Int)

    @Update
    fun updateTodo(todo: Todo)

    @Query ("Select * FROM Todo where id = :id")
    fun getTodoById(id: Int): Todo

}