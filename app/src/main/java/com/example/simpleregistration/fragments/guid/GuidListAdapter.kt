package com.example.simpleregistration.fragments.guid

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.simpleregistration.databinding.GuidItemBinding
import com.example.simpleregistration.fragments.model.Guid
import com.example.simpleregistration.utils.ItemClickListener

class GuidListAdapter(private val callback: ItemClickListener<Guid> ) :
    ListAdapter<Guid, GuidListViewHolder>(GuidListDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = GuidListViewHolder(
        GuidItemBinding.inflate(LayoutInflater.from(parent.context), parent, false), callback
    )

    override fun onBindViewHolder(holder: GuidListViewHolder, position: Int) =
        holder.bind(currentList[position])
}