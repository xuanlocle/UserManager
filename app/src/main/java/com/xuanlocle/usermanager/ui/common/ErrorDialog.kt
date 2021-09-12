package com.xuanlocle.usermanager.ui.common

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.xuanlocle.usermanager.R
import kotlinx.android.synthetic.main.error_dialog.*

class ErrorDialog : DialogFragment() {
    private var layout: Int = R.layout.error_dialog

    private lateinit var mTitle: String
    private lateinit var mContent: String
    private var negativeListener: (() -> Unit)? = null
    fun init(title: String = "", content: String = ""): ErrorDialog {
        this.mTitle = title
        this.mContent = content
        return this
    }

    fun setNegativeListener(negative: () -> Unit): ErrorDialog {
        negativeListener = negative
        return this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindListener()
        bindInfo()
    }

    private fun bindInfo() {
        if (mTitle.isNotEmpty())
            tvTitle.text = mTitle
        if (mContent.isNotEmpty())
            tvContent.text = mContent

    }


    private fun bindListener() {
        btnOK.setOnClickListener {
            negativeListener?.invoke()
            this.dismiss()
        }

        vRootDialog.setOnClickListener {
            negativeListener?.invoke()
            this.dismiss()
        }

    }

    override fun onStart() {
        val dialog = dialog
        dialog?.let {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            it.window?.setLayout(width, height)
        }
        super.onStart()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.let {
            it.requestFeature(Window.FEATURE_NO_TITLE)
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        return inflater.inflate(layout, container, true)
    }


}