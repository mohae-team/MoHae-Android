package com.mohaeyo.mohae.ui.fragment.main.feedback

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.adapter.FeedbackListAdapter
import com.mohaeyo.mohae.base.EndPointDataBindingFragment
import com.mohaeyo.mohae.databinding.FragmentFeedbackListBinding
import com.mohaeyo.mohae.doBackAnimation
import com.mohaeyo.mohae.doCommonAnimation
import com.mohaeyo.mohae.model.FeedbackModel
import com.mohaeyo.mohae.model.GroupModel
import com.mohaeyo.mohae.viewmodel.main.feedback.list.FeedbackListViewModel
import com.mohaeyo.mohae.viewmodel.main.feedback.list.FeedbackListViewModelFactory
import kotlinx.android.synthetic.main.fragment_feedback_list.*
import javax.inject.Inject

class FeedbackListFragment: EndPointDataBindingFragment<FragmentFeedbackListBinding>() {

    @Inject
    lateinit var factory: FeedbackListViewModelFactory

    override val viewModel by lazy { ViewModelProviders.of(this, factory).get(FeedbackListViewModel::class.java) }

    override val layoutId: Int
        get() = R.layout.fragment_feedback_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        binding.feedbackList.layoutManager = LinearLayoutManager(context)
        binding.feedbackList.adapter = FeedbackListAdapter(viewModel)
    }

    override fun observeEvent() {
        viewModel.startListToDocEvent.observe(this, Observer {
            feedback_list_add_fab.doCommonAnimation(R.drawable.add_to_check)
            feedback_list_back_fab.doBackAnimation(true)
            findNavController().navigate(R.id.action_feedbackListFragment_to_feedbackDocFragment)
        })

        viewModel.listAnimationEvent.observe(this, Observer {
            binding.feedbackList.layoutAnimation =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_slide_from_bottom)
            binding.feedbackList.scheduleLayoutAnimation()
        })

        viewModel.startListToDetailEvent.observe(this, Observer {
            feedback_list_add_fab.doCommonAnimation(R.drawable.add_to_like_or_hate)
            feedback_list_back_fab.doBackAnimation(true)
            findNavController().navigate(R.id.action_feedbackListFragment_to_feedbackDetailFragment, getFeedbackItemBundle(it))
        })
    }

    private fun getFeedbackItemBundle(feedbackModel: FeedbackModel): Bundle {
        val bundle = Bundle()
        with(feedbackModel) { bundle.putInt("id", id) }
        return bundle
    }
}