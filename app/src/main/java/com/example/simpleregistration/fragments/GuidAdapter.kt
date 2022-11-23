package com.example.simpleregistration.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.simpleregistration.databinding.GuidItemBinding
import com.example.simpleregistration.fragments.model.Guid

class GuidAdapter() : ListAdapter<Guid, GuidViewHolder>(GuidDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = GuidViewHolder(
        GuidItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: GuidViewHolder, position: Int) =
        holder.bind(currentList[position])

}