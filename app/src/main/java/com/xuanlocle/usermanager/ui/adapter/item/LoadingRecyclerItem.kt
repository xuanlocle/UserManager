package com.xuanlocle.usermanager.ui.adapter.item

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.xuanlocle.usermanager.R

class LoadingRecyclerItem : RecyclerViewItem<LoadingRecyclerVH>() {
    override fun bind(viewHolder: LoadingRecyclerVH) {

    }

    override fun createViewHolder(context: Context): RecyclerView.ViewHolder {
        return LoadingRecyclerVH(LayoutInflater.from(context).inflate(R.layout.item_loading, null))
    }
}