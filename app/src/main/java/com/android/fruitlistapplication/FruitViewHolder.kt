package com.android.fruitlistapplication

import android.view.View
import com.android.fruitlistapplication.base.OnClick
import com.android.fruitlistapplication.model.FruitItem
import kotlinx.android.synthetic.main.fruit_list_row.view.*

class FruitViewHolder<T>(val view: View) : BaseViewHolder<FruitItem>(view) {
    override fun onBindData(dataModel: FruitItem, onClickListener: OnClick<FruitItem>) {
        itemView.txtFruitName.text = dataModel.type


        itemView.setOnClickListener {
            onClickListener.onClick(dataModel)

        }
    }
}