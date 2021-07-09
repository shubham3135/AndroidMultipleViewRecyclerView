package com.shubhamkumarwinner.multipleviewrecyclerview.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.shubhamkumarwinner.multipleviewrecyclerview.data.network.Resource
import com.shubhamkumarwinner.multipleviewrecyclerview.databinding.MainFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding
    private val viewModel by viewModels<MainViewModel>()
    private val homeRecyclerViewAdapter = HomeRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(layoutInflater)

        viewModel.homeListItemsLiveData.observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Failure -> binding.progressBar.hide()
                Resource.Loading -> binding.progressBar.show()
                is Resource.Success -> {
                    binding.progressBar.hide()
                    binding.recyclerView.adapter = homeRecyclerViewAdapter
                    homeRecyclerViewAdapter.submitList(result.value)
                }
            }
        })
        return binding.root
    }


}