package com.zannardyapps.mylist.datasource

import com.zannardyapps.mylist.model.Task

object TaskDataSource {
    private val tasksList = arrayListOf<Task>()

    fun getList() = tasksList

    fun insertTasksList(task: Task){
        tasksList.add(task.copy(id = tasksList.size + 1))
    }
}