package com.mohaeyo.mohae.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.mohaeyo.mohae.BR
import com.mohaeyo.mohae.databinding.ItemFeedbackListBinding
import com.mohaeyo.mohae.model.FeedbackModel
import com.mohaeyo.mohae.viewmodel.main.feedback.list.FeedbackListViewModel

class FeedbackListAdapter(private val viewModel: FeedbackListViewModel): RecyclerView.Adapter<FeedbackListAdapter.FeedbackListViewHolder>() {

    private var feedbackItems = ArrayList<FeedbackModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedbackListViewHolder {
        val binding
                = ItemFeedbackListBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        binding.vm = viewModel
        return FeedbackListViewHolder(binding)
    }


    override fun getItemCount(): Int = feedbackItems.size

    override fun onBindViewHolder(holder: FeedbackListViewHolder, position: Int)
            = holder.bind(feedbackItems[position])

    fun setItem(feedbackItems: MutableLiveData<ArrayList<FeedbackModel>>) {
        this.feedbackItems = feedbackItems.value!!
        notifyDataSetChanged()
    }

    inner class FeedbackListViewHolder(val binding: ItemFeedbackListBinding): RecyclerView.ViewHolder(binding.root.rootView) {

        fun bind(feedback: FeedbackModel) {
            binding.setVariable(BR.feedbackItem, feedback)
        }

    }
}