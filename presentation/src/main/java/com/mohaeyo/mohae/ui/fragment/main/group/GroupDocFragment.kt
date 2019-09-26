package com.mohaeyo.mohae.ui.fragment.main.group

import android.content.Context
import android.graphics.drawable.Animatable
import android.os.Bundle
import android.view.View
import android.view.animation.TranslateAnimation
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.base.DataBindingFragment
import com.mohaeyo.mohae.databinding.FragmentGroupDocBinding
import com.mohaeyo.mohae.viewmodel.main.group.GroupViewModel
import kotlinx.android.synthetic.main.fragment_group_doc.*

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

        observeEvent()
        binding.vm = viewModel
    }

    private fun observeEvent() {
        viewModel.startDocToListEvent.observe(this, Observer {
            doFabAnimation(R.drawable.check_to_add)
            findNavController().navigate(R.id.action_groupDocFragment_to_groupListFragment)
        })
    }

    private fun doFabAnimation(resId: Int) {
        val avd = AnimatedVectorDrawableCompat.create(context!!, resId)
        group_doc_post_fab.setImageDrawable(avd)
        (group_doc_post_fab.drawable as Animatable).start()

        val anim = TranslateAnimation(0f, 1000f, 0f, 0f)
        anim.duration = 400
        group_doc_back_fab.startAnimation(anim)
    }
}