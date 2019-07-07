package com.android.fruitlistapplication.dagger

import android.app.Application
import com.android.fruitlistapplication.FruitApplication
import com.android.fruitlistapplication.network.NetWorkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = arrayOf(
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityModule::class,
        NetWorkModule::class
    )
)
@Singleton
interface AppComponent : AndroidInjector<FruitApplication> {

    fun injectApp(application: FruitApplication)
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }


}



