package com.example.fetchassesment.di

import com.example.fetchassesment.model.HiringApiService
import com.example.fetchassesment.usecase.HiringUseCase
import com.example.fetchassesment.usecase.HiringUseCaseImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class HiringModule {
    @Provides
    fun provideHiringUseCase(hiringApiService: HiringApiService): HiringUseCase {
        return HiringUseCaseImpl(hiringApiService)
    }

    @Provides
    fun providesHiringApiService(retrofit: Retrofit): HiringApiService =
        retrofit.create(HiringApiService::class.java)


    @Provides
    fun providesRetrofit(loginClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(HiringApiService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(loginClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    @Provides
    fun provideHttpLogginClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }
}