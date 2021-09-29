package com.zannardyapps.mylist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zannardyapps.mylist.databinding.ActivityMainBinding
import com.zannardyapps.mylist.ui.AddTaskActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

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
        startActivity(intent)
        finish()
    }
}