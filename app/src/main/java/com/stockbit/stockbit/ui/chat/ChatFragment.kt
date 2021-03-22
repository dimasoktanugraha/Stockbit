package com.stockbit.stockbit.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.stockbit.stockbit.databinding.FragmentChatBinding
import com.stockbit.stockbit.databinding.FragmentLoginBinding
import com.stockbit.stockbit.databinding.FragmentStreamBinding
import com.stockbit.stockbit.databinding.FragmentWatchBinding
import com.stockbit.stockbit.ui.login.LoginViewModel
import org.koin.android.ext.android.inject

class ChatFragment: Fragment() {

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity!=null){

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}