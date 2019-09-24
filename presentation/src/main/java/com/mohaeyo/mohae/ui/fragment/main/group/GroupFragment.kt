package com.mohaeyo.mohae.ui.fragment.main.group

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProviders
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.backButtonSubject
import com.mohaeyo.mohae.base.DataBindingFragment
import com.mohaeyo.mohae.base.EndPointFragment
import com.mohaeyo.mohae.databinding.FragmentGroupBinding
import com.mohaeyo.mohae.viewmodel.main.group.GroupViewModel
import com.mohaeyo.mohae.viewmodel.main.group.GroupViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

class GroupFragment: EndPointFragment<FragmentGroupBinding>() {

    @Inject
    lateinit var factory: GroupViewModelFactory

    private val viewModel by lazy { ViewModelProviders.of(this, factory).get(GroupViewModel::class.java) }

    override val layoutId: Int
        get() = R.layout.fragment_group

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vm = viewModel
    }
}