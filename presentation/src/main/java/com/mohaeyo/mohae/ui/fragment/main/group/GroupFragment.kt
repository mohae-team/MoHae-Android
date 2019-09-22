package com.mohaeyo.mohae.ui.fragment.main.group

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.base.DataBindingFragment
import com.mohaeyo.mohae.databinding.FragmentGroupBinding
import com.mohaeyo.mohae.viewmodel.main.group.GroupViewModel
import com.mohaeyo.mohae.viewmodel.main.group.GroupViewModelFactory
import javax.inject.Inject

class GroupFragment: DataBindingFragment<FragmentGroupBinding>() {

    @Inject
    lateinit var factory: GroupViewModelFactory

    private val viewModel by lazy { ViewModelProviders.of(this, factory).get(GroupViewModel::class.java) }

    override val layoutId: Int
        get() = R.layout.fragment_group

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vm = viewModel
    }
}