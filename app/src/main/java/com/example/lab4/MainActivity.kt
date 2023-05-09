package com.example.lab4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab4.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    var taskList = arrayListOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
    private lateinit var recyclerView: RecyclerView
    lateinit var taskAdapter: TaskAdapter

    lateinit var navController: NavController
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MAIN = this
        taskAdapter = TaskAdapter(this, taskList, supportActionBar)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = findViewById(R.id.recycler)
        recyclerView.adapter = taskAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        taskAdapter.updateActionBar()

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
    }

}