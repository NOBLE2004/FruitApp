package com.android.fruitlistapplication.base

import android.content.Context
import android.support.v4.app.Fragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

open class BaseFragment : Fragment(), HasSupportFragmentInjector {
    @Inject
    lateinit var supportFragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment>? {
        return supportFragmentDispatchingAndroidInjector
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

}