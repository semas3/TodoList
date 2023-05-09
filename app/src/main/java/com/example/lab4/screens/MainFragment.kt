package com.example.lab4.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lab4.MAIN
import com.example.lab4.R
import com.example.lab4.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    lateinit var binding: FragmentMainBinding
    lateinit var recycler: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)

        recycler = binding.recycler
        recycler.adapter = MAIN.taskAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addButton.setOnClickListener {
            MAIN.navController.navigate(R.id.action_mainFragment_to_addItemFragment)
        }
    }
}