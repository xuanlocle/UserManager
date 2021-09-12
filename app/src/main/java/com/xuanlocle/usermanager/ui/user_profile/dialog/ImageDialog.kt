package com.xuanlocle.usermanager.ui.user_profile.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.xuanlocle.usermanager.R
import com.xuanlocle.usermanager.util.image.ImageHelper
import kotlinx.android.synthetic.main.image_detail_dialog.*


class ImageDialog : DialogFragment() {
    private lateinit var imageUrl: String
    private var layout: Int = R.layout.image_detail_dialog
    private var isShowingTabBar = true

    private lateinit var ownerImageName: String
    private var createdAt: Int = 0

    fun init(name: String = "", createdAt: Int = -1): ImageDialog {
        this.ownerImageName = name
        this.createdAt = createdAt
        return this
    }

    fun setImageUrl(url: String): ImageDialog {
        this.imageUrl = url
        return this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindListener()
        bindImage()
        bindInfo()
    }

    private fun bindInfo() {
        if (ownerImageName.isNotEmpty())
            tvName.text = ownerImageName
    }

    private fun bindImage() {
        imvDetails.maxScale = 12f
        ImageHelper.loadImageZoomable(imageUrl, imvDetails)
    }


    private fun bindListener() {
        vImageTopBar.setOnClickListener {}
        imvBack.setOnClickListener { this.dismiss() }

        vRootDialog.setOnClickListener {
            if (isShowingTabBar) {
                hideTabBars()
            } else {
                showTabBars()
            }

            isShowingTabBar = !isShowingTabBar
        }

    }

    private fun hideTabBars() {
        vImageTopBar.visibility = View.INVISIBLE
    }

    private fun showTabBars() {
        vImageTopBar.visibility = View.VISIBLE
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