package com.example.fetchassesment.model

data class SectionListDataModel(
    val id: Int,
    val listId: String,
    val name: String?
) : BaseHiringListDataModel()