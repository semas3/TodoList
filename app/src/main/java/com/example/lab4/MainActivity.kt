package com.example.lab4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    var taskList = arrayListOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10",  "1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val taskAdapter = TaskAdapter(this, taskList, supportActionBar)

        recyclerView = findViewById(R.id.recycler)
        recyclerView.adapter = taskAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        taskAdapter.supportActionBar?.title = "To do list        Total: ${taskAdapter.taskList.size} - Checked: 0"
    }

}