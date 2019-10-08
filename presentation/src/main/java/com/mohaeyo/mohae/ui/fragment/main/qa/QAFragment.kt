package com.mohaeyo.mohae.ui.fragment.main.qa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.base.EndPointFragment

class QAFragment: EndPointFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_qa, container, false)
    }
}