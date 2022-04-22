package com.example.physicswallah

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.physicswallah.adapter.TeachersAdapter
import com.example.physicswallah.databinding.FragmentTeachersDetailsBinding
import com.example.physicswallah.model.UsersResponseItem
import com.example.physicswallah.utils.LoadingState
import com.example.physicswallah.viewModels.GetTeachersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeachersDetailsFragment : Fragment(R.layout.fragment_teachers_details) {
    private lateinit var binding : FragmentTeachersDetailsBinding
    private val viewModel: GetTeachersViewModel by activityViewModels()
    private var linearLayoutManager: LinearLayoutManager? = null
    private var adapter:TeachersAdapter?=null
    private var list: List<UsersResponseItem>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentTeachersDetailsBinding.inflate(inflater,container,false)
        binding.rvTeachers.visibility=View.GONE
        binding.shimmerViewContainer.visibility=View.VISIBLE
        binding.shimmerViewContainer.startShimmer()
        initComponents()
        callListeners()
        return  binding.root
    }

    private fun callListeners() {

        viewModel.userDetailsLiveData.observe(viewLifecycleOwner) {
            it?.let { it ->
                list=it
                linearLayoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL, false)
                binding.rvTeachers.layoutManager = linearLayoutManager
                adapter = TeachersAdapter()
                binding.rvTeachers.adapter = adapter
                binding.rvTeachers.visibility=View.VISIBLE
                binding.shimmerViewContainer.visibility=View.GONE
                binding.shimmerViewContainer.stopShimmer()
                list?.let { adapter?.submitList(it) }
            }
        }
        viewModel.loadingState.observe(viewLifecycleOwner) {
            when (it?.status) {
                LoadingState.Status.RUNNING -> {

                }
                LoadingState.Status.SUCCESS -> {

                }
                LoadingState.Status.FAILED -> {
                    Toast.makeText(requireContext(),it.msg,Toast.LENGTH_LONG).show()
                }
            }

        }


    }

    private fun initComponents() {
        viewModel.getTeachersInfo()
    }

}