package com.zannardyapps.mylist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zannardyapps.mylist.databinding.ItemTaskLayoutBinding
import com.zannardyapps.mylist.model.Task

class TaskAdapter(): ListAdapter<Task,TaskAdapter.TaskViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTaskLayoutBinding.inflate(inflater, parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TaskViewHolder
        (private val binding: ItemTaskLayoutBinding)
        : RecyclerView.ViewHolder(binding.root){

            fun bind(item: Task){
                binding.taskTitle.text = item.title
                binding.taskDescription.text = item.description
                binding.taskTime.text = item.time
                binding.taskDate.text = item.date
            }
    }

    class DiffCallback: DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task) = oldItem == newItem

        override fun areContentsTheSame(oldItem: Task, newItem: Task) = oldItem.id == newItem.id

    }
}