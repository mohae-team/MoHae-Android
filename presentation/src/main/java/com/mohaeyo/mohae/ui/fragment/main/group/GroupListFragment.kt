package com.mohaeyo.mohae.ui.fragment.main.group

import android.graphics.drawable.Animatable
import android.os.Bundle
import android.view.View
import android.view.animation.TranslateAnimation
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.adapter.GroupListAdapter
import com.mohaeyo.mohae.base.EndPointFragment
import com.mohaeyo.mohae.databinding.FragmentGroupListBinding
import com.mohaeyo.mohae.doBackAnimation
import com.mohaeyo.mohae.doCommonAnimation
import com.mohaeyo.mohae.model.GroupModel
import com.mohaeyo.mohae.viewmodel.main.group.GroupViewModel
import com.mohaeyo.mohae.viewmodel.main.group.GroupViewModelFactory
import kotlinx.android.synthetic.main.fragment_group_list.*
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

        observeEvent()
        binding.vm = viewModel

        binding.groupList.layoutManager = LinearLayoutManager(context)
        binding.groupList.adapter = GroupListAdapter(viewModel)
    }

    private fun observeEvent() {
        viewModel.startListToDocEvent.observe(this, Observer {
            group_list_add_fab.doCommonAnimation(R.drawable.add_to_check)
            group_list_back_fab.doBackAnimation(true)
            findNavController().navigate(R.id.action_groupListFragment_to_groupDocFragment)
        })

        viewModel.startListToDetailEvent.observe(this, Observer {
            group_list_add_fab.doCommonAnimation(R.drawable.add_to_swap_horiz)
            group_list_back_fab.doBackAnimation(true)
            findNavController().navigate(R.id.action_groupListFragment_to_groupDetailFragment, getGroupItemBundle(it))
        })
    }

    private fun getGroupItemBundle(groupModel: GroupModel): Bundle {
        val bundle = Bundle()

        with(groupModel) {
            bundle.putString("title", title)
            bundle.putString("address", address)
            bundle.putString("term", term)
            bundle.putString("imageUrl", imageUrl)
            bundle.putString("count", count)
            bundle.putString("description", description)
        }

        return bundle
    }
}