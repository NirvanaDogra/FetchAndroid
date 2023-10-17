package com.example.fetchassesment.usecase

import com.example.fetchassesment.model.HiringApiService
import com.example.fetchassesment.model.HiringApiServiceDataModel
import com.example.fetchassesment.model.SectionHeadingDataModel
import com.example.fetchassesment.model.SectionListDataModel
import io.reactivex.Observable
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.`when` as mockitoWhen

@RunWith(MockitoJUnitRunner::class)
internal class HiringUseCaseImplTest {
    private lateinit var hiringUseCase: HiringUseCase
    @Mock
    lateinit var service: HiringApiService

    @Before
    fun setup() {
        hiringUseCase = HiringUseCaseImpl(service)
    }
    @Test
    fun given_getHiringData_return_empty_list_when_getHiringData_is_called_then_assert_empty() {
        // give
        mockitoWhen(service.getHiringData()).thenReturn(Observable.just(listOf()))
        // when
        val result = hiringUseCase.getHiringData().test().values()[0]
        // then
        assertEquals(listOf<HiringApiServiceDataModel>(), result)
    }

    @Test
    fun given_getHiringData_return_null_as_name_list_when_getHiringData_is_called_then_assert_empty() {
        // give
        val data = listOf(
            HiringApiServiceDataModel(1, "ID1", null)
        )
        mockitoWhen(service.getHiringData()).thenReturn(Observable.just(data))
        // when
        val result = hiringUseCase.getHiringData().test().values()[0]
        // then
        assertEquals(listOf<HiringApiServiceDataModel>(), result)
    }

    @Test
    fun given_getHiringData_return_null_string_as_name_list_when_getHiringData_is_called_then_assert_empty() {
        // give
        val data = listOf(
            HiringApiServiceDataModel(1, "ID1", "null")
        )
        mockitoWhen(service.getHiringData()).thenReturn(Observable.just(data))
        // when
        val result = hiringUseCase.getHiringData().test().values()[0]
        // then
        assertEquals(listOf<HiringApiServiceDataModel>(), result)
    }

    @Test
    fun given_getHiringData_return_empty_string_as_name_list_when_getHiringData_is_called_then_assert_empty() {
        // give
        val data = listOf(
            HiringApiServiceDataModel(1, "ID1", "")
        )
        mockitoWhen(service.getHiringData()).thenReturn(Observable.just(data))
        // when
        val result = hiringUseCase.getHiringData().test().values()[0]
        // then
        assertEquals(listOf<HiringApiServiceDataModel>(), result)
    }

    @Test
    fun given_getHiringData_returns_list_when_getHiringData_is_called_then_assert_empty() {
        // give
        val data = listOf(
            HiringApiServiceDataModel(1, "ID1", "name1"),
            HiringApiServiceDataModel(2, "ID2", "name2"),
            HiringApiServiceDataModel(3, "ID3", "name3"),
            HiringApiServiceDataModel(4, "ID4", "name4")
        )
        mockitoWhen(service.getHiringData()).thenReturn(Observable.just(data))
        // when
        val result = hiringUseCase.getHiringData().test().values()[0]
        // then
        val expectedResult = listOf(
            SectionHeadingDataModel("ID1"),
            SectionListDataModel(1, "ID1", "name1"),
            SectionHeadingDataModel("ID2"),
            SectionListDataModel(2, "ID2", "name2"),
            SectionHeadingDataModel("ID3"),
            SectionListDataModel(3, "ID3", "name3"),
            SectionHeadingDataModel("ID4"),
            SectionListDataModel(4, "ID4", "name4")
        )
        assertEquals(expectedResult, result)
    }
}