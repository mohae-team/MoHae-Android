package com.mohaeyo.mohae.ui.fragment.main.group

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.adapter.GroupListAdapter
import com.mohaeyo.mohae.base.EndPointFragment
import com.mohaeyo.mohae.databinding.FragmentGroupListBinding
import com.mohaeyo.mohae.viewmodel.main.group.GroupViewModel
import com.mohaeyo.mohae.viewmodel.main.group.GroupViewModelFactory
import javax.inject.Inject

class GroupListFragment: EndPointFragment<FragmentGroupListBinding>() {

    @Inject
    lateinit var factory: GroupViewModelFactory

    private val viewModel by lazy { ViewModelProviders.of(this, factory).get(GroupViewModel::class.java) }

    override val layoutId: Int
        get() = R.layout.fragment_group_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModelProvider = ViewModelProvider(
            NavHostFragment.findNavController(this).getViewModelStoreOwner(R.id.group_nav_graph))

        viewModelProvider.get(viewModel::class.java)

        binding.vm = viewModel

        binding.groupList.layoutManager = LinearLayoutManager(context)
        binding.groupList.adapter = GroupListAdapter(viewModel)
    }
}