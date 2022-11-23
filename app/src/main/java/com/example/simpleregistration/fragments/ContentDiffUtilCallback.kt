package com.example.simpleregistration.fragments

import androidx.recyclerview.widget.DiffUtil
import com.example.simpleregistration.fragments.model.Guid

class ContentDiffUtilCallback : DiffUtil.ItemCallback<Guid>() {

    override fun areItemsTheSame(oldItem: Guid, newItem: Guid) = oldItem == newItem

    override fun areContentsTheSame(oldItem: Guid, newItem: Guid) = oldItem.urlReference == newItem.urlReference
}