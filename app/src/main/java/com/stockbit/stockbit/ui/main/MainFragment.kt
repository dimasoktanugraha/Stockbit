package com.stockbit.stockbit.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.stockbit.stockbit.R
import com.stockbit.stockbit.databinding.FragmentMainBinding


class MainFragment: Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity!=null){

            val navController = requireActivity().findNavController(R.id.main_nav_fragment)
            navController.setGraph(R.navigation.main_navigation)
            binding.mainBottomNav.setupWithNavController(navController)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}