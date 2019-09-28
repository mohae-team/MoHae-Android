package com.mohaeyo.mohae.ui.fragment.main.group

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.base.DataBindingFragment
import com.mohaeyo.mohae.databinding.FragmentGroupDetailBinding
import com.mohaeyo.mohae.doBackAnimation
import com.mohaeyo.mohae.doCommonAnimation
import com.mohaeyo.mohae.model.GroupModel
import com.mohaeyo.mohae.ui.dialog.GroupDetailDialogFragment
import com.mohaeyo.mohae.viewmodel.main.group.detail.GroupDetailViewModel
import com.mohaeyo.mohae.viewmodel.main.group.detail.GroupDetailViewModelFactory
import kotlinx.android.synthetic.main.fragment_group_detail.*
import javax.inject.Inject

class GroupDetailFragment: DataBindingFragment<FragmentGroupDetailBinding>() {

    @Inject
    lateinit var factory: GroupDetailViewModelFactory

    private val viewModel by lazy { ViewModelProviders.of(this, factory).get(GroupDetailViewModel::class.java) }

    override val layoutId: Int
        get() = R.layout.fragment_group_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getArgGroupItem()
        observeEvent()

        binding.vm = viewModel
    }

    private fun observeEvent() {
        viewModel.startDetailToListEvent.observe(this, Observer {
            group_detail_join_fab.doCommonAnimation(R.drawable.swap_horiz_to_add)
            group_detail_back_fab.doBackAnimation(false)
            findNavController().navigate(R.id.action_groupDetailFragment_to_groupListFragment)
        })

        viewModel.startDetailToDialogEvent.observe(this, Observer {
            GroupDetailDialogFragment().show(fragmentManager!!, "detail")
        })
    }

    private fun getArgGroupItem() {
        viewModel.selectedGroupItem.value = GroupModel(
            title = arguments!!.getString("title")!!,
            address = arguments!!.getString("address")!!,
            term = arguments!!.getString("term")!!,
            imageUrl = arguments!!.getString("imageUrl")!!,
            description = arguments!!.getString("description")!!,
            count = arguments!!.getString("count")!!)
    }
}