package com.example.fetchassesment.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.fetchassesment.databinding.ItemHiringSectionHeadingBinding
import com.example.fetchassesment.databinding.ItemHiringSectionListBinding

enum class ViewType {
    HEADER {
        override fun inflate(parent: ViewGroup): BaseViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding: ItemHiringSectionHeadingBinding =
                ItemHiringSectionHeadingBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            return SectionHeadingViewHolder(binding)
        }
    },
    LIST_SUBSECTION {
        override fun inflate(parent: ViewGroup): BaseViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding: ItemHiringSectionListBinding =
                ItemHiringSectionListBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            return SectionListViewHolder(binding)
        }
    };

    abstract fun inflate(parent: ViewGroup): BaseViewHolder
}