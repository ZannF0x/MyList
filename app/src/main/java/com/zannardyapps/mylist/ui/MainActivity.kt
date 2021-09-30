package com.zannardyapps.mylist.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zannardyapps.mylist.databinding.ActivityMainBinding
import com.zannardyapps.mylist.datasource.TaskDataSource

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val taskAdapter by lazy { TaskAdapter() }

    companion object {
        private const val CODE_CREATE_NEW_TASK = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        binding.buttonAdd.setOnClickListener {
            openAddTaskActivity()
        }

    }

    private fun openAddTaskActivity(){
        val intent = Intent(this, AddTaskActivity::class.java)
        startActivityForResult(intent, CODE_CREATE_NEW_TASK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CODE_CREATE_NEW_TASK){

            taskAdapter.submitList(TaskDataSource.getList())
            binding.recyclerViewTasksToday.adapter = taskAdapter

        }
    }
}