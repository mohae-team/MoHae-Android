package com.mohaeyo.mohae.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.mohaeyo.mohae.BR
import com.mohaeyo.mohae.databinding.ItemGroupListBinding
import com.mohaeyo.mohae.model.GroupModel
import com.mohaeyo.mohae.viewmodel.main.group.GroupViewModel

class GroupListAdapter(private val viewModel: GroupViewModel): RecyclerView.Adapter<GroupListAdapter.GroupListViewHolder>() {

    private var groupItems = ArrayList<GroupModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupListViewHolder {
        val binding
                = ItemGroupListBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        binding.vm = viewModel
        return GroupListViewHolder(binding)
    }


    override fun getItemCount(): Int = groupItems.size

    override fun onBindViewHolder(holder: GroupListViewHolder, position: Int)
            = holder.bind(groupItems[position])

    fun setItem(groupItems: MutableLiveData<ArrayList<GroupModel>>) {
        this.groupItems = groupItems.value!!
        notifyDataSetChanged()
    }

    inner class GroupListViewHolder(val binding: ItemGroupListBinding): RecyclerView.ViewHolder(binding.root.rootView) {

        fun bind(group: GroupModel) {
            binding.setVariable(BR.groupItem, group)
            binding.root.setOnClickListener { binding.vm?.groupItemClick(group) }
        }

    }
}