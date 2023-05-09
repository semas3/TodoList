package com.example.lab4

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Room
import com.example.lab4.TaskAdapter.TaskViewHolder
import com.example.lab4.database.AppDatabase

@Entity
class TaskInfo(val text: String,
               val details: String,
               var isChecked: Boolean,
               @PrimaryKey val id: Int) {
    companion object {
        var count = 0
    }
}

class TaskAdapter(context: Context, taskTitleList: ArrayList<String>, val supportActionBar: ActionBar?)
                                                        : RecyclerView.Adapter<TaskViewHolder>() {
    lateinit var taskList: MutableList<TaskInfo>
    val inflater: LayoutInflater = LayoutInflater.from(context)

    val db = Room.databaseBuilder(MAIN, AppDatabase::class.java, "db").allowMainThreadQueries().build()
    val taskInfoDao = db.taskInfoDao()

    init {
        updateTaskListFromDB()
        if (taskList.isEmpty()) {
            taskInfoDao.insertAll(*taskTitleList.map { TaskInfo(it, it, false, TaskInfo.count++) }.toTypedArray())
        }
        else TaskInfo.count = taskList.maxOf { it.id } + 1
        updateTaskListFromDB()
    }

    var lastDetailsNumber = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = inflater.inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(itemView, this)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.apply {
            taskCheckBox.text = taskList[position].text
            taskCheckBox.isChecked = taskList[position].isChecked
            number.text = taskList[position].id.toString()
            taskCheckBox.paintFlags = if (taskList[position].isChecked)
                taskCheckBox.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            else
                taskCheckBox.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun getItemCount() = taskList.size

    inner class TaskViewHolder(itemView: View, private val adapter: TaskAdapter) : RecyclerView.ViewHolder(itemView) {
        val taskCheckBox: CheckBox = itemView.findViewById(R.id.taskCheckBox)
        val deleteButton: Button = itemView.findViewById(R.id.deleteButton)
        val detailsButton: Button = itemView.findViewById(R.id.detailsButton)
        val number: TextView = itemView.findViewById(R.id.numberTextView)

        init {
            taskCheckBox.setOnClickListener {
                taskList[layoutPosition].isChecked = !taskList[layoutPosition].isChecked
                taskInfoDao.update(taskList[layoutPosition])
                adapter.notifyItemChanged(layoutPosition)
                updateActionBar()
            }
            deleteButton.setOnClickListener {
                taskInfoDao.delete(taskList[layoutPosition])
                updateTaskListFromDB()
                adapter.notifyItemRemoved(layoutPosition)
                updateActionBar()
            }
            detailsButton.setOnClickListener {
                lastDetailsNumber = number.text.toString()
                MAIN.navController.navigate(R.id.action_mainFragment_to_itemDetailsFragment)
            }
        }
    }

    fun updateActionBar() {
        supportActionBar?.title =
            "To do list        Total: ${taskList.size} - Checked: ${taskList.count { it.isChecked }}"
    }

    fun updateTaskListFromDB() {
        taskList = taskInfoDao.getAll().toMutableList()
    }

    fun clearDB() {
        taskList.forEach {
            taskInfoDao.delete(it)
        }
        updateTaskListFromDB()
    }
}