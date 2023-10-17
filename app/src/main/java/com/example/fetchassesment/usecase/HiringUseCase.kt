package com.example.fetchassesment.usecase

import com.example.fetchassesment.model.BaseHiringListDataModel
import io.reactivex.Observable

interface HiringUseCase {
    fun getHiringData(): Observable<List<BaseHiringListDataModel>>
}