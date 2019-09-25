package com.mohaeyo.mohae.ui.fragment.main.group

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.base.DataBindingFragment
import com.mohaeyo.mohae.databinding.FragmentGroupDocBinding
import com.mohaeyo.mohae.viewmodel.main.group.GroupViewModel

class GroupDocFragment: DataBindingFragment<FragmentGroupDocBinding>() {

    private val viewModel: GroupViewModel by navGraphViewModels(R.id.group_nav_graph)

    override val layoutId: Int
        get() = R.layout.fragment_group_doc

    override fun onAttach(context: Context) {
        super.onAttach(context)

        requireActivity().onBackPressedDispatcher.addCallback(
            this, object: OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.action_groupDocFragment_to_groupListFragment)
                }
            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vm = viewModel
    }
}