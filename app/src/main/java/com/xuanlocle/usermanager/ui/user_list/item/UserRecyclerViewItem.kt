package com.xuanlocle.usermanager.ui.user_list.item

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.xuanlocle.usermanager.R
import com.xuanlocle.usermanager.data.model.UserModel
import com.xuanlocle.usermanager.ui.adapter.item.RecyclerViewItem

class RepoRecyclerViewItem(
    private val userModel: UserModel,
    private val listener: UserRecyclerItemListener?
) : RecyclerViewItem<UserRecyclerVH>() {

    override fun bind(viewHolder: UserRecyclerVH) {
        viewHolder.let { vh ->
            if (userModel.avatarURL.isNotEmpty())
                vh.bindAvatar(userModel.avatarURL)

            if(userModel.login.isNotEmpty()){
                vh.bindName(userModel.login)
            }

            if(userModel.htmlURL.isNotEmpty()){
                vh.bindDescription(userModel.htmlURL)

                if(listener != null){
                    vh.bindListener(userModel.login, listener)
                }
            }
        }
    }

    override fun createViewHolder(context: Context): RecyclerView.ViewHolder {
        return UserRecyclerVH(LayoutInflater.from(context).inflate(R.layout.item_user_single, null))
    }
}

interface UserRecyclerItemListener {
    fun onClickUserItem(userLoginId: String)
}

