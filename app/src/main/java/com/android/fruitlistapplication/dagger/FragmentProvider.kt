package com.android.fruitlistapplication.dagger



import com.android.fruitlistapplication.DetailsFragment
import com.android.fruitlistapplication.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentProvider {
    @ContributesAndroidInjector
    abstract fun bindHomeFragment(): HomeFragment
    abstract fun bindDetailsFragment(): DetailsFragment
}