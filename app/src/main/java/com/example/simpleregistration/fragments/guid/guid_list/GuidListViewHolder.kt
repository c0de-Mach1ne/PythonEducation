package com.example.simpleregistration.fragments.guid.guid_list

import androidx.recyclerview.widget.RecyclerView
import com.example.simpleregistration.databinding.GuidItemBinding
import com.example.simpleregistration.fragments.model.Guid
import com.example.simpleregistration.utils.ItemClickListener

class GuidListViewHolder(
    private val binding: GuidItemBinding,
    private val callback: ItemClickListener<Guid>,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(guid: Guid) {
        binding.root.setOnClickListener { callback.onClickItem(guid) }
        binding.tvGuidName.text = guid.title
    }
}