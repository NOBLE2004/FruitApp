package com.android.fruitlistapplication

import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import com.android.fruitlistapplication.base.BaseActivity


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

    }

    private fun init() {
        val fragment = HomeFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.baseContainer, fragment, "HomeFragment")
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

    }
}
