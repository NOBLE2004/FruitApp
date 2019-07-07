package com.android.fruitlistapplication

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.fruitlistapplication.base.OnClick
import com.android.fruitlistapplication.model.FruitItem



class FruitAdapter : RecyclerView.Adapter<FruitViewHolder<FruitItem>> {
    private var fruitList: List<FruitItem>
    private var onClick: OnClick<FruitItem>

    constructor(fruitList: List<FruitItem>, onClick: OnClick<FruitItem>) : super() {
        this.fruitList = fruitList
        this.onClick = onClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): FruitViewHolder<FruitItem> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fruit_list_row,
            parent, false)
        return FruitViewHolder(view)
    }

    override fun getItemCount(): Int {
        return fruitList.size
    }

    override fun onBindViewHolder(viewHolder: FruitViewHolder<FruitItem>, pos: Int) {
        viewHolder.onBindData(fruitList[pos], onClick)
    }

}