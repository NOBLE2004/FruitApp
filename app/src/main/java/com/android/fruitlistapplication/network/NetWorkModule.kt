package com.android.fruitlistapplication.network
import com.android.fruitlistapplication.BuildConfig
import com.android.fruitlistapplication.dagger.AppModule
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module(includes = arrayOf(AppModule::class))
open class NetWorkModule {

    @Provides
    @Singleton
    fun prepareHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).addInterceptor { chain ->
            val request = chain.request()
            if (null != request) {
                val newRequest = request.newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .build()
                return@addInterceptor chain.proceed(newRequest)
            }
            null
        }.build()
    }

    @Provides
    @Singleton
    fun prepareRetrofitService(): APIEndService {
        val retrofit = Retrofit.Builder().client(prepareHttpClient())
            .addCallAdapterFactory(
                RxJava2CallAdapterFactory
                    .createWithScheduler(Schedulers.newThread())
            )
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL).build()
        return retrofit.create(APIEndService::class.java)
    }
}