package com.abmn.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: TaskViewModel = ViewModelProvider(this, TaskViewModelFactory(this))[TaskViewModel::class.java]

        setContent {
            ToDoApp(viewModel)
        }
    }
}

@Composable
fun ToDoApp(viewModel: TaskViewModel) {
    val taskList by viewModel.taskList.collectAsState()

    var taskText by remember { mutableStateOf("") }

    Column(Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = taskText,
            onValueChange = { taskText = it },
            label = { Text("Enter a task") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        Button(onClick = {
            if (taskText.isNotBlank()) {
                viewModel.addTask(taskText)
                taskText = ""
            }
        }) {
            Text("Add Task")
        }

        Spacer(Modifier.height(16.dp))

        LazyColumn {
            items(taskList) { task ->
                TaskItem(task.title) {
                    viewModel.deleteTask(task)
                }
            }
        }
    }
}

@Composable
fun TaskItem(task: String, onDelete: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(task)
        IconButton(onClick = onDelete) {
            Icon(Icons.Default.Delete, contentDescription = "Delete")
        }
    }
}
