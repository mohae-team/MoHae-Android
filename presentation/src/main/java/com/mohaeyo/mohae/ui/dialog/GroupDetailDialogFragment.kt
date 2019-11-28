package com.mohaeyo.mohae.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.base.DataBindingDialogFragment
import com.mohaeyo.mohae.databinding.FragmentGroupDetailDialogBinding
import com.mohaeyo.mohae.viewmodel.main.group.detail.GroupDetailViewModel
import com.mohaeyo.mohae.viewmodel.main.group.detail.GroupDetailViewModelFactory
import javax.inject.Inject

class GroupDetailDialogFragment: DataBindingDialogFragment<FragmentGroupDetailDialogBinding>() {
    @Inject
    lateinit var factory: GroupDetailViewModelFactory

    private val viewModel by lazy { ViewModelProviders.of(this, factory).get(GroupDetailViewModel::class.java) }

    override val layoutId: Int
        get() = R.layout.fragment_group_detail_dialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.selectedGroupId.value = arguments!!.getInt("id")
        viewModel.closeDialog.observe(this, Observer { dismiss() })

        binding.vm = viewModel
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}