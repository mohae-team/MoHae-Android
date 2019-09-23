package com.mohaeyo.mohae.ui.fragment.login

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.backButtonSubject
import com.mohaeyo.mohae.base.DataBindingFragment
import com.mohaeyo.mohae.databinding.FragmentLoginBinding
import com.mohaeyo.mohae.viewmodel.login.LoginViewModel
import com.mohaeyo.mohae.viewmodel.login.LoginViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast
import javax.inject.Inject

class LoginFragment: DataBindingFragment<FragmentLoginBinding>() {

    @Inject
    lateinit var factory: LoginViewModelFactory

    private val viewModel by lazy { ViewModelProviders.of(this, factory).get(LoginViewModel::class.java) }

    override val layoutId: Int
        get() = R.layout.fragment_login

    private val backButtonSubjectDisposable: Disposable = backButtonSubject
        .buffer(2, 1)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe {
            if (it[1] - it[0] <= 1500) activity?.finish()
            else toast("뒤로가기 버튼을 한 번 더 누르시면 종료됩니다.")
        }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        requireActivity().onBackPressedDispatcher.addCallback(
            this, object: OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    backButtonSubject.onNext(System.currentTimeMillis())
                }
            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        observeViewModelEvent()
    }

    override fun onDestroy() {
        super.onDestroy()
        backButtonSubjectDisposable.dispose()
    }

    private fun observeViewModelEvent() {
        viewModel.startMainEvent.observe(this, Observer {
            findNavController(view!!).navigate(R.id.action_loginFragment_to_main_fragment)
        })

        viewModel.startSignUpEvent.observe(this, Observer {
            findNavController(view!!).navigate(R.id.action_loginFragment_to_signUpFragment)
        })
    }
}