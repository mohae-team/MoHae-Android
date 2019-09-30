package com.mohaeyo.mohae.ui.fragment.main.feedback

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.base.DataBindingFragment
import com.mohaeyo.mohae.databinding.FragmentFeedbackDetailBinding
import com.mohaeyo.mohae.doBackAnimation
import com.mohaeyo.mohae.doCommonAnimation
import com.mohaeyo.mohae.model.FeedbackModel
import com.mohaeyo.mohae.ui.dialog.FeedbackDetailDialogFragment
import com.mohaeyo.mohae.viewmodel.main.feedback.detail.FeedbackDetailViewModel
import com.mohaeyo.mohae.viewmodel.main.feedback.detail.FeedbackDetailViewModelFactory
import kotlinx.android.synthetic.main.fragment_feedback_detail.*
import javax.inject.Inject

class FeedbackDetailFragment: DataBindingFragment<FragmentFeedbackDetailBinding>() {

    @Inject
    lateinit var factory: FeedbackDetailViewModelFactory

    private val viewModel by lazy { ViewModelProviders.of(this, factory).get(FeedbackDetailViewModel::class.java) }

    override val layoutId: Int
        get() = R.layout.fragment_feedback_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getArgFeedbackItem()
        observeEvent()

        binding.vm = viewModel
    }

    private fun observeEvent() {
        viewModel.startDetailToListEvent.observe(this, Observer {
            feedback_detail_like_or_hate_fab.doCommonAnimation(R.drawable.like_or_hate_to_add)
            feedback_detail_back_fab.doBackAnimation(false)
            findNavController().navigate(R.id.action_feedbackDetailFragment_to_feedbackListFragment)
        })

        viewModel.startDetailToDialogEvent.observe(this, Observer {
            FeedbackDetailDialogFragment().show(fragmentManager!!, "detail")
        })
    }

    private fun getArgFeedbackItem() {
        viewModel.selectedFeedbackItem.value = FeedbackModel(
            title = arguments!!.getString("title")!!,
            address = arguments!!.getString("address")!!,
            summary = arguments!!.getString("summary")!!,
            imageUrl = arguments!!.getString("imageUrl")!!,
            description = arguments!!.getString("description")!!,
            like = arguments!!.getString("like")!!,
            hate = arguments!!.getString("hate")!!)
    }
}