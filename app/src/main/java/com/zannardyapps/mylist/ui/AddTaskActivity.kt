package com.zannardyapps.mylist.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.google.android.material.timepicker.TimeFormat.CLOCK_24H
import com.zannardyapps.mylist.databinding.ActivityAddTaskBinding
import com.zannardyapps.mylist.datasource.TaskDataSource
import com.zannardyapps.mylist.extensions.format
import com.zannardyapps.mylist.model.Task
import java.util.*

class AddTaskActivity: AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        binding.editTextData.setOnClickListener {
            insertDate()
        }

        binding.editTextTime.setOnClickListener {
            insertTime()
        }

        binding.buttonCancel.setOnClickListener {
            finish()
        }

        binding.buttonAdd.setOnClickListener {

            if (binding.editTextTitle.text.isEmpty()||
                //binding.editTextDescription.text.isEmpty()||
                binding.editTextData.text.isEmpty()||
                binding.editTextTime.text.isEmpty()){

                Toast.makeText(this, "ERRO! Preencha todos os campos não opcionais.", Toast.LENGTH_LONG).show()

            }else {

                val addedTask: Task = Task(
                    title = binding.editTextTitle.text.toString(),
                    description = binding.editTextDescription.text.toString(),
                    date = binding.editTextData.text.toString(),
                    time = binding.editTextTime.text.toString()
                )

                TaskDataSource.insertTasksList(addedTask)
                Toast.makeText(this, "Tarefa adicionada!", Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun insertDate(){
        val datePicker = MaterialDatePicker.Builder.datePicker().build()

        // Exibe o calendario para selecionar a Data
        datePicker.show(supportFragmentManager, "DATE_PICKER_TAG")

        // Se o usuário apertar em Ok...
        datePicker.addOnPositiveButtonClickListener { dataSelected ->
            val timeZone = TimeZone.getDefault()
            // O datepicker seleciona um dia antes da escolha do usuário
            val ofSett = timeZone.getOffset(Date().time) * -1
            // Somando a data selecionada + ofSett a data exibida foi exatamente o que o usuário escolheu.
            binding.editTextData.setText(Date(dataSelected + ofSett).format())
        }
    }

    private fun insertTime(){
        val timePicker = MaterialTimePicker
            .Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .build()

        timePicker.show(supportFragmentManager, "TIME_PICKER_TAG")

        // Se o usuário apertar em Ok...
        timePicker.addOnPositiveButtonClickListener {
           val timeHour = if (timePicker.minute in 0..9) "0${timePicker.minute}" else timePicker.minute
            val timeMinute = if (timePicker.hour in 0..9) "0${timePicker.hour}" else timePicker.hour

            binding.editTextTime.setText("$timeHour:$timeMinute")
        }
    }
}