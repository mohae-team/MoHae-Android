package com.mohaeyo.mohae.ui.fragment.main.mypage

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.net.toFile
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.base.BaseLocationFragment
import com.mohaeyo.mohae.databinding.FragmentMypageProfileEditBinding
import com.mohaeyo.mohae.viewmodel.main.mypage.MyPageProfileEditViewModel
import com.mohaeyo.mohae.viewmodel.main.mypage.MyPageProfileEditViewModelFactory
import kotlinx.android.synthetic.main.fragment_mypage_profile_edit.*
import javax.inject.Inject
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mohaeyo.data.copyStreamToFile


class MyPageProfileEditFragment: BaseLocationFragment<FragmentMypageProfileEditBinding>() {

    override val mapId: Int
        get() = R.id.mypage_profile_edit_search_map

    @Inject
    lateinit var factory: MyPageProfileEditViewModelFactory

    override val viewModel
            by lazy { ViewModelProviders.of(this, factory).get(MyPageProfileEditViewModel::class.java) }

    override val layoutId: Int
        get() = R.layout.fragment_mypage_profile_edit

    override fun onAttach(context: Context) {
        super.onAttach(context)

        requireActivity().onBackPressedDispatcher.addCallback(
            this, object: OnBackPressedCallback(true) {
                override fun handleOnBackPressed() = backToProfile()
            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
    }

    override fun observeEvent() {
        viewModel.startProfileEvent.observe(this, Observer { backToProfile() })

        viewModel.getProfileImageEvent.observe(this, Observer { getProfileImage() })

        viewModel.descriptionErrorEvent.observe(this, Observer { mypage_profile_description_card_edit_lay.error = it })

        viewModel.setProfileImageEvent.observe(this, Observer {
            Glide.with(mypage_profile_edit_imv)
                .load(viewModel.userModel.value!!.imageFile.toString())
                .apply(RequestOptions.circleCropTransform())
                .into(mypage_profile_edit_imv)
        })
    }

    private fun backToProfile()
            = findNavController().navigate(R.id.action_myPageProfileEditFragment_to_myPageProfileFragment)

    private fun getProfileImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(
            Intent.createChooser(intent, "Select file to upload "), 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 0) {
            if (resultCode == RESULT_OK) {
                val selectedImageUri = data!!.data!!
                viewModel.userModel.value!!.imageFile = copyStreamToFile(context!!, selectedImageUri)
                Glide.with(mypage_profile_edit_imv)
                    .load(selectedImageUri)
                    .apply(RequestOptions.circleCropTransform())
                    .into(mypage_profile_edit_imv)
            }
        }
    }
}