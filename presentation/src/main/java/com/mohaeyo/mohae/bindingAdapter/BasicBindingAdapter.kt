package com.mohaeyo.mohae.bindingAdapter

import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.mohaeyo.mohae.adapter.GroupListAdapter
import com.mohaeyo.mohae.model.GroupModel

@BindingAdapter("groupItems")
fun RecyclerView.bindGroupItems(groupItems: MutableLiveData<ArrayList<GroupModel>>) {
    (adapter as GroupListAdapter).setItem(groupItems)
}