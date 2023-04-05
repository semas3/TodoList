package com.example.lab4

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.RecyclerView
import com.example.lab4.TaskAdapter.TaskViewHolder

class TaskInfo(val text: String, var isChecked: Boolean, val number: Int) {
    companion object {
        var count = 0
    }
}

class TaskAdapter(context: Context, taskList: ArrayList<String>, val supportActionBar: ActionBar?)
                                                        : RecyclerView.Adapter<TaskViewHolder>() {
    val taskList: MutableList<TaskInfo> = taskList
        .map { TaskInfo(it, false, TaskInfo.count++) }.toMutableList()
    private val inflater: LayoutInflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = inflater.inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(itemView, this)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.apply {
            taskCheckBox.text = taskList[position].text
            taskCheckBox.isChecked = taskList[position].isChecked
            number.text = taskList[position].number.toString()
            taskCheckBox.paintFlags = if (taskList[position].isChecked)
                taskCheckBox.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            else
                taskCheckBox.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun getItemCount() = taskList.size

    inner class TaskViewHolder(itemView: View, private val adapter: TaskAdapter) : RecyclerView.ViewHolder(itemView) {
        val taskCheckBox: CheckBox = itemView.findViewById(R.id.taskCheckBox)
        val deleteButton: TextView = itemView.findViewById(R.id.deleteButton)
        val number: TextView = itemView.findViewById(R.id.numberTextView)

        init {
            taskCheckBox.setOnClickListener {
                taskList[layoutPosition].isChecked = !taskList[layoutPosition].isChecked
                adapter.notifyItemChanged(layoutPosition)
                adapter.supportActionBar?.title =
                    "To do list        Total: ${adapter.taskList.size} - Checked: ${adapter.taskList.count { it.isChecked }}"
            }
            deleteButton.setOnClickListener {
                val position = taskList.indexOfFirst { it.number.toString() == number.text }
                taskList.removeAt(position)
                adapter.notifyItemRemoved(position)
                adapter.supportActionBar?.title =
                    "To do list        Total: ${adapter.taskList.size} - Checked: ${adapter.taskList.count { it.isChecked }}"
            }
        }
    }


}