package com.mohaeyo.mohae.ui.fragment.main.group

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.base.DataBindingFragment
import com.mohaeyo.mohae.databinding.FragmentGroupDocBinding
import com.mohaeyo.mohae.doBackAnimation
import com.mohaeyo.mohae.doCommonAnimation
import com.mohaeyo.mohae.viewmodel.main.group.doc.GroupDocViewModel
import com.mohaeyo.mohae.viewmodel.main.group.doc.GroupDocViewModelFactory
import kotlinx.android.synthetic.main.fragment_group_doc.*
import javax.inject.Inject

class GroupDocFragment: DataBindingFragment<FragmentGroupDocBinding>() {


    @Inject
    lateinit var factory: GroupDocViewModelFactory

    private val viewModel by lazy { ViewModelProviders.of(this, factory).get(GroupDocViewModel::class.java) }

    override val layoutId: Int
        get() = R.layout.fragment_group_doc

    override fun onAttach(context: Context) {
        super.onAttach(context)

        requireActivity().onBackPressedDispatcher.addCallback(
            this, object: OnBackPressedCallback(true) {
                override fun handleOnBackPressed() = backToList()
            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeEvent()
        binding.vm = viewModel
    }

    private fun backToList() {
        group_doc_post_fab.doCommonAnimation(R.drawable.check_to_add)
        group_doc_back_fab.doBackAnimation(false)
        findNavController().navigate(R.id.action_groupDocFragment_to_groupListFragment)
    }

    private fun observeEvent() {
        viewModel.startDocToListEvent.observe(this, Observer { backToList() })
    }
}