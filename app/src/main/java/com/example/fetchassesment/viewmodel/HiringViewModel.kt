package com.example.fetchassesment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fetchassesment.di.DaggerHiringComponent
import com.example.fetchassesment.model.BaseHiringListDataModel
import com.example.fetchassesment.usecase.HiringUseCase
import com.example.fetchassesment.viewmodel.HiringState.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HiringViewModel() : ViewModel() {
    companion object {
        private const val TAG = "HiringViewModel"
    }

    private var mutableHiringLiveData = MutableLiveData<List<BaseHiringListDataModel>>()
    val hiringLiveData: LiveData<List<BaseHiringListDataModel>>
        get() = mutableHiringLiveData

    private var mutableSateLiveData = MutableLiveData<HiringState>()
    val sateLiveData: LiveData<HiringState>
        get() = mutableSateLiveData

    @Inject
    lateinit var useCase: HiringUseCase
    private lateinit var disposable: Disposable

    init {
        DaggerHiringComponent.builder().build().inject(this)
    }

     fun getHiringData() {
        mutableSateLiveData.value = LOADING
        disposable = useCase.getHiringData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d(TAG, it.toString())
                mutableSateLiveData.value = SUCCESS
                mutableHiringLiveData.value = it
            }, {
                Log.d(TAG, it.toString())
                mutableSateLiveData.value = ERROR
            })
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}