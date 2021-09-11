package com.xuanlocle.usermanager.ui.user_list.item

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.xuanlocle.usermanager.util.image.ImageHelper
import kotlinx.android.synthetic.main.item_user_single.view.*

class UserRecyclerVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindAvatar(avatarURL: String) {
        ImageHelper.loadImageShapeable(avatarURL, itemView.imvAvatar)
    }

    fun bindName(login: String) {
        itemView.tvTitle.text = login
    }

    fun bindDescription(htmlURL: String) {
        itemView.tvDescription.text = htmlURL
    }

    fun bindListener(htmlURL: String, listener: UserRecyclerItemListener) {
        itemView.setOnClickListener {
            listener.onClickUserItem(htmlURL)
        }
    }

}

