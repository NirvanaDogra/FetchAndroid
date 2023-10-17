package com.example.fetchassesment.model

import io.reactivex.Observable
import retrofit2.http.GET

interface HiringApiService {
    companion object {
        const val BASE_URL = "https://fetch-hiring.s3.amazonaws.com/"
    }

    @GET("hiring.json")
    fun getHiringData(): Observable<List<HiringApiServiceDataModel>>
}
