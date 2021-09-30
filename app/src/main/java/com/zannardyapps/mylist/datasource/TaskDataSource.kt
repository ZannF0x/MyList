package com.zannardyapps.mylist.datasource

import com.zannardyapps.mylist.model.Task

object TaskDataSource {
    private val tasksList = arrayListOf<Task>()

    fun getList() = tasksList.toList()

    fun insertTasksList(task: Task){
        if (task.id == 0){
            tasksList.add(task.copy(id = tasksList.size + 1))
        } else {
            tasksList.remove(task)
            //tasksList.add(task)
        }
    }

    fun lastPositionList() = tasksList.size - 1

    fun findById(id: Int) = tasksList.find {it.id == id }

    fun removeTask(task: Task){
        tasksList.remove(task)
    }

}