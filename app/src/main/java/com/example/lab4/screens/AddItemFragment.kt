package com.example.lab4.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lab4.MAIN
import com.example.lab4.R
import com.example.lab4.TaskInfo
import com.example.lab4.databinding.FragmentAddItemBinding

class AddItemFragment : Fragment() {
    lateinit var binding: FragmentAddItemBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAddItemBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addNewTaskButton.setOnClickListener {
            MAIN.taskAdapter.taskInfoDao.insertAll(
                TaskInfo(
                binding.newTaskTitle.text.toString(),
                binding.newTaskDetails.text.toString(),
                false, TaskInfo.count++
            )
            )
            MAIN.taskAdapter.updateTaskListFromDB()
            MAIN.taskAdapter.updateActionBar()
            MAIN.navController.navigate(R.id.action_addItemFragment_to_mainFragment)
        }
        binding.cancelButton.setOnClickListener {
            MAIN.navController.navigate(R.id.action_addItemFragment_to_mainFragment)
        }
    }

}