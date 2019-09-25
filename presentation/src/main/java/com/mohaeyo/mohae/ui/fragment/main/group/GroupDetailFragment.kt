package com.mohaeyo.mohae.ui.fragment.main.group

import android.os.Bundle
import android.view.View
import androidx.navigation.navGraphViewModels
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.base.DataBindingFragment
import com.mohaeyo.mohae.databinding.FragmentGroupDetailBinding
import com.mohaeyo.mohae.viewmodel.main.group.GroupViewModel

class GroupDetailFragment: DataBindingFragment<FragmentGroupDetailBinding>() {

    private val viewModel: GroupViewModel by navGraphViewModels(R.id.group_nav_graph)

    override val layoutId: Int
        get() = R.layout.fragment_group_detail


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vm = viewModel
    }
}