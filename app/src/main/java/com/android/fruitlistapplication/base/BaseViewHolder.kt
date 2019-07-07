package com.android.fruitlistapplication

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.android.fruitlistapplication.base.OnClick

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    protected val context: Context

    init {
        context = itemView.context
    }

    abstract fun onBindData(dataModel: T, onClickListener: OnClick<T>)
}