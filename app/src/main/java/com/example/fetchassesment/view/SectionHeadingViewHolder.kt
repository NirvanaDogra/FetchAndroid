package com.example.fetchassesment.view

import com.example.fetchassesment.databinding.ItemHiringSectionHeadingBinding
import com.example.fetchassesment.model.BaseHiringListDataModel
import com.example.fetchassesment.model.SectionHeadingDataModel

class SectionHeadingViewHolder(private val headerView: ItemHiringSectionHeadingBinding) :
    BaseViewHolder(headerView.root) {
    companion object {
        const val SECTION = "Section:"
    }

    override fun bind(baseListDataModel: BaseHiringListDataModel) {
        with(baseListDataModel as SectionHeadingDataModel) {
            headerView.sectionTitle = "$SECTION ${this.heading}"
        }
    }
}
