package com.mohaeyo.mohae.ui.fragment.main.group

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.base.DataBindingFragment
import com.mohaeyo.mohae.databinding.FragmentGroupDetailBinding
import com.mohaeyo.mohae.doBackAnimation
import com.mohaeyo.mohae.doCommonAnimation
import com.mohaeyo.mohae.ui.dialog.GroupDetailDialogFragment
import com.mohaeyo.mohae.viewmodel.main.group.detail.GroupDetailViewModel
import kotlinx.android.synthetic.main.fragment_group_detail.*
import javax.inject.Inject

class GroupDetailFragment: DataBindingFragment<FragmentGroupDetailBinding>() {
    @Inject
    override lateinit var viewModel: GroupDetailViewModel

    override val layoutId: Int
        get() = R.layout.fragment_group_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        getArgGroupItem()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        requireActivity().onBackPressedDispatcher.addCallback(
            this, object: OnBackPressedCallback(true) {
                override fun handleOnBackPressed() = backToList()
            })
    }

    override fun observeEvent() {
        viewModel.startDetailToListEvent.observe(this, Observer { backToList() })

        viewModel.startDetailToDialogEvent.observe(this, Observer {
            val dialog = GroupDetailDialogFragment()
            val bundle = Bundle()

            bundle.putInt("id", viewModel.selectedGroupId.value!!)
            dialog.arguments = bundle
            dialog.show(fragmentManager!!, "detail")
        })
    }

    private fun backToList() {
        group_detail_join_fab.doCommonAnimation(R.drawable.swap_horiz_to_add)
        group_detail_back_fab.doBackAnimation(false)
        findNavController().navigate(R.id.action_groupDetailFragment_to_groupListFragment)
    }

    private fun getArgGroupItem() {
        viewModel.selectedGroupId.value = arguments!!.getInt("id")
    }
}