package com.example.fetchassesment.usecase

import com.example.fetchassesment.model.*
import io.reactivex.Observable
import javax.inject.Inject

class HiringUseCaseImpl @Inject constructor(private val hiringApiService: HiringApiService) :
    HiringUseCase {
    /**
     * This function calls the Hiring Api and formats the list into
     * BaseHiringListDataModel that can be handled by the Adapter
     */
    override fun getHiringData(): Observable<List<BaseHiringListDataModel>> {
        return hiringApiService.getHiringData().map { formatData(it) }
    }

    private fun formatData(list1: List<HiringApiServiceDataModel>): List<BaseHiringListDataModel> {
        val list = removeNonNullOrBlankFromList(list1)
        return groupListByListId(list)
    }

    /**
     * Groups data by listID and adds section headers
     */
    private fun groupListByListId(list: List<HiringApiServiceDataModel>): MutableList<BaseHiringListDataModel> {
        val groupedHiringList = mutableListOf<BaseHiringListDataModel>()
        val groupedData = list.groupBy() { it.listId }
        for (key in groupedData.keys.sorted()) {
            groupedData[key]?.let {
                groupedHiringList.add(SectionHeadingDataModel(key))
                groupedHiringList.addAll(getSortedSectionListData(it))
            }
        }
        return groupedHiringList
    }

    /**
     * Sorts the list by id. NOTE: I am sorting by id because the name is
     * made by adding "item" and id. This help me reduce string operations
     */
    private fun getSortedSectionListData(sectionList: List<HiringApiServiceDataModel>): MutableList<BaseHiringListDataModel> {
        val listData = mutableListOf<SectionListDataModel>()
        for (data in sectionList) {
            with(data) {
                listData.add(SectionListDataModel(id, listId, name))
            }
        }
        listData.sortBy { it.id }
        return listData as MutableList<BaseHiringListDataModel>
    }

    private fun removeNonNullOrBlankFromList(list: List<HiringApiServiceDataModel>): List<HiringApiServiceDataModel> {
        return list.filter { it.name != null && it.name != "" && it.name != "null" }
    }
}