package com.example.lab4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lab4.databinding.FragmentItemDetailsBinding

class ItemDetailsFragment : Fragment() {
    lateinit var binding: FragmentItemDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentItemDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backButton.setOnClickListener {
            MAIN.navController.navigate(R.id.action_itemDetailsFragment_to_mainFragment)
        }
        val item = MAIN.taskAdapter.taskList.find { it.number.toString() == MAIN.taskAdapter.lastDetailsNumber }
        binding.taskTitle.text = item?.text
        binding.taskDetails.text = item?.details
    }

}