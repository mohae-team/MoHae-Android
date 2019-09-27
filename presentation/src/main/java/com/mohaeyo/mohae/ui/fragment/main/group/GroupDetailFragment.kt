package com.mohaeyo.mohae.ui.fragment.main.group

import android.graphics.drawable.Animatable
import android.os.Bundle
import android.view.View
import android.view.animation.TranslateAnimation
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.mohaeyo.mohae.BR
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.base.DataBindingFragment
import com.mohaeyo.mohae.databinding.FragmentGroupDetailBinding
import com.mohaeyo.mohae.doBackAnimation
import com.mohaeyo.mohae.doCommonAnimation
import com.mohaeyo.mohae.model.GroupModel
import com.mohaeyo.mohae.viewmodel.main.group.GroupViewModel
import kotlinx.android.synthetic.main.fragment_group_detail.*

class GroupDetailFragment: DataBindingFragment<FragmentGroupDetailBinding>() {

    private val viewModel: GroupViewModel by navGraphViewModels(R.id.group_nav_graph)

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