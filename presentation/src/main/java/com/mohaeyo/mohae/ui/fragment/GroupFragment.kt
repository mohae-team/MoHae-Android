package com.mohaeyo.mohae.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.base.DataBindingFragment
import com.mohaeyo.mohae.databinding.FragmentGroupBinding
import com.mohaeyo.mohae.viewmodel.GroupViewModel
import com.mohaeyo.mohae.viewmodel.facotry.GroupViewModelFactory
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