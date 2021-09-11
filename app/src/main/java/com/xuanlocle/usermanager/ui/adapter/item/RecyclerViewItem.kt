package com.xuanlocle.usermanager.ui.adapter.item

import android.content.Context
import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerViewItem<VH : RecyclerView.ViewHolder> {

    fun getType(): Int = this::class.java.hashCode()

    abstract fun bind(viewHolder: VH)

    abstract fun createViewHolder(context: Context): RecyclerView.ViewHolder

}