package com.example.todolistsfinal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import java.time.LocalDate
import java.time.LocalTime
import java.util.*
import kotlinx.coroutines.launch
class TaskViewModel(private val repository: TaskItemRepository):  ViewModel()
{
    var taskItems: LiveData<List<TaskItem>> = repository.allTaskItems1`.asLiveData()

         fun addTaskItem(newTask: TaskItem) = viewModelScope.launch {
             repository.insertTaskItem(newTask)
         }
        fun updateTaskItem(taskItem: TaskItem) = viewModeScope.launch {
            repository.updateTaskItem(taskItem)
        }
        fun setCompleted(taskItem: TaskItem) = viewModelScope.launch {
            if(!taskItem.isCompleted())
                taskItem.completedDateString = taskItem.dateFormatter.format(LocalDate.now())
            repository.updateTaskItem(taskItem)
        }

    class TaskItemModelFactory(private val repository: TaskItemRepository): ViewModelProvider.Factory
    {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(TaskViewModel::class.java))
                return TaskViewModel(repository) as T

            throw IllegalArgumentException("Unknown Class for View Model")
        }

    }    }

 //   init {
 //       taskItems.value = mutableListOf()
 //   }
//
 //   fun addTaskItem(newTask: TaskItem)
 //   {
  //      val list = taskItems.value
  //      list!!.add(newTask)
   //     taskItems.postValue(list)
   // }

    fun updateTaskItem(id: UUID, name: String, desc: String, dueTime: LocalTime?)
    {
        val list = taskItems.value
        val task = list!!.find { it.id == id }!!
        task.name = name
        task.desc = desc
        task.dueTime = dueTime
        taskItems.postValue(list)
    }

   // fun setCompleted(taskItem: TaskItem)
    //{
     //   val list = taskItems.value
     //   val task = list!!.find { it.id == taskItem.id }!!
     //   if (task.completedDate() == null)
     //       task.completedDate() = LocalDate.now()
     //   taskItems.postValue(list)
   // }
//}