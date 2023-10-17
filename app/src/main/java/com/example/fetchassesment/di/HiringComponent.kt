package com.example.fetchassesment.di

import com.example.fetchassesment.viewmodel.HiringViewModel
import dagger.Component

@Component(modules = [HiringModule::class])
interface HiringComponent {
    fun inject(hiringViewModel: HiringViewModel)
}