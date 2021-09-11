package com.xuanlocle.usermanager.util.image

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.imageview.ShapeableImageView
import com.xuanlocle.usermanager.R

object ImageHelper {

    private fun verifyInput(input: Any?, imageView: ImageView?): Boolean {
        return input != null && imageView != null
    }

    private fun verifyInputShape(input: Any?, imageView: ShapeableImageView?): Boolean {
        return input != null && imageView != null
    }

    fun loadImage(url: String?, imageView: ImageView?) {
        if (verifyInput(url, imageView)) {
            val requestOptions = RequestOptions().dontAnimate()
            Glide.with(imageView!!.context)
                .setDefaultRequestOptions(requestOptions)
                .load(url)
//                .listener(requestListener)
                .into(imageView)
        }
    }

    fun loadImageShapeable(url: String?, imageView: ShapeableImageView?) {
        if (verifyInput(url, imageView)) {
            val requestOptions = RequestOptions().dontAnimate().placeholder(R.color.grey_f6)
            Glide.with(imageView!!.context)
                .setDefaultRequestOptions(requestOptions)
                .load(url)
//                .listener(requestListener)
                .into(imageView)
        }
    }
}