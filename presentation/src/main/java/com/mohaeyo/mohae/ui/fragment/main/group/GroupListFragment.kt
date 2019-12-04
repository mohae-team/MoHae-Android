package com.mohaeyo.mohae.ui.fragment.main.group

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.adapter.GroupListAdapter
import com.mohaeyo.mohae.base.EndPointDataBindingFragment
import com.mohaeyo.mohae.databinding.FragmentGroupListBinding
import com.mohaeyo.mohae.doBackAnimation
import com.mohaeyo.mohae.doCommonAnimation
import com.mohaeyo.mohae.model.GroupModel
import com.mohaeyo.mohae.viewmodel.main.group.list.GroupListViewModel
import kotlinx.android.synthetic.main.fragment_group_list.*
import javax.inject.Inject

class GroupListFragment: EndPointDataBindingFragment<FragmentGroupListBinding>() {
    @Inject
    override lateinit var viewModel: GroupListViewModel

    override val layoutId: Int
        get() = R.layout.fragment_group_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        binding.groupList.layoutManager = LinearLayoutManager(context)
        binding.groupList.adapter = GroupListAdapter(viewModel)
    }

    override fun observeEvent() {
        viewModel.startListToDocEvent.observe(this, Observer {
            group_list_add_fab.doCommonAnimation(R.drawable.add_to_check)
            group_list_back_fab.doBackAnimation(true)
            findNavController().navigate(R.id.action_groupListFragment_to_groupDocFragment)
        })

        viewModel.listAnimationEvent.observe(this, Observer {
            binding.groupList.layoutAnimation =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_slide_from_bottom)
            binding.groupList.scheduleLayoutAnimation()
        })

        viewModel.startListToDetailEvent.observe(this, Observer {
            group_list_add_fab.doCommonAnimation(R.drawable.add_to_swap_horiz)
            group_list_back_fab.doBackAnimation(true)
            findNavController().navigate(R.id.action_groupListFragment_to_groupDetailFragment, getGroupItemBundle(it))
        })
    }

    private fun getGroupItemBundle(groupModel: GroupModel): Bundle {
        val bundle = Bundle()
        with(groupModel) { bundle.putInt("id", id) }
        return bundle
    }
}