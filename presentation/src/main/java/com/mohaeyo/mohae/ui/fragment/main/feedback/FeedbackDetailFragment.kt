package com.mohaeyo.mohae.ui.fragment.main.feedback

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
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

    override val viewModel by lazy { ViewModelProviders.of(this, factory).get(FeedbackDetailViewModel::class.java) }

    override val layoutId: Int
        get() = R.layout.fragment_feedback_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        getArgFeedbackItem()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        requireActivity().onBackPressedDispatcher.addCallback(
            this, object: OnBackPressedCallback(true) {
                override fun handleOnBackPressed() = backToList()
            })
    }

    override fun observeEvent() {
        viewModel.startDetailToListEvent.observe(this, Observer { backToList() })

        viewModel.startDetailToDialogEvent.observe(this, Observer {
            val dialog = FeedbackDetailDialogFragment()
            val bundle = Bundle()

            bundle.putInt("id", viewModel.selectedFeedbackId.value!!)
            dialog.arguments = bundle
            dialog.show(fragmentManager!!, "detail")
        })

        viewModel.selectedFeedbackItem.observe(this, Observer {
            Glide.with(feedback_detail_image_imv)
                .load(it.imageFile.toString())
                .into(feedback_detail_image_imv)
        })
    }

    private fun backToList() {
        feedback_detail_like_or_hate_fab.doCommonAnimation(R.drawable.like_or_hate_to_add)
        feedback_detail_back_fab.doBackAnimation(false)
        findNavController().navigate(R.id.action_feedbackDetailFragment_to_feedbackListFragment)
    }

    private fun getArgFeedbackItem() {
        viewModel.selectedFeedbackId.value = arguments!!.getInt("id")
    }
}