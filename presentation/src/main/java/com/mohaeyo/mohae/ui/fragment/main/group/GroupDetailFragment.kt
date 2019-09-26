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
import com.mohaeyo.mohae.model.GroupModel
import com.mohaeyo.mohae.viewmodel.main.group.GroupViewModel
import kotlinx.android.synthetic.main.fragment_group_detail.*

class GroupDetailFragment: DataBindingFragment<FragmentGroupDetailBinding>() {

    private val viewModel: GroupViewModel by navGraphViewModels(R.id.group_nav_graph)

    override val layoutId: Int
        get() = R.layout.fragment_group_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.selectedGroupItem.value = GroupModel(
            title = arguments!!.getString("title")!!,
            address = arguments!!.getString("address")!!,
            term = arguments!!.getString("term")!!,
            imageUrl = arguments!!.getString("imageUrl")!!,
            description = arguments!!.getString("description")!!,
            count = arguments!!.getString("count")!!)

        observeEvent()
        binding.vm = viewModel
    }

    private fun observeEvent() {
        viewModel.startDetailToListEvent.observe(this, Observer {
            doFabAnimation(R.drawable.swap_horiz_to_add)
            findNavController().navigate(R.id.action_groupDetailFragment_to_groupListFragment)
        })
    }

    private fun doFabAnimation(resId: Int) {
        val avd = AnimatedVectorDrawableCompat.create(context!!, resId)
        group_detail_join_fab.setImageDrawable(avd)
        (group_detail_join_fab.drawable as Animatable).start()

        val anim = TranslateAnimation(0f, 1000f, 0f, 0f)
        anim.duration = 400
        group_detail_back_fab.startAnimation(anim)
    }
}