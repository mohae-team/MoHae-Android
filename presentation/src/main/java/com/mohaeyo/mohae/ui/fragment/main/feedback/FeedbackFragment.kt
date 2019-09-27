package com.mohaeyo.mohae.ui.fragment.main.feedback

import android.os.Bundle
import android.view.View
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.base.EndPointFragment
import com.mohaeyo.mohae.databinding.FragmentFeedbackBinding


class FeedbackFragment: EndPointFragment<FragmentFeedbackBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_feedback

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }
}