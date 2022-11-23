package com.example.simpleregistration.fragments

import androidx.recyclerview.widget.RecyclerView
import com.example.simpleregistration.databinding.GuidItemBinding
import com.example.simpleregistration.fragments.model.Guid

class GuidViewHolder(private val binding: GuidItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(guidList: Guid){
        binding.tvGuidName.text = guidList.title
    }
}