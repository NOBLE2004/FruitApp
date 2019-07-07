package com.android.fruitlistapplication.dagger


import com.android.fruitlistapplication.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class, FragmentProvider::class))
    internal abstract fun bindMainActivity(): MainActivity
}