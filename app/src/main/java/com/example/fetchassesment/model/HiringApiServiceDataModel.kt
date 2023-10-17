package com.example.fetchassesment.model

import com.google.gson.annotations.SerializedName

/**
 * Sample Api response
 * [ {"id": 755, "listId": 2, "name": ""} ]
 */
data class HiringApiServiceDataModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("listId")
    val listId: String,
    @SerializedName("name")
    val name: String?
)