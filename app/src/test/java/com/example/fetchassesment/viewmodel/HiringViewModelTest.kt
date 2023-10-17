package com.example.fetchassesment.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.fetchassesment.model.BaseHiringListDataModel
import com.example.fetchassesment.model.SectionListDataModel
import com.example.fetchassesment.usecase.HiringUseCase
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class HiringViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: HiringViewModel

    @Mock
    private lateinit var useCase: HiringUseCase

    @Mock
    lateinit var stateObserver:Observer<HiringState>

    @Mock
    lateinit var hiringDataObserver:Observer<List<BaseHiringListDataModel>>


    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        RxAndroidPlugins.setMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        viewModel = HiringViewModel()
        viewModel.useCase = useCase
        viewModel.sateLiveData.observeForever(stateObserver)
        viewModel.hiringLiveData.observeForever(hiringDataObserver)
    }

    @Test
    fun `given useCase getHiringData return success when getHiringData is called then asset the array values`() {
        // given
        val expectedData = listOf<BaseHiringListDataModel>(
            SectionListDataModel(1, "3", "name")
        )
        val stateCaptor = ArgumentCaptor.forClass(HiringState::class.java)
        val hiringDataCaptor = ArgumentCaptor.forClass(List::class.java) as ArgumentCaptor<List<BaseHiringListDataModel>>

        Mockito.`when`(useCase.getHiringData()).thenReturn(Observable.just(expectedData))
        // when
        viewModel.getHiringData()

        // then
        verify(stateObserver, times(2)).onChanged(stateCaptor.capture())
        assertEquals(HiringState.SUCCESS, stateCaptor.value)

        verify(hiringDataObserver).onChanged(hiringDataCaptor.capture())
        assertEquals(expectedData, hiringDataCaptor.value)
    }

    @Test
    fun `given useCase getHiringData return error when getHiringData is called then asset error`() {
        // given
        val stateCaptor = ArgumentCaptor.forClass(HiringState::class.java)
        val hiringDataCaptor = ArgumentCaptor.forClass(List::class.java) as ArgumentCaptor<List<BaseHiringListDataModel>>

        Mockito.`when`(useCase.getHiringData()).thenReturn(Observable.error(Throwable("error")))
        // when
        viewModel.getHiringData()

        // then
        verify(stateObserver, times(2)).onChanged(stateCaptor.capture())
        assertEquals(HiringState.ERROR, stateCaptor.value)

        verify(hiringDataObserver, times(0)).onChanged(hiringDataCaptor.capture())
    }

    @After
    fun tearDown() {
        viewModel.sateLiveData.removeObserver(stateObserver)
        viewModel.hiringLiveData.removeObserver(hiringDataObserver)
    }
}
