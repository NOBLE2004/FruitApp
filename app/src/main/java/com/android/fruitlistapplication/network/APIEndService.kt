package com.android.fruitlistapplication.network

import com.android.fruitlistapplication.model.Response
import io.reactivex.Single
import retrofit2.http.GET


interface APIEndService {
    @GET("fmtvp/recruit-test-data/master/data.json")
    fun fruitsList(): Single<Response>
}