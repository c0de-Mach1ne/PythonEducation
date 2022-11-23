package com.example.simpleregistration.fragments.guid

import androidx.recyclerview.widget.RecyclerView
import com.example.simpleregistration.databinding.GuidItemBinding
import com.example.simpleregistration.fragments.model.Guid

class GuidViewHolder(private val binding: GuidItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(guid: Guid){
        binding.tvGuidName.text = guid.title
    }
}