package com.example.fetchassesment.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchassesment.model.BaseHiringListDataModel

abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(baseListDataModel: BaseHiringListDataModel)
}