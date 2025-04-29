package com.abmn.todoapp

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TaskViewModelFactory(context: Context) : ViewModelProvider.Factory {
    private val database = TaskDatabase.getDatabase(context)
    private val repository = TaskRepository(database.taskDao())

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TaskViewModel(repository) as T
    }
}
