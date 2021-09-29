package com.zannardyapps.mylist.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.google.android.material.timepicker.TimeFormat.CLOCK_24H
import com.zannardyapps.mylist.databinding.ActivityAddTaskBinding
import com.zannardyapps.mylist.extensions.format
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
            TODO("Criar TaskDataSource")
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
            binding.editTextTime.setText("${timePicker.hour}:${timePicker.minute}")
        }
    }
}