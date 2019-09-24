package com.mohaeyo.mohae.ui.fragment.main.feedback

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.backButtonSubject
import com.mohaeyo.mohae.base.DataBindingFragment
import com.mohaeyo.mohae.base.EndPointFragment
import com.mohaeyo.mohae.databinding.FragmentFeedbackBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import org.jetbrains.anko.support.v4.toast


class FeedbackFragment: EndPointFragment<FragmentFeedbackBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_feedback

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }
}