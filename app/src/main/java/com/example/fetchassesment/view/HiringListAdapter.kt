package com.example.fetchassesment.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchassesment.model.BaseHiringListDataModel
import com.example.fetchassesment.model.SectionHeadingDataModel
import com.example.fetchassesment.model.SectionListDataModel

class HiringListAdapter : RecyclerView.Adapter<BaseViewHolder>() {
    companion object {
        private const val HEADER_TYPE = 0
        private const val LIST_SUBSECTION = 1
    }

    private val data = mutableListOf<BaseHiringListDataModel>()


    fun submitData(list: List<BaseHiringListDataModel>) {
        data.addAll(list)
        notifyItemRangeInserted(data.size - list.size, data.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ViewType.values()[viewType].inflate(parent)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (data[position]) {
            is SectionHeadingDataModel -> HEADER_TYPE
            is SectionListDataModel -> LIST_SUBSECTION
            else -> super.getItemViewType(position)
        }
    }
}