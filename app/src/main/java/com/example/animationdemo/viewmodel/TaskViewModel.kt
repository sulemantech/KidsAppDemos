package com.example.animationdemo.viewmodel


import android.app.Application
import androidx.lifecycle.*
import com.example.animationdemo.data.Task
import com.example.animationdemo.data.TaskDatabase
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val taskDao = TaskDatabase.getDatabase(application).taskDao()
    val tasks: LiveData<List<Task>> = taskDao.getAllTasks().asLiveData()

    fun addTask(task: Task) = viewModelScope.launch { taskDao.insertTask(task) }
    fun updateTask(task: Task) = viewModelScope.launch { taskDao.updateTask(task) }
    fun deleteTask(task: Task) = viewModelScope.launch { taskDao.deleteTask(task) }
}


class TaskViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
