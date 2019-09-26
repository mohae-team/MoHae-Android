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
            doFabAnimation(R.drawable.add_to_check)
            findNavController().navigate(R.id.action_groupListFragment_to_groupDocFragment)
        })

        viewModel.startListToDetailEvent.observe(this, Observer {
            doFabAnimation(R.drawable.add_to_swap_horiz)

            val bundle = Bundle()

            bundle.putString("title", it.title)
            bundle.putString("address", it.address)
            bundle.putString("term", it.term)
            bundle.putString("imageUrl", it.imageUrl)
            bundle.putString("count", it.count)
            bundle.putString("description", it.description)

            findNavController().navigate(R.id.action_groupListFragment_to_groupDetailFragment, bundle)
        })
    }

    private fun doFabAnimation(resId: Int) {
        val avd = AnimatedVectorDrawableCompat.create(context!!, resId)
        group_list_add_fab.setImageDrawable(avd)
        (group_list_add_fab.drawable as Animatable).start()

        val anim = TranslateAnimation(0f, -1000f, 0f, 0f)
        anim.duration = 400
        group_list_back_fab.startAnimation(anim)
    }
}