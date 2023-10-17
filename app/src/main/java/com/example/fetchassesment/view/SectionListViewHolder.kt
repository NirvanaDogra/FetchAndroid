package com.example.fetchassesment.view

import com.example.fetchassesment.databinding.ItemHiringSectionListBinding
import com.example.fetchassesment.model.BaseHiringListDataModel
import com.example.fetchassesment.model.SectionListDataModel

class SectionListViewHolder(private val listSubSectionView: ItemHiringSectionListBinding) :
    BaseViewHolder(listSubSectionView.root) {
    override fun bind(baseListDataModel: BaseHiringListDataModel) {
        with(baseListDataModel as SectionListDataModel) {
            listSubSectionView.listModel = this
        }
    }
}