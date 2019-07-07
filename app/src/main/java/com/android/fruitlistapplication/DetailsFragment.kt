package com.android.fruitlistapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.fruitlistapplication.model.FruitItem
import kotlinx.android.synthetic.main.fragment_fruit_details.*
import kotlinx.android.synthetic.main.fragment_fruit_details.toolbar
import kotlinx.android.synthetic.main.toolbar.view.*


class DetailsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_fruit_details, container, false)
    }


    @SuppressLint("SetTextI18n", "PrivateResource")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_material)

        val dataModel = arguments!!.getSerializable("data") as FruitItem
        mTextViewType.text = "Type : " + dataModel.type
        mTextViewWeight.text = "Weight : " + dataModel.weight.toString()
        mTextViewPrice.text = "Price :" + dataModel.price.toString() + " (" +
                String.format("%.2f", dataModel.priceKg) + " £/Kg )"
        toolbar.toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

    }
}