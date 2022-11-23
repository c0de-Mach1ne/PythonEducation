package com.example.simpleregistration.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.simpleregistration.databinding.GuidItemBinding
import com.example.simpleregistration.fragments.model.Guid

class ContentAdapter() : ListAdapter<Guid, ContentViewHolder>(ContentDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ContentViewHolder(
        GuidItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) =
        holder.bind(currentList[position])

}