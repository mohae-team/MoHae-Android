package com.mohaeyo.mohae.ui.fragment.main.feedback

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.base.DataBindingFragment
import com.mohaeyo.mohae.databinding.FragmentFeedbackDocBinding
import com.mohaeyo.mohae.doBackAnimation
import com.mohaeyo.mohae.doCommonAnimation
import com.mohaeyo.mohae.viewmodel.main.feedback.doc.FeedbackDocViewModel
import com.mohaeyo.mohae.viewmodel.main.feedback.doc.FeedbackDocViewModelFactory
import kotlinx.android.synthetic.main.fragment_feedback_doc.*
import javax.inject.Inject

class FeedbackDocFragment: DataBindingFragment<FragmentFeedbackDocBinding>() {


    @Inject
    lateinit var factory: FeedbackDocViewModelFactory

    private val viewModel by lazy { ViewModelProviders.of(this, factory).get(FeedbackDocViewModel::class.java) }

    override val layoutId: Int
        get() = R.layout.fragment_feedback_doc

    override fun onAttach(context: Context) {
        super.onAttach(context)

        requireActivity().onBackPressedDispatcher.addCallback(
            this, object: OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.action_feedbackDocFragment_to_feedbackListFragment)
                }
            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeEvent()
        binding.vm = viewModel
    }

    private fun observeEvent() {
        viewModel.startDocToListEvent.observe(this, Observer {
            feedback_doc_post_fab.doCommonAnimation(R.drawable.check_to_add)
            feedback_doc_back_fab.doBackAnimation(false)
            findNavController().navigate(R.id.action_feedbackDocFragment_to_feedbackListFragment)
        })
    }
}