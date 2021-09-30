package com.zannardyapps.mylist.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
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

        initAdapter()

        binding.buttonAdd.setOnClickListener {
            openAddTaskActivity()
        }

    }

    private fun openAddTaskActivity(){
        val intent = Intent(this, AddTaskActivity::class.java)
        startActivityForResult(intent, CODE_CREATE_NEW_TASK)
    }

    private fun initAdapter(){
        taskAdapter.submitList(TaskDataSource.getList())
        binding.recyclerViewTasksToday.adapter = taskAdapter
        binding.recyclerViewTasksToday.layoutManager = LinearLayoutManager(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CODE_CREATE_NEW_TASK){
            taskAdapter.notifyItemInserted(TaskDataSource.lastPositionList())
        }
    }
}