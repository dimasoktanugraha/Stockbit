package com.stockbit.stockbit.ui.watch

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.stockbit.stockbit.R
import com.stockbit.stockbit.core.data.source.remote.network.ApiResponse
import com.stockbit.stockbit.core.data.source.remote.response.CryptoData
import com.stockbit.stockbit.databinding.FragmentLoginBinding
import com.stockbit.stockbit.databinding.FragmentWatchBinding
import com.stockbit.stockbit.ui.login.LoginViewModel
import org.koin.android.ext.android.inject

class WatchFragment: Fragment() {

    private val watchViewModel: WatchViewModel by inject()
    private lateinit var watchAdapter: WatchAdapter
    private var page: Int = 0

    private var _binding: FragmentWatchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWatchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity!=null){
            watchAdapter = WatchAdapter()
            setSwipe()

            binding.watchRv.apply {
                layoutManager = LinearLayoutManager(requireActivity())
                addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
                setHasFixedSize(true)
                adapter = watchAdapter
            }
            getData()
        }
    }

    private fun setSwipe() {
        binding.watchSwipe.setOnRefreshListener {
            getData()
            binding.watchSwipe.isRefreshing = false
        }
    }

    private fun getData() {
        Log.d("CRYPTO", "Fragment")
        watchViewModel.getCrypto(page).observe(viewLifecycleOwner, {crypto ->
            if (crypto!=null){
                when(crypto){
                    is ApiResponse.Loading -> binding.watchProgress.visibility = View.VISIBLE
                    is ApiResponse.Empty -> {
                        binding.watchProgress.visibility = View.GONE
                        if (page==0){
                            binding.watchNoData.visibility = View.VISIBLE
                        }
                    }is ApiResponse.Success -> {
                        binding.watchProgress.visibility = View.GONE
                        if (page==0){
                            watchAdapter.setData(crypto.data)
                        }else{
                            watchAdapter.addData(crypto.data)
                        }
                    }is ApiResponse.Error -> {
                        binding.watchProgress.visibility = View.GONE
                        binding.watchNoData.visibility = View.VISIBLE
                        binding.watchNoData.text = crypto.errorMessage ?: getString(R.string.something_wrong)
                    }
                }
            }
        })
    }

    override fun onStop() {
        super.onStop()
        binding.watchSwipe.isRefreshing = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}